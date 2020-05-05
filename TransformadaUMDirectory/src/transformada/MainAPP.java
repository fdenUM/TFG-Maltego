package transformada;

import parser.EmailInputParser;
import resources.ResourceType;

public class MainAPP {

	public static void main(String[] args) {
		EmailInputParser input = new EmailInputParser();
		UMDirectory transformada = new UMDirectory();
		transformada.run(args, input, ResourceType.HTML);
	}

}
