package resources;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class HTML extends Resource{

	private HtmlPage currentPage;
	private HtmlTextInput cuadrobusqueda;
	private WebClient client;
	private WebResponse response;
	
	public HTML() {
		super();
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
		client = new WebClient(BrowserVersion.CHROME);
		client = new WebClient(BrowserVersion.CHROME);
		client.addRequestHeader("Content-Type","text/html; charset=utf-8");
		client.addRequestHeader("Accept-Language" , "es");
	}

	@Override
	public String toString() {
		return response.getContentAsString();
	}
	public void getPage(String url) throws MalformedURLException, IOException {
		currentPage = client.getPage(url);
	}
	
	public void searchTextField(String xPath, String value) {
			cuadrobusqueda = currentPage.getElementByName(xPath);
			cuadrobusqueda.setValueAttribute(value);
	}
	

	public void searchAndClickButton(String xPath) {
		HtmlElement button = (HtmlElement) currentPage.getByXPath(xPath).get(0);
		try {
			currentPage = button.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Resource getResource() {
		response = currentPage.getWebResponse();
		return this;
	}
}
