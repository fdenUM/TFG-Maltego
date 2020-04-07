/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformadas;

import java.util.ArrayList;
import java.util.Collection;

import org.openqa.selenium.*;

import aplicacion.Directory;
import client.WebServiceClient;
import maltego.MaltegoEntity;
import maltego.MaltegoTransform;

/**
 *
 * @author Francisco Denis
 */
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
    
    
    public UMDirectory(WebServiceClient cliente) {
		super(cliente);
		source = "https://www.um.es/atica/directorio/";
	}
    
	@Override
	protected Collection<MaltegoEntity> getOutputEntities(MaltegoEntity inputEntity) {
		
		this.inputEntity = inputEntity;
		mt = new MaltegoTransform();
		
		askForResource();
		getValues();
		return generateOutputEntities();
	}
	private void getValues() {
		String aux = htmlDoc;
		
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

	@Override
	protected void askForResource() {
		
		chrome.get(source);
		
		chrome.findElement(By.xpath("//input[@name=\"texto\"]")).sendKeys(inputEntity.getEntityValue());
		chrome.findElement(By.xpath("//input[@name=\"buscar\"]")).sendKeys(Keys.ENTER);
		
		htmlDoc = chrome.getPageSource();
		htmlDoc = htmlDoc.substring(htmlDoc.indexOf("urlsUnidades\">"));
	}
	
	private Collection<MaltegoEntity> generateOutputEntities(){
		
		outputEntities = new ArrayList<MaltegoEntity>();
		
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