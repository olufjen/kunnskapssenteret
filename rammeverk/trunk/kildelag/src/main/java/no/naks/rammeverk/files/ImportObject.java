package no.naks.rammeverk.files;

import java.util.ArrayList;
import java.util.List;

/**
 
 * Denne klassen inneholder data fra import fra regneark
 * * @author olj
 */
public class ImportObject {
	private int noofColumns; // Antall kolonner i regnearket
	private List<String> nameColumns; // Navn på kolonnene i regnearket
	private List<String> contentColumns; //innholdet i hver kolonne i regnearket
	
	public ImportObject(int noofColumns) {
		super();
		this.noofColumns = noofColumns;
		nameColumns = new ArrayList();
		contentColumns = new ArrayList();
		
	}
	public int getNoofColumns() {
		return noofColumns;
	}
	public void setNoofColumns(int noofColumns) {
		this.noofColumns = noofColumns;
	}
	public List<String> getNameColumns() {
		return nameColumns;
	}
	public void setNameColumns(List<String> nameColumns) {
		this.nameColumns = nameColumns;
	}
	public List<String> getContentColumns() {
		return contentColumns;
	}
	public void setContentColumns(List<String> contentColumns) {
		this.contentColumns = contentColumns;
	}
	public void addtoNamecolumn(String val){
		nameColumns.add(val);
	}
	public void addtoContentcolumn(String val){
		contentColumns.add(val);
	}
	public String extractfromNamecolumn(int pos){
		return nameColumns.get(pos);
	}
	public String extractfromContentcolumn(int pos){
		return contentColumns.get(pos);
	}
	
}
