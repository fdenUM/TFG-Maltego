package client;


import java.io.IOException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import resources.Binary;
import resources.HTML;
import resources.JSON;
import resources.Resource;
import resources.ResourceType;
import resources.XML;

public class HTTPClient extends WebServiceClient{
		
	
	
	public HTTPClient(ResourceType tipo) {
		super(tipo);
	
		switch (tipoRecurso) {
		case HTML:
			resource = new HTML();
			break;
		case JSON:
			resource = new JSON();
			break;
		case XML:
			resource = new XML();
			break;
		case Binary:
			resource = new Binary();
			break;
		}
	}
	
	public void searchTextField(String xPath, String value) {
		switch (tipoRecurso) {
		case HTML:
			HTML resourceHTML = (HTML) resource;
			resourceHTML.searchTextField(xPath, value);			
			break;
		default:
			break;
		}
	}
	public void setCurrentPage(String url) {
		switch (tipoRecurso) {
		case HTML:
			HTML resourceHTML = (HTML) resource;
			try {
				resourceHTML.getPage(url);
			} catch (FailingHttpStatusCodeException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

	}
	
	public void searchAndClickButton(String xPath) {
		switch (tipoRecurso) {
		case HTML:
			HTML resourceHTML = (HTML) resource;
			resourceHTML.searchAndClickButton(xPath);
			break;
		default:
			break;
		}
	}

	@Override
	public Resource getResource() {
		switch (tipoRecurso) {
		case HTML:
			HTML resourceHTML = (HTML) resource;
			return resourceHTML.getResource();
		default:
			break;
		}
		return resource;
	}
}
