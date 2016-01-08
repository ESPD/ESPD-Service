package eu.europa.ec.grow.espd.xml.response.importing.selection
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/8/16 at 11:45 AM.
 */
class WorkContractsPerformanceOfWorksImportTest extends AbstractXmlFileImport {

    def "13. should import all fields of 'For works contracts: performance of works of the specified type'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/work_contracts_performance_of_works_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.workContractsPerformanceOfWorks.exists == true

        then:
        espd.workContractsPerformanceOfWorks.description1 == "description1"
        espd.workContractsPerformanceOfWorks.amount1 == 111
        espd.workContractsPerformanceOfWorks.currency1 == "RON"
        espd.workContractsPerformanceOfWorks.date1 == LocalDateAdapter.unmarshal("2016-17-01").toDate()
        espd.workContractsPerformanceOfWorks.recipients1 == "recipients1"

        then:
        espd.workContractsPerformanceOfWorks.description2 == "description2"
        espd.workContractsPerformanceOfWorks.amount2 == 222
        espd.workContractsPerformanceOfWorks.currency2 == "EUR"
        espd.workContractsPerformanceOfWorks.date2 == LocalDateAdapter.unmarshal("2016-18-01").toDate()
        espd.workContractsPerformanceOfWorks.recipients2 == "recipients2"

        then:
        espd.workContractsPerformanceOfWorks.description3 == "description3"
        espd.workContractsPerformanceOfWorks.amount3 == 333
        espd.workContractsPerformanceOfWorks.currency3 == "USD"
        espd.workContractsPerformanceOfWorks.date3 == LocalDateAdapter.unmarshal("2016-19-01").toDate()
        espd.workContractsPerformanceOfWorks.recipients3 == "recipients3"

        then: "info electronically"
        espd.workContractsPerformanceOfWorks.availableElectronically.exists == true
        espd.workContractsPerformanceOfWorks.availableElectronically.url == "www.hodor.com"
        espd.workContractsPerformanceOfWorks.availableElectronically.code == "PERF"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true,
                description1: "description1", amount1: 111, currency1: "RON", date1: new Date(), recipients1: "recipients1",
                description2: "description2", amount2: 222, currency2: "EUR", date2: new Date().plus(1), recipients2: "recipients2",
                description3: "description3", amount3: 333, currency3: "USD", date3: new Date().plus(2), recipients3: "recipients3",
                availableElectronically: new AvailableElectronically(exists: true, url: "www.hodor.com", code: "PERF")))

        expect:
        1 == 1
    }

}