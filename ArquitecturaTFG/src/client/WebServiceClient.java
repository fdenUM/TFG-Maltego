package client;

import aplicacion.Resource;

public abstract class WebServiceClient {

	protected Resource resource;
	
	public abstract Resource getResource();

	public abstract void setCurrentPage(String source);
	
	public abstract void searchTextField(String xPath, String value);
	
	public abstract void searchAndClickButton(String xPath);
}
