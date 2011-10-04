package no.helsebiblioteket.plugin;

import java.util.ArrayList;

/**
 * Simple class for holding information about the Categories its fields
 */
public class Category {
    private int key;
    private ArrayList<String> fields = new ArrayList<String>();
    private ArrayList<Boolean> isHtmlArea = new ArrayList<Boolean>();

    public Category(){}

    public Category(int key, ArrayList<String> fields, ArrayList<Boolean> isHtmlArea){
        this.key = key;
        this.fields = fields;
        this.isHtmlArea = isHtmlArea;
    }

    public int getKey(){
        return this.key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public ArrayList<String> getFields(){
        return this.fields;
    }
    public ArrayList<Boolean> getIsHtmlArea(){
        return this.isHtmlArea;
    }
    
    public void setFieldValue(String fieldName){
        this.fields.add(fieldName);
    }
    public void setIsHtmlAreaValue(boolean htmlArea){
        this.isHtmlArea.add(htmlArea);
    }
}
