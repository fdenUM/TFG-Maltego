package parser;

import java.util.HashMap;
import maltego.MaltegoEntity;

public class InputParser {

	HashMap<String, String> data;
	protected MaltegoEntity inputEntity;
	protected String inputEntityType;
	protected String inputEntityKey;


	public InputParser() {
		data = new HashMap<String, String>();
	}
	
	public HashMap<String, String> getData(){
		return data;
	}
	
	private void parseInput(String entrada) {
		
		String cadena;
		String[] variable;
		String[] datos = entrada.split("#",0);

		for (int i=0; i<datos.length; i++) {
			cadena = datos[i];
			variable = cadena.split("=");
			data.put(variable[0], variable[1]);
		}
	}
	
	public MaltegoEntity getInputEntity() {
		return inputEntity;
	}
	
	public void generateInputEntity(String input) {
		parseInput(input);
		inputEntity = new MaltegoEntity(inputEntityType, data.get(inputEntityKey));
	}
	
}
