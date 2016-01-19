package eu.europa.ec.grow.espd.entities;

/**
 * The contracting authority or contracting entity who is buying supplies,
 * services or public works using a tendering procedure as described in the
 * applicable directive (Directives 2014/24/EU, 2014/25/EU).
 * <p/>
 * Created by vigi on 11/19/15:2:59 PM.
 */
public interface CacParty {

    String getName();
    
    String getVatNumber();

    String getAnotherNationalId();

    String getWebsite();

    String getStreet();

    String getPostalCode();

    String getCity();

    CacCountry getCountry();

    String getContactName();

    String getContactPhone();

    String getContactEmail();
}
