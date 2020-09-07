package transformada;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aplicacion.WebForm;
import maltego.MaltegoEntity;

public class Ciudad extends WebForm{

	private final String letra = "^[a-zA-Z].*";
	ArrayList<String> lista;
	
	public Ciudad() {
		super("https://es.wikipedia.org/wiki/Wikipedia:Portada");
		cuadroDialogo = "//input[@name='search']";
		botonBusqueda = "//input[@name='go']";
	}
	

	protected void getValues(String recurso) {
		
		Pattern patron1 = Pattern.compile(letra);
		Matcher matcher1;
		
		String aux = recurso.toString();
		aux = aux.substring(aux.indexOf("<td><i><b><a href=\""));		
		
		aux = aux.replaceAll("<.*?>", " ");
		aux = aux.replaceAll("\\s{2,}", "\n");	
		
		String array[] = aux.split("\n", 0);
		lista = new ArrayList<String>();
		for (String string : array) {
			lista.add(string);
		}
		
		for (int i=0; i<lista.size(); i++) {
			matcher1 = patron1.matcher(lista.get(i));
			if (!matcher1.matches()) {
				lista.remove(i);
				i--;
			}
		}	
	}
	protected Collection<MaltegoEntity> generateOutputEntities(){			
			for (String string : lista) {
				MaltegoEntity lugar = new MaltegoEntity("maltego.Location",string);
				outputEntities.add(lugar);
			}
			return outputEntities;	
	}
}
