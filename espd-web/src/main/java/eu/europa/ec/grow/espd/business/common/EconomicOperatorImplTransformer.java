package eu.europa.ec.grow.espd.business.common;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.EconomicOperatorImpl;
import eu.europa.ec.grow.espd.domain.PartyImpl;
import grow.names.specification.ubl.schema.xsd.espd_commonaggregatecomponents_1.EconomicOperatorPartyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ratoico on 1/19/16 at 10:27 AM.
 */
@Component
@Slf4j
public class EconomicOperatorImplTransformer implements Function<EconomicOperatorPartyType, EconomicOperatorImpl> {

    private final PartyImplTransformer partyImplTransformer;

    @Autowired
    EconomicOperatorImplTransformer(PartyImplTransformer partyImplTransformer) {
        this.partyImplTransformer = partyImplTransformer;
    }

    @Override
    public EconomicOperatorImpl apply(EconomicOperatorPartyType input) {
        EconomicOperatorImpl impl = new EconomicOperatorImpl();

        if (input.getParty() != null) {
            PartyImpl party = partyImplTransformer.apply(input.getParty());
            impl.copyProperties(party);
        }

        return impl;
    }
}
