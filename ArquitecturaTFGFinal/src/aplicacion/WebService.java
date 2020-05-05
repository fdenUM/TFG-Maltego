package aplicacion;

import java.util.Collection;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import client.HTTPClient;
import maltego.MaltegoEntity;
import resources.Resource;

public abstract class WebService extends OpenSource{
	
	protected String cuadroDialogo;
	protected String botonBusqueda;
	
	public WebService(String source) {
		super(source);
		cliente = (HTTPClient) cliente;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<MaltegoEntity> getOutputEntities(MaltegoEntity inputEntity) {
		
		this.inputEntity = inputEntity;		
		try {
			recurso = askForResource();
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getValues(recurso.toString());
		return generateOutputEntities();
	}

	protected abstract Collection<MaltegoEntity> generateOutputEntities();

	protected abstract void getValues(String string);

	@Override
	protected Resource askForResource() {
		cliente.setCurrentPage(source.toString());
		
		cliente.searchTextField(cuadroDialogo, inputEntity.getEntityValue());
			
		cliente.searchAndClickButton(botonBusqueda);
		
		return cliente.getResource();
	}

}
