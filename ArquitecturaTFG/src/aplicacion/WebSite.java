package aplicacion;

import java.util.Collection;

import client.WebServiceClient;
import maltego.MaltegoEntity;

public class WebSite extends WebService{

	protected String htmlDoc;
	
	public WebSite(WebServiceClient cliente) {
		super(cliente);
	}

	@Override
	protected Collection<MaltegoEntity> generateOutputEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void getValues() {
		// TODO Auto-generated method stub
		
	}
	
	
}