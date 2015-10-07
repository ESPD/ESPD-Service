package eu.grow.espd.controller;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import eu.grow.espd.domain.EspdDocument;

@Controller
@SessionAttributes("espd")
public class DownloadController {

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
}
