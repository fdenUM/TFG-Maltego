package transformada;

import parser.AliasInputParser;
import resources.ResourceType;

public class MainAPP {

	public static void main(String[] args) {
			
		AliasInputParser input = new AliasInputParser();
		Matricula transformada = new Matricula();
		transformada.run(args, input, ResourceType.HTML);

	}

}
