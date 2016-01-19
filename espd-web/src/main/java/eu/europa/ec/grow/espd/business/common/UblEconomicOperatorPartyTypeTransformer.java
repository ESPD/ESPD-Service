package eu.europa.ec.grow.espd.business.common;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.EconomicOperatorImpl;
import grow.names.specification.ubl.schema.xsd.espd_commonaggregatecomponents_1.EconomicOperatorPartyType;
import grow.names.specification.ubl.schema.xsd.espd_commonbasiccomponents_1.IndicatorType;
import org.springframework.stereotype.Component;

/**
 * Created by ratoico on 1/18/16 at 5:34 PM.
 */
@Component
public class UblEconomicOperatorPartyTypeTransformer implements Function<EconomicOperatorImpl, EconomicOperatorPartyType> {

    private final UblPartyTypeTransformer partyTypeTransformer = new UblPartyTypeTransformer();

    @Override
    public EconomicOperatorPartyType apply(EconomicOperatorImpl input) {
        if (input == null) {
            return null;
        }

        EconomicOperatorPartyType eoPartyType = new EconomicOperatorPartyType();

        eoPartyType.setParty(partyTypeTransformer.apply(input));

        IndicatorType smeIndicator = new IndicatorType();
        smeIndicator.setValue(Boolean.TRUE.equals(input.getIsSmallSizedEnterprise()));
        eoPartyType.setSMEIndicator(smeIndicator);

        return eoPartyType;
    }
}
