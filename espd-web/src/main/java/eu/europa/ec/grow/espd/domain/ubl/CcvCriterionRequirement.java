package eu.europa.ec.grow.espd.domain.ubl;

import java.util.List;

/**
 *
 *
 * Created by vigi on 11/23/15:11:49 AM.
 */
public interface CcvCriterionRequirement {

    String getId();

    String getDescription();

    CcvResponseType getResponseType();

    List<String> getEspdCriterionFields();
}
