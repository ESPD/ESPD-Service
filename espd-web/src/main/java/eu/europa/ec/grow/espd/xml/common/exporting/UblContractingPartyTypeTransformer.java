package eu.europa.ec.grow.espd.xml.common.exporting;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.ubl.CacParty;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContractingPartyType;
import org.springframework.stereotype.Component;

/**
 * Transforms the information coming from a {@link CacParty} into a {@link ContractingPartyType} object.
 * <p/>
 * Created by vigi on 11/16/15:3:29 PM.
 */
@Component
public class UblContractingPartyTypeTransformer implements Function<CacParty, ContractingPartyType> {

    private final UblPartyTypeTransformer partyTypeTransformer = new UblPartyTypeTransformer();

    @Override
    public ContractingPartyType apply(CacParty party) {
        if (party == null) {
            return null;
        }
        ContractingPartyType contractingPartyType = new ContractingPartyType();

        contractingPartyType.setParty(partyTypeTransformer.apply(party));

        return contractingPartyType;
    }

}
