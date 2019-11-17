package Projeto_METI_111.Projeto_METI_111;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	private ArrayList<Record> records=new ArrayList<Record>();
	private Openfile of;
	
	private FileInputStream fis;
	private int rowCount;
	private int columnCount;
	private ArrayList<String> columnNames;
	private ArrayList<String> data;
	private XSSFWorkbook workbook;
	private static ReadExcel INSTANCE = null;
	
	public ReadExcel(File excelF) throws IOException{
		fis = new FileInputStream(excelF);
		columnNames = new ArrayList<String>();
		data = new ArrayList<String>();
		workbook = new XSSFWorkbook(fis);
		XSSFSheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		Row nextRow = iterator.next();
		this.rowCount = ((XSSFSheet) firstSheet).getLastRowNum();
		this.columnCount = nextRow.getLastCellNum();
		ReadFile();
  }
	
	public void ReadFile() {
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIt = sheet.iterator();
		Boolean Titles = true;
		while(rowIt.hasNext()) {
			Row row = rowIt.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			if(Titles == true) {
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					columnNames.add(cell.toString());
				}
			}else {
				Record rec = new Record();
				int i=0;
				while (cellIterator.hasNext()) {
					i++;
					Cell cell = cellIterator.next();
					rec.add(i, cell.toString());
					data.add(cell.toString());
				}
				records.add(rec);
			}
			Titles = false;
		}
	}
	
	public String [] getColumnNames() {
		String [] columnVector = new String [columnCount];
		for(int i = 0; i < columnCount; i++) {
			columnVector [i] = columnNames.get(i);
		}
	return columnVector;
	}
	
	
	public String [] [] getData(){
		String [] [] dataMatrix = new String [rowCount-1] [columnCount];
		int index = 0;
		for(int i = 0; i < (rowCount-1); i++) {
			for(int j = 0; j < columnCount; j++) {
				dataMatrix [i] [j] = this.data.get(index);
				index++;
			}
		}
		return dataMatrix;
	}
	
	
}