package aplicacion;

import org.openqa.selenium.WebDriver;

import client.WebServiceClient;

public class WebSite extends WebService{

	protected WebDriver chrome;
	protected String htmlDoc;
	
	public WebSite(WebServiceClient cliente) {
		super(cliente);
		chrome = (WebDriver) cliente.getResource();
	}

}
