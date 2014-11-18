package no.naks.rammeverk.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Denne klassen håndterer import fra filer.
 * Filene inneholder deltakere som kan delta i en studie
 * 
 * @author olj
 * @version 0.9
 * Foreløpig håndterer den import fra excel
 */
public class FileImport {

	private Workbook workbook = null;
	
	public FileImport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	/**
	 * getRow
	 * Denne rutinen setter kolonneverdier i en rad i et regneark 
	 * sammen til en streng variabel
	 * @param row
	 * @param n
	 * @return
	 */
	private String getRow(Cell[]row,int n){
		String val = "";
		if (n > 0)
			return val + row[n].getContents() + getRow(row,n-1);
		if (n == 0)
			return val + row[n].getContents() ;
		return val;
	}
	public void createImport(String name,String type) {
		
		if (name.contains(".xls")){
		      try {
				Workbook work = Workbook.getWorkbook(new File(name));
			      Sheet xsheet = work.getSheet(0);
			      int nr = xsheet.getRows();
			//      String v1 = xsheet.getCell(0, 1).getContents();
			//      System.out.println(v1);
			      setWorkbook(work);
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * generateValues
	 * Denne rutinen lager en liste av Streng variabler.
	 * En slik streng variabel inneholder streng verdien av en rad
	 * i regnearket. Kun rader med numeriske verdier benyttes.  
	 * @param sn Regneark nummer 
	 * @return Liste over Streng varable
	 */
	public List<String> generateValues(int sn){
		List members = null;
		if (workbook != null){
			Sheet s = workbook.getSheet(sn);
			members = new ArrayList<String>();
		
			int noofColumns = s.getColumns();
			int noofRows = s.getRows();
			Cell[] row = new Cell[noofColumns];
			int rows = 0;
			for (int i = 0 ; i < noofRows ; i++){
				for (int j = 0;j < noofColumns;j++){
					Cell myCell = s.getCell(j,i);
					boolean labelFlag = false;
					if (myCell != null && myCell.getType() != CellType.EMPTY )
						labelFlag =  myCell.getType() == CellType.NUMBER;
					if (labelFlag ){
						row[rows] = myCell;
						rows++;
					}
				}
				if (row != null && row[0] != null)
					members.add(getRow(row, noofColumns-1));
				rows = 0;
			 }
		}
		return members;
	}
	/**
	 * importExcel
	 * Denne rutinen importerer fra et excel regneark som har navnet sheetName
	 * @param sheetName navn på regnearket
	 * @param noofColumns antall kolonner i regnearket
	 * @return et importobjekt som inneholder de importerte verdiene og navn på kolonnene
	 */
	public ImportObject importExcel(String sheetName, int noofColumns){
		ImportObject impObject = new ImportObject(noofColumns);
		if (workbook != null){
			Sheet s = workbook.getSheet(sheetName);
			int localColumns = s.getColumns();
			int noofRows = s.getRows();
			Cell[] row = new Cell[noofColumns];
			int rows = 0;
			for (int i = 0 ; i < noofRows ; i++){
				for (int j = 0;j < noofColumns;j++){
					Cell myCell = s.getCell(j,i);
					
					if (myCell != null && myCell.getType() != CellType.EMPTY ){
				
						row[rows] = myCell;
						if (i != 0)
							impObject.addtoContentcolumn(row[rows].getContents());
						else
							impObject.addtoNamecolumn(row[rows].getContents());
						rows++;
						
					}
				}
				rows = 0;
			 }
			
			
			
		}
		
		return impObject;
	}
}
