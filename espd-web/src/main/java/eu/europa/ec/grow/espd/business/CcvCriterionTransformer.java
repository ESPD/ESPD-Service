package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.criteria.CcvCriterion;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.NameType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TypeCodeType;
import org.springframework.stereotype.Component;

/**
 * Created by vigi on 11/16/15:3:38 PM.
 */
@Component
class CcvCriterionTransformer implements Function<CcvCriterion, CriterionType> {

    @Override
    public CriterionType apply(CcvCriterion input) {
        CriterionType criterionType = new CriterionType();

        addCriterionID(input, criterionType);
        addTypeCode(input, criterionType);
        addName(input, criterionType);
        addDescription(input, criterionType);

        return criterionType;
    }

    private void addCriterionID(final CcvCriterion input, final CriterionType criterionType) {
        IDType idType = new IDType();
        idType.setValue(input.getId());
        idType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        idType.setSchemeVersionID("1.0");
        idType.setSchemeID("CriteriaID");
        criterionType.setCriterionID(idType);
    }

    private void addTypeCode(final CcvCriterion input, final CriterionType criterionType) {
        TypeCodeType typeCodeType = new TypeCodeType();
        typeCodeType.setValue(input.getTypeCode());
        typeCodeType.setListAgencyID(Agency.EU_COM_GROW.getIdentifier());
        typeCodeType.setListID("CriteriaTypeCode");
        typeCodeType.setListVersionID("1.0");
        criterionType.setCriterionTypeCode(typeCodeType);
    }

    private void addName(final CcvCriterion input, final CriterionType criterionType) {
        NameType nameType = new NameType();
        nameType.setValue(input.getName());
        criterionType.setCriterionName(nameType);
    }

    private void addDescription(final CcvCriterion input, final CriterionType criterionType) {
        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(input.getDescription());
        criterionType.setCriterionDescription(descriptionType);
    }
}
