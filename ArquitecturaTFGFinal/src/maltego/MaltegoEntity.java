/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maltego;

import java.util.ArrayList;
import java.util.List;

import maltego.MaltegoEntityField;

/**
 *
 * @author Félix Gómez
 */
public class MaltegoEntity {
    public static final String BOOKMARK_COLOR_NONE = "-1";
    public static final String BOOKMARK_COLOR_BLUE = "0";
    public static final String BOOKMARK_COLOR_GREEN = "1";
    public static final String BOOKMARK_COLOR_YELLOW = "2";
    public static final String BOOKMARK_COLOR_ORANGE = "3";
    public static final String BOOKMARK_COLOR_RED = "4";

    private List<MaltegoEntityField> additionalFields = new ArrayList<MaltegoEntityField>();
    private int weight = 100;
    private String iconURL = "";
    private String entityType;
    private String entityValue;

    public MaltegoEntity(String entityType, String entityValue) {
        this.entityType = entityType;
        this.entityValue = entityValue;
    }

    public MaltegoEntity(String entityType) {
        this.entityType = entityType;
        this.entityValue = "";
    }

    public void addField(String fieldName, String displayName, String matchingRule, String value) {
        MaltegoEntityField additionalField = new MaltegoEntityField(fieldName, displayName, matchingRule, value);
        this.additionalFields.add(additionalField);
    }

    public void addField(String fieldName, String displayName, String value) {
        this.addField(fieldName, displayName, "", value);
    }

    public void addField(String fieldName, String value) {
        this.addField(fieldName, "", "", value);
    }

    public int numAdditionalFields() {
        return this.additionalFields.size();
    }
    
    public boolean hasIcon() {
        return (iconURL != null) && (iconURL.length() > 0);
    }
    
    public String getEntityType() {
        return entityType;
    }

    public String getEntityValue() {
        return entityValue;
    }

    public int getWeight() {
        return weight;
    }
    
    public String getIconURL() {
        return iconURL;
    }

    public List<MaltegoEntityField> getAdditionalFields() {
        return additionalFields;
    }

    public void setEntityValue(String entityValue) {
        this.entityValue = entityValue;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    /** Set Link properties**/
    public void setLinkColor(String color) {
        this.addField("link#maltego.link.color", "LinkColor", "", color);
    }

    public void setLinkStyle(String style) {
        this.addField("link#maltego.link.color", "LinkColor", "", style);
    }

    public void setLinkThichkness(String thick) {
        this.addField("link#maltego.link.thickness", "Thickness", "", thick);
    }

    public void setLinkLabel(String Label) {
        this.addField("link#maltego.link.label", "Label", "", Label);
    }

    /** Set Bookmark **/
    public void setBookmark(String bookmark) {
        this.addField("bookmark#", "Bookmark", "strict", bookmark);
    }

    /** Set Note **/
    public void setNote(String Note) {
        this.addField("notes#", "Note", "", Note);
    }
}