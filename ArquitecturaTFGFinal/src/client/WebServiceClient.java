package client;

import resources.Resource;
import resources.ResourceType;

public abstract class WebServiceClient {

	protected Resource resource;
	protected ResourceType tipoRecurso;
	
	public WebServiceClient(ResourceType tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}
	
	public abstract Resource getResource();

	public abstract void setCurrentPage(String source);
	
	public abstract void searchTextField(String xPath, String value);
	
	public abstract void searchAndClickButton(String xPath);
}
