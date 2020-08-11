package transformada;

import java.util.Collection;

import aplicacion.Directory;
import maltego.MaltegoEntity;

public class UMDirectory extends Directory{
	private String NOMBRE = "";
	private String AREA = "";
	private String UNIDAD = "Departamento de ";
    private String TELEFONO = "";
    private String DIRECCION = "";
    private String CENTRO = "";
    private String PUESTO = "";
    private String DESPACHO = "";
    private String FILIACION = "";
    private String WEB = "";
    
    
    public UMDirectory() {
		super("https://www.um.es/atica/directorio/");
		cuadroDialogo = "texto";
		botonBusqueda = "//input[@name='buscar']";
	}
    
	
	protected void getValues(String recurso) {
		
		String aux = recurso.toString();
		aux = aux.substring(aux.indexOf("urlsUnidades\'>"));
		
		aux = aux.replaceAll("<.*?>", " ");
		aux = aux.replaceAll("\\s{2,}", "\n");	
		aux = aux.substring(15, aux.indexOf("Tarjeta"));
		
		String array[] = aux.split("\n", 0);
		
		for (int i = 0; i<array.length; i++) {
			if (array[i].equalsIgnoreCase("Nombre:")) {
				NOMBRE = array[i+1];
				i++;
			}
			else if(array[i].equalsIgnoreCase("Area de conocimiento:")) {
				AREA = array[i+1];
				i++;
			}
			else if(array[i].equalsIgnoreCase("Unidad Organizativa:")) {
				UNIDAD += array[i+2];
				i += 2;
			}
			else if(array[i].equalsIgnoreCase("Teléfono:")) {
				TELEFONO = array[i+1];
				i++;
			}
			else if(array[i].equalsIgnoreCase("Dirección postal:")) {
				DIRECCION = array[i+1];
				i++;
			}
			else if(array[i].equalsIgnoreCase("Centro:")) {
				CENTRO = array[i+1];
				i++;
			}
			else if(array[i].equalsIgnoreCase("Puesto:")) {
				PUESTO = array[i+1];
				i++;
			}
			else if(array[i].equalsIgnoreCase("Despacho:")) {
				DESPACHO = array[i+1];
				i++;
			}
			else if(array[i].equalsIgnoreCase("Filiación:")) {
				FILIACION = array[i+1];
				i++;
			}
			else if(array[i].equalsIgnoreCase("Web personal institucional:")) WEB = array[i+1];
		}

	}

	
	protected Collection<MaltegoEntity> generateOutputEntities(){
		
		if (NOMBRE != "") {
			MaltegoEntity person = new MaltegoEntity("maltego.Person",NOMBRE);
			if (AREA != "") person.addField("area", "Área", AREA);
			if (UNIDAD != "") person.addField("unidad", "Unidad", UNIDAD);
			if (PUESTO != "") person.addField("puesto", "Puesto", PUESTO);
			if (FILIACION != "") person.addField("filiacion", "Filiacion", FILIACION);
			outputEntities.add(person);
		}
		if (TELEFONO != "") {
			MaltegoEntity telephone = new MaltegoEntity("maltego.PhoneNumber");
			telephone.addField("phonenumber", TELEFONO);
			outputEntities.add(telephone);
		}
		
		if (CENTRO != "") {
			MaltegoEntity workingPlace = new MaltegoEntity("maltego.Organization");
			workingPlace.addField("title", CENTRO);
			if(DIRECCION != "") workingPlace.addField("direccion", DIRECCION);
			if(DESPACHO != "") workingPlace.addField("despacho", DESPACHO);
			outputEntities.add(workingPlace);
		}
		if (WEB != "") {
			MaltegoEntity webSite = new MaltegoEntity("maltego.Website");
			webSite.addField("fqdn", WEB);
			outputEntities.add(webSite);
		}
		
		return outputEntities;
				
	}
}
