package transformada;

import java.util.Collection;

import aplicacion.WebForm;
import maltego.MaltegoEntity;

public class Matricula extends WebForm{

	private String MARCA = "";
	private String MODELO = "";
	
	public Matricula() {
		super("https://www.oscaro.es/");
		cuadroDialogo = "//*[@id='vehicle-input-plate']";
		botonBusqueda = "//button[@type='submit']";
	}
	
	protected void getValues(String recurso) {
		
		String aux = recurso.toString();
		aux = aux.substring(aux.indexOf("<title>"));
		aux = aux.substring(0, aux.indexOf("</title>"));
		
		String array[] = aux.split(" ", 0);

	}
	
	protected Collection<MaltegoEntity> generateOutputEntities(){
		
		MaltegoEntity coche = new MaltegoEntity("FDH.Coche", MARCA+MODELO);
		coche.addField("marca", MARCA);
		coche.addField("modelo", MODELO);
		outputEntities.add(coche);
        
		return outputEntities;
				
	}

}
