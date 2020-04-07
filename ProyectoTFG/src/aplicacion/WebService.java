package aplicacion;

import java.util.Collection;

import client.WebServiceClient;
import maltego.MaltegoEntity;

public class WebService extends OpenSource{

	public WebService(WebServiceClient cliente) {
		super(cliente);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Collection<MaltegoEntity> getOutputEntities(MaltegoEntity inputEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void askForResource() {
		// TODO Auto-generated method stub
		
	}

}
