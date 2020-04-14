package client;


import java.io.IOException;
import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import aplicacion.HTML;
import aplicacion.Resource;

public class HTTPClient extends WebServiceClient{
	
	private WebClient client;
	private HtmlPage currentPage;
	
	public HTTPClient() {
		
		super();
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
		client = new WebClient(BrowserVersion.CHROME);
		client.addRequestHeader("Content-Type","text/html; charset=utf-8");
		client.addRequestHeader("Accept-Language" , "es");
	}
	
	public void searchTextField(String xPath, String value) {
		HtmlTextInput cuadrobusqueda = currentPage.getElementByName(xPath);
		cuadrobusqueda.setValueAttribute(value);
	}
	
	public void setCurrentPage(String url) {
		try {
			currentPage = client.getPage(url);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchAndClickButton(String xPath) {
		HtmlButtonInput button = currentPage.getElementByName(xPath);
		try {
			currentPage = button.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Resource getResource() {
		WebResponse response = currentPage.getWebResponse();
		resource = new HTML(response.getContentAsString());
		return resource;
	}
	
}
