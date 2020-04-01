package client;

import org.openqa.selenium.WebDriver;

public abstract class WebServiceClient {

	protected Object resource;
	
	public abstract Object getResource();
}
