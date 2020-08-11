package transformada;

import java.util.Collection;

import aplicacion.Directory;
import maltego.MaltegoEntity;

public class UADirectory extends Directory{

	private String TELEFONO = "";
	private String CORREO = "";
	private String CENTRO = "";
	private String DEPARTAMENTO = "";
	
	public UADirectory() {
		super("https://cvnet.cpd.ua.es/Directorio/");
		cuadroDialogo = "Que";
		botonBusqueda = "//button[@type='submit']";	
	}
	
	
	protected void getValues(String recurso) {
		
		String aux = recurso.toString();
		aux = aux.substring(aux.indexOf("<div id=\"datos_basicos\">"));
		
		aux = aux.replaceAll("<.*?>", " ");
		aux = aux.replaceAll("\\s{2,}", "\n");	
		aux = aux.substring(1, aux.indexOf("Guardar"));
		
		String array[] = aux.split("\n", 0);
				
		for (int i = 4; i<array.length; i++) {
			if (array[i].equalsIgnoreCase("correo-e") && CORREO == "") {
				CORREO = array[i+1];
				i = i+2;
			}
			if(array[i].contains("tel") && TELEFONO != ""){
				TELEFONO = array[i+1];
				i++;
			}
			if(array[i].equalsIgnoreCase("centro") && CENTRO == "") {
				CENTRO = array[i+1];
				i++;
			}
			if(array[i].equalsIgnoreCase("departamento") && DEPARTAMENTO == "") {
				DEPARTAMENTO = array[i+1];
				i++;
			}
		}

	}
	
	protected Collection<MaltegoEntity> generateOutputEntities(){
		
		if (TELEFONO != "") {
			MaltegoEntity phone = new MaltegoEntity("maltego.PhoneNumber", TELEFONO);
			outputEntities.add(phone);
		}
		
		if(CORREO != "") {
			MaltegoEntity email = new MaltegoEntity("maltego.EmailAddress", CORREO);
			outputEntities.add(email);
		}
		
        if (CENTRO != "") {
            MaltegoEntity office = new MaltegoEntity("maltego.Office", CENTRO);
            outputEntities.add(office);
        }
		
        if (DEPARTAMENTO != "") {
            MaltegoEntity workingPlace = new MaltegoEntity("maltego.EducationInstitution", DEPARTAMENTO);
            outputEntities.add(workingPlace);
        }
        
		return outputEntities;
				
	}
}
