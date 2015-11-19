package eu.europa.ec.grow.espd.entities;

/**
 * Created by vigi on 11/19/15:2:59 PM.
 */
public interface CacParty {

    String getName();

    String getNationalRegistrationNumber();

    String getWebsite();

    String getStreet();

    String getPostalCode();

    String getCity();

    CacCountry getCountry();

    String getContactName();

    String getContactPhone();

    String getContactEmail();
}
