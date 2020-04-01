/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformadas;

import java.util.Collection;
import java.util.HashMap;

import org.openqa.selenium.*;

import aplicacion.Directory;
import client.WebServiceClient;
import maltego.MaltegoEntity;
import maltego.MaltegoTransform;

/**
 *
 * @author Félix Gómez
 */
public class UMDirectory extends Directory{

	private final String AREA = "Area de Conocimiento:";
    private final String CENTRO = "Centro:";
    private final String DESPACHO = "Despacho:";
    private final String DIRECCION = "Direccion postal:";
    private final String FILIACION = "Filiacion:";
    private final String NOMBRE = "Nombre:";
    private final String PUESTO = "Puesto:";
    private final String TELEFONO = "Telefono:";
    private final String UNIDAD = "Unidad Organizativa:";

    
    public UMDirectory(MaltegoEntity inputEntity) {
		super(inputEntity);
		source = "https://www.um.es/atica/directorio/";
	}
    
    public MaltegoTransform generateMaltegoTransform() {
        MaltegoTransform mt = new MaltegoTransform();
        
        htmlDoc = htmlDoc.substring(htmlDoc.indexOf("infoElem")+84);
        String table = htmlDoc.substring(0, htmlDoc.indexOf("</table>"));
        
        HashMap<String,String> parsedTable = parseTable(table);
        if (parsedTable.keySet().contains(NOMBRE)) {
            MaltegoEntity person = new MaltegoEntity("maltego.Person", parsedTable.get(NOMBRE));
            if (parsedTable.keySet().contains(PUESTO)) {
                person.addField("#position", "Position", parsedTable.get(PUESTO));
            }
            if (parsedTable.keySet().contains(FILIACION)) {
                person.addField("#affiliation", "Affiliation", parsedTable.get(FILIACION));
            }
            if (parsedTable.keySet().contains(AREA)) {
                person.addField("#area", "Area", parsedTable.get(AREA));
            }
            mt.addEntity(person);
        }
        
        if (parsedTable.keySet().contains(TELEFONO)) {
            MaltegoEntity phone = new MaltegoEntity("maltego.PhoneNumber", parsedTable.get(TELEFONO));
            mt.addEntity(phone);
        }

        if (parsedTable.keySet().contains(DESPACHO)) {
            MaltegoEntity office = new MaltegoEntity("maltego.Office", parsedTable.get(DESPACHO));
            mt.addEntity(office);
        }
        
        if (parsedTable.keySet().contains(UNIDAD)) {
            MaltegoEntity workingPlace = new MaltegoEntity("maltego.EducationInstitution", parsedTable.get(UNIDAD));
            mt.addEntity(workingPlace);
        }
        
        if (parsedTable.keySet().contains(CENTRO) || parsedTable.keySet().contains(DIRECCION)) {
            MaltegoEntity workingPlace = new MaltegoEntity("maltego.EducationInstitution");
            if (parsedTable.keySet().contains(CENTRO)) {
                workingPlace.setEntityValue(parsedTable.get(CENTRO));
            }
            if (parsedTable.keySet().contains(DIRECCION)) {
                workingPlace.addField("address", "Address", parsedTable.get(DIRECCION));
            }
            mt.addEntity(workingPlace);
        }
        
        mt.addEntity(new MaltegoEntity("maltego.EducationInstitution", "Universidad de Murcia"));
        
        /*for (String key : parsedTable.keySet()) {
            System.out.println("'"+key+"'");
            System.out.println(parsedTable.get(key)+"\n");
            MaltegoEntity person = new MaltegoEntity("maltego.Person", parsedTable.get(key));
            mt.addEntity(person);
        }*/
        return mt;
    }
    
    private HashMap<String,String> parseTable(String table) {
        HashMap<String,String> parsedTable = new HashMap<String,String>();
        do {
            String row = table.substring(table.indexOf("<tr>")+4, table.indexOf("</tr>"));
            if (!row.contains("class='centra'") && !row.contains("correo")) {
                String key = row.substring(row.indexOf('>')+1, row.indexOf("</td>"));
                row = row.substring(row.indexOf("</td>")+5);
                String value = removeTags(row.substring(row.indexOf('>')+1, row.indexOf("</td>")));
                parsedTable.put(key, value);
            }
            table = table.substring(table.indexOf("</tr>")+5);
        } while (table.contains("<tr>"));
        
        return parsedTable;
    }
    
    private String removeTags(String element) {
        while (element.contains("<")) {
            element = element.substring(0, element.indexOf('<')) + " " + element.substring(element.indexOf('>')+1);
        }
        return element;
    }

	@Override
	protected Collection<MaltegoEntity> getOutputEntities(MaltegoEntity inputEntity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void askForResource(WebServiceClient cliente) {
		chrome = (WebDriver) cliente.getResource();
		chrome.get(source);
		
		chrome.findElement(By.xpath("//input[@name=\"texto\"]")).sendKeys(inputEntity.getEntityValue());
		chrome.findElement(By.xpath("//input[@name=\"buscar\"]")).sendKeys(Keys.ENTER);
		
		htmlDoc = chrome.getPageSource();
	}
}