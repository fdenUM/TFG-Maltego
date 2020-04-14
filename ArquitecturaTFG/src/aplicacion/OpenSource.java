package aplicacion;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import client.WebServiceClient;
import maltego.MaltegoEntity;
import maltego.MaltegoTransform;

public abstract class OpenSource {

	protected MaltegoEntity inputEntity;
	protected String source;
	protected WebServiceClient cliente;
	protected Collection <MaltegoEntity> outputEntities;
	protected Resource recurso;

	protected MaltegoTransform mt;

	public OpenSource(WebServiceClient cliente) {	
		this.cliente = cliente;
	}
	
	protected abstract  Collection<MaltegoEntity> getOutputEntities(MaltegoEntity inputEntity);
	
	protected abstract Resource askForResource() throws FailingHttpStatusCodeException, MalformedURLException, IOException;
	
}
