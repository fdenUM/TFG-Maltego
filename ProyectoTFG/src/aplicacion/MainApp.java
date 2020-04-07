package aplicacion;

import java.util.ArrayList;
import java.util.Collection;

import client.HTTPClient;
import client.WebServiceClient;
import maltego.MaltegoEntity;
import parser.EmailInputParser;
import parser.OutputParser;
import transformadas.UMDirectory;

public class MainApp {
	
	private static String inputXML;
	private static MaltegoEntity inputEntity;
	private static OpenSource transformada;
	private static WebServiceClient cliente;
	protected static Collection <MaltegoEntity> outputEntities;
	
	public static void main(String[] args) {
		
		outputEntities = new ArrayList<MaltegoEntity>();
		
		//INICIO: Crear inputEntity
		
		inputXML = args[args.length-1];
		
		EmailInputParser input = new EmailInputParser(inputXML);
		inputEntity = input.getInputEntity();
		
		//FIN: Crear inputEntity
		
		//INICIO: Crear HTTP Client especifico
		cliente = new HTTPClient();
		//FIN: Crear HTTP Client especifico
		
		//INICIO: Creación de transformada
		transformada = new UMDirectory(cliente);
		outputEntities = transformada.getOutputEntities(inputEntity);
		//FIN: Creación de transformada
		
		OutputParser output = new OutputParser(outputEntities);
		
		output.getOutput();
		
		
		
		/*
		 * Pasar transformada concreta como parametro en lugar de crear multiples proyectos
		 * No, deben de ser tantos proyectos como transformadas haya.
		 * 
		 * 
		 * ¿Es necesario crear la MaltegoEntity con la entrada? Se parsea el XML y se saca
		 * la informacion. Pero ¿hace falta crear la Entity de entrada?
		 * Inputparser devuelve maltegoentity
		 * 
		 * 
		 * La clase WebServiceClient no la entiendo muy bien. Toda la funcionalidad recae sobre
		 * HTTPClient.
		 * La clase MainApp tiene un atributo WebServiceClient
		 * 
		 * 
		 * 
		 * WebServiceClient puede ser que sea un atributo abstract. Y openSource tambien seria
		 * una clase abstracta
		 * getOutputEntities se implementará en la clase de mas abajo pero estara definida
		 * arriba en la clase abstracta
		 * 
		 * El Main es otra clase distinta. getOutputEntity se define en UMUDirectory.
		 * El main utiliza el input al principio, el getoutputentities, y luego el output parser
		 */

	}

}
