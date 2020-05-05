package aplicacion;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import client.HTTPClient;
import client.WebServiceClient;
import maltego.MaltegoEntity;
import parser.InputParser;
import parser.OutputParser;
import resources.Resource;
import resources.ResourceType;

public abstract class OpenSource {

	protected MaltegoEntity inputEntity;
	protected URI source;
	protected WebServiceClient cliente;
	protected Collection <MaltegoEntity> outputEntities;
	protected Resource recurso;

	public OpenSource(String url) {	
		try {
			source = new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract  Collection<MaltegoEntity> getOutputEntities(MaltegoEntity inputEntity);
	
	protected abstract Resource askForResource() throws FailingHttpStatusCodeException, MalformedURLException, IOException;
	
	public void run(String[] args, InputParser input, ResourceType tipoRecurso) {
		outputEntities = new ArrayList<MaltegoEntity>();

		input.generateInputEntity(args[args.length-1]);
		
		//INICIO: Crear inputEntity
		inputEntity = input.getInputEntity();
		//FIN: Crear inputEntity
		
		//INICIO: Crear HTTP Client especifico
		cliente = new HTTPClient(tipoRecurso);
		//FIN: Crear HTTP Client especifico
		
		//INICIO: Creación de transformada
		outputEntities = getOutputEntities(inputEntity);
		//FIN: Creación de transformada
		
		OutputParser output = new OutputParser(outputEntities);
		output.getOutput();
	}
}
