package parser;

public class PersonInputParser extends InputParser{

	public PersonInputParser() {
		super();
		inputEntityKey = "person.fullname";
		inputEntityType = "maltego.Person";
	}
}
