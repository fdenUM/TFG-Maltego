package parser;

public class EmailInputParser extends InputParser{
	
	public EmailInputParser(String inputXML) {
		
		super(inputXML);
		inputEntityKey = "email";
		inputEntityType = "maltego.EmailAddress";
		super.generateInputEntity();
		
	}
}
