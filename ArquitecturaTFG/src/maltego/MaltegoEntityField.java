/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maltego;

/**
 *
 * @author Félix Gómez
 */
public class MaltegoEntityField {
    
    private String fieldName; 
    private String displayName; 
    private String matchingRule; 
    private String value;

    public MaltegoEntityField(String fieldName, String displayName, String matchingRule, String value) {
        this.fieldName = fieldName;
        this.displayName = displayName;
        this.matchingRule = matchingRule;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMatchingRule() {
        return matchingRule;
    }

    public String getValue() {
        return value;
    }
}