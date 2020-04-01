package aplicacion;

import java.util.Collection;

import client.HTTPClient;
import client.WebServiceClient;
import maltego.MaltegoEntity;

public abstract class OpenSource {

	protected MaltegoEntity inputEntity;
	protected String source;

	public OpenSource(MaltegoEntity inputEntity) {	
		this.inputEntity = inputEntity;
	}
	
	protected abstract  Collection<MaltegoEntity> getOutputEntities(MaltegoEntity inputEntity);
	
	protected abstract void askForResource(WebServiceClient cliente);
	
}
