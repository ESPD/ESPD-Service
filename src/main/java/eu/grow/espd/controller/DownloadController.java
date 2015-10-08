package eu.grow.espd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("espd")
public class DownloadController {
/*
	fails in jenkins
	see comment https://webgate.ec.europa.eu/CITnet/jira/browse/ESPD-16


	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download(@ModelAttribute("espd") EspdDocument espd,
			HttpServletRequest request, HttpServletResponse response)

	throws Exception {
		Marshaller m = JAXBContext.newInstance(EspdDocument.class)
				.createMarshaller();

		response.setHeader("Content-Disposition",
				"attachment;filename=espd.xml");
		response.setContentType("application/octet-stream");

		ServletOutputStream out = response.getOutputStream();

		m.marshal(espd, out);

		out.flush();
		out.close();

		return null;
	}
	*/
}
