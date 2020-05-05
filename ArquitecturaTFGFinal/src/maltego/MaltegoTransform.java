/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maltego;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import maltego.MaltegoEntity;

/**
 *
 * @author Félix Gómez
 */
public class MaltegoTransform {

    private List<MaltegoEntity> listEntities = new ArrayList<MaltegoEntity>();

    public void addEntity(MaltegoEntity entity) {
        this.listEntities.add(entity);
    }

    public void returnOutput() {
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document xmlDocument = documentBuilder.newDocument();
            Element maltegoMessage = xmlDocument.createElement("MaltegoMessage");
            xmlDocument.appendChild(maltegoMessage);

            Element maltegoResponse = xmlDocument.createElement("MaltegoTransformResponseMessage");
            maltegoMessage.appendChild(maltegoResponse);

            Element Entities = xmlDocument.createElement("Entities");
            maltegoResponse.appendChild(Entities);

            for (MaltegoEntity maltegoEntity : listEntities) {

                Element entity = xmlDocument.createElement("Entity");
                entity.setAttribute("Type", maltegoEntity.getEntityType());
                Entities.appendChild(entity);

                Element value = xmlDocument.createElement("Value");
                value.appendChild(xmlDocument.createTextNode(maltegoEntity.getEntityValue()));
                entity.appendChild(value);

                Element weight = xmlDocument.createElement("Weight");
                weight.appendChild(xmlDocument.createTextNode(String.valueOf(maltegoEntity.getWeight())));
                entity.appendChild(weight);

                if (maltegoEntity.numAdditionalFields() > 0) {
                    Element additionalFields = xmlDocument.createElement("AdditionalFields");
                    for (MaltegoEntityField additionalField : maltegoEntity.getAdditionalFields()) {
                        Element field = xmlDocument.createElement("Field");
                        field.setAttribute("Name", additionalField.getFieldName());
                        field.setAttribute("DisplayName", additionalField.getDisplayName());
                        field.setAttribute("MatchingRule", additionalField.getMatchingRule());
                        field.appendChild(xmlDocument.createTextNode(additionalField.getValue()));
                        additionalFields.appendChild(field);
                    }

                    entity.appendChild(additionalFields);

                }
                if (maltegoEntity.hasIcon()) {
                    Element iconUrl = xmlDocument.createElement("IconURL");
                    iconUrl.appendChild(xmlDocument.createTextNode(maltegoEntity.getIconURL()));
                    Entities.appendChild(iconUrl);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(xmlDocument);
            StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
}
