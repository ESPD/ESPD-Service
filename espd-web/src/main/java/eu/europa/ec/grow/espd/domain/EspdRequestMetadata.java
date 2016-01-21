package eu.europa.ec.grow.espd.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by ratoico on 1/21/16 at 11:01 AM.
 */
@Data
public class EspdRequestMetadata {

    private String id;

    private Date issueDate;

    private String url;

    private String description;
}
