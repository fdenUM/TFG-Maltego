package aplicacion;

import java.util.Collection;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import client.HTTPClient;
import client.WebServiceClient;
import maltego.MaltegoEntity;
import maltego.MaltegoTransform;

public abstract class WebService extends OpenSource{
	
	protected String cuadroDialogo;
	protected String botonBusqueda;
	
	public WebService(WebServiceClient cliente) {
		super(cliente);
		cliente = (HTTPClient) cliente;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Collection<MaltegoEntity> getOutputEntities(MaltegoEntity inputEntity) {
		
		this.inputEntity = inputEntity;
		mt = new MaltegoTransform();
		
		try {
			recurso = askForResource();
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getValues();
		return generateOutputEntities();
	}

	protected abstract Collection<MaltegoEntity> generateOutputEntities();

	protected abstract void getValues();

	@Override
	protected Resource askForResource() {
		cliente.setCurrentPage(source);
		
		cliente.searchTextField(cuadroDialogo, inputEntity.getEntityValue());
			
		cliente.searchAndClickButton(botonBusqueda);
		
		return cliente.getResource();
	}

}
