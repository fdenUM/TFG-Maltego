package aplicacion;

import org.openqa.selenium.WebDriver;

import maltego.MaltegoEntity;

public class WebSite extends WebService{

	protected WebDriver chrome;
	protected String htmlDoc;
	
	public WebSite(MaltegoEntity inputEntity) {
		super(inputEntity);
		// TODO Auto-generated constructor stub
	}

}
