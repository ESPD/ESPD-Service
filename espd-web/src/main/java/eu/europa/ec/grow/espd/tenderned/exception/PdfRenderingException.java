package eu.europa.ec.grow.espd.tenderned.exception;/*
 * Copyright 2009-2016 PIANOo; TenderNed programma.
 */

/**
 * @author Desiree Hof
 * @since 11-07-2016
 */
public class PdfRenderingException extends Exception {

    /**
     * @see Exception#Exception(String)
     */
    public PdfRenderingException(final String message) {
        super(message);
    }

    /**
     * @see Exception#Exception(Throwable)
     */
    public PdfRenderingException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * @see Exception#Exception(String, Throwable)
     */
    public PdfRenderingException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
