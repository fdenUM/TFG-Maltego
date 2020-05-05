package parser;

import java.util.Collection;

import maltego.MaltegoEntity;
import maltego.MaltegoTransform;

public class OutputParser {

	protected static Collection <MaltegoEntity> outputEntities;
	
	public OutputParser (Collection <MaltegoEntity> outputEntities) {
		OutputParser.outputEntities = outputEntities;
	}
	
	public void getOutput() {
		MaltegoTransform mtOutput = new MaltegoTransform();
		for (MaltegoEntity entity : outputEntities) mtOutput.addEntity(entity);
		mtOutput.returnOutput();
	}
}