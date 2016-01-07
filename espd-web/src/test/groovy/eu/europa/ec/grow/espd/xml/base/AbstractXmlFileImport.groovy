package eu.europa.ec.grow.espd.xml.base
/**
 * Created by ratoico on 1/7/16 at 10:51 AM.
 */
class AbstractXmlFileImport extends AbstractEspdXmlMarshalling {

    protected String importXmlRequestFile(String fileName) {
        return new File("./src/test/groovy/eu/europa/ec/grow/espd/xml/samples/request/${fileName}").getText('UTF-8')
    }

    protected String importXmlResponseFile(String fileName) {
        return new File("./src/test/groovy/eu/europa/ec/grow/espd/xml/samples/response/${fileName}").getText('UTF-8')
    }

}