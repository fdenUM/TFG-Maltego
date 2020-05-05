package parser;

public class EmailInputParser extends InputParser{
	
	public EmailInputParser() {
		super();
		inputEntityKey = "email";
		inputEntityType = "maltego.EmailAddress";
	}
}
