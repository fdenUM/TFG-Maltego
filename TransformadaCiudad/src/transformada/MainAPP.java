package transformada;

import parser.DocumentInputParser;
import resources.ResourceType;

public class MainAPP {
	
	public static void main(String[] args) {
		DocumentInputParser input = new DocumentInputParser();
		Ciudad transformada = new Ciudad();
		transformada.run(args, input, ResourceType.HTML);
	}
}
