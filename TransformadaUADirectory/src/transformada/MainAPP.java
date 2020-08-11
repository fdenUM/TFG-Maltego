package transformada;

import parser.PersonInputParser;
import resources.ResourceType;

public class MainAPP {

	public static void main(String[] args) {
		
		PersonInputParser input = new PersonInputParser();
		UADirectory transformada = new UADirectory();
		transformada.run(args, input, ResourceType.HTML);

	}

}
