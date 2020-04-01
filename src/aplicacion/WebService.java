package aplicacion;

import java.util.Collection;

import client.WebServiceClient;
import maltego.MaltegoEntity;

public class WebService extends OpenSource{

	public WebService(MaltegoEntity inputEntity) {
		super(inputEntity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Collection<MaltegoEntity> getOutputEntities(MaltegoEntity inputEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void askForResource(WebServiceClient cliente) {
		// TODO Auto-generated method stub
		
	}

}
