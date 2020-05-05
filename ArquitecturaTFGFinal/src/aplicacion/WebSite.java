package aplicacion;

import java.util.Collection;
import maltego.MaltegoEntity;

public class WebSite extends WebService{

	protected String htmlDoc;
	
	public WebSite(String source) {
		super(source);
	}

	@Override
	protected Collection<MaltegoEntity> generateOutputEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void getValues(String recurso) {
		// TODO Auto-generated method stub
		
	}

	
	
}