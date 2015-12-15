package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.criteria.enums.CriterionJurisdictionLevel;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.LegislationType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Transforms the criterion information coming from ESPD into a {@link CriterionType} object.
 * <p/>
 * Created by vigi on 11/16/15:3:38 PM.
 */
@Component
class CcvCriterionToCriterionTypeTransformer implements Function<CcvCriterion, CriterionType> {

    private final ToCriterionRequirementTransformer criterionRequirementTransformer;

    @Autowired
    CcvCriterionToCriterionTypeTransformer(ToCriterionRequirementTransformer criterionRequirementTransformer) {
        this.criterionRequirementTransformer = criterionRequirementTransformer;
    }

    @Override
    public CriterionType apply(CcvCriterion input) {
        CriterionType criterionType = new CriterionType();

        addCriterionID(input, criterionType);
        addTypeCode(input, criterionType);
        addName(input, criterionType);
        addDescription(input, criterionType);
        addLegislationReference(input, criterionType);
        addSubcriteria(input, criterionType);

        return criterionType;
    }

    private void addCriterionID(CcvCriterion input, CriterionType criterionType) {
        IDType idType = new IDType();
        idType.setValue(input.getUuid());
        idType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        idType.setSchemeVersionID(eu.europa.ec.grow.espd.criteria.enums.CriterionType.SCHEME_VERSION_ID);
        idType.setSchemeID(eu.europa.ec.grow.espd.criteria.enums.CriterionType.SCHEME_ID);
        criterionType.setID(idType);
    }

    private void addTypeCode(CcvCriterion input, CriterionType criterionType) {
        TypeCodeType typeCodeType = new TypeCodeType();
        typeCodeType.setValue(input.getTypeCode());
        typeCodeType.setListAgencyID(Agency.EU_COM_GROW.getIdentifier());
        typeCodeType.setListID(eu.europa.ec.grow.espd.criteria.enums.CriterionType.LIST_ID);
        typeCodeType.setListVersionID(eu.europa.ec.grow.espd.criteria.enums.CriterionType.LIST_VERSION_ID);
        criterionType.setTypeCode(typeCodeType);
    }

    private void addName(CcvCriterion input, CriterionType criterionType) {
        NameType nameType = new NameType();
        nameType.setValue(input.getName());
        criterionType.setName(nameType);
    }

    private void addDescription(CcvCriterion input, CriterionType criterionType) {
        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(input.getDescription());
        criterionType.setDescription(descriptionType);
    }

    private void addLegislationReference(CcvCriterion input, CriterionType criterionType) {
        if (input.getLegislation() == null) {
            return;
        }

        LegislationType legislationType = new LegislationType();

        TextType title = new TextType();
        title.setValue(input.getLegislation().getTitle());
        legislationType.setTitle(title);

        DescriptionType description = new DescriptionType();
        description.setValue(input.getLegislation().getDescription());
        legislationType.setDescription(description);

        TypeCodeType jurisdictionLevelCode = new TypeCodeType();
        jurisdictionLevelCode.setValue(CriterionJurisdictionLevel.EU_DIRECTIVE.getCode());
        jurisdictionLevelCode.setListAgencyID(Agency.EU_COM_GROW.getIdentifier());
        jurisdictionLevelCode.setListID(CriterionJurisdictionLevel.LIST_ID);
        jurisdictionLevelCode.setListVersionID(CriterionJurisdictionLevel.LIST_VERSION_ID);
        legislationType.setJurisdictionLevelCode(jurisdictionLevelCode);

        TextType article = new TextType();
        article.setValue(input.getLegislation().getArticle());
        legislationType.setArticle(article);

        URIType uriid = new URIType();
        uriid.setValue(input.getLegislation().getUrl());
        legislationType.setURI(uriid);

        criterionType.getLegislationReference().add(legislationType);
    }

    private void addSubcriteria(CcvCriterion input, CriterionType criterionType) {
        if (CollectionUtils.isEmpty(input.getSubCriteria())) {
            return;
        }

        List<CriterionType> groupTypes = Lists.transform(input.getSubCriteria(), this);
        criterionType.getSubCriterion().addAll(groupTypes);
    }

}
