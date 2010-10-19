package no.helsebiblioteket.plugin;

import java.util.ArrayList;

/**
 * Simple class for holding information about the Categories its fields
 */
public class Category {
    private int categoryKey;
    private ArrayList<String> isHtmlArea;
    private ArrayList<String> fields;

    public Category(int categoryKey){
        this.categoryKey = categoryKey;
        this.fields = new ArrayList<String>();
        this.isHtmlArea = new ArrayList<String>();
     }

    public Category(int categoryKey, ArrayList<String> fieldArray){
        this.categoryKey = categoryKey;
        this.fields = new ArrayList<String>();
        for(String s : fieldArray){
            this.fields.add(s);
        }
        this.isHtmlArea = new ArrayList<String>();
    }
    public Category(int categoryKey, ArrayList<String> fieldArray, ArrayList<String> isHTMLArray){
        this.categoryKey = categoryKey;
        this.fields = fieldArray;
        this.isHtmlArea = isHTMLArray;
    }

    public int getCategoryKey(){
        return this.categoryKey;
    }

    public void setCategoryKey(int categoryKey) {
        this.categoryKey = categoryKey;
    }

    public ArrayList<String> getFieldList(){
        return this.fields;
    }

    public void setFieldValue(String fieldName){
        this.fields.add(fieldName);
    }
    public void setIsHtmlArea(String trueFalse){
        this.isHtmlArea.add(trueFalse);
    }
    public ArrayList<String> getIsHtmlArea(){
        return this.isHtmlArea;
    }
}
