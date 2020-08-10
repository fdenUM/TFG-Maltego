package aplicacion;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;

import maltego.MaltegoEntity;
import resources.Resource;

public class File extends OpenSource{

	public File(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<MaltegoEntity> getOutputEntities(MaltegoEntity inputEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Resource askForResource() throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
