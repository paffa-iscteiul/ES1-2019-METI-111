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

public class readExcel {
	private ArrayList<record> records=new ArrayList<record>();
	private Openfile of;
	
	private FileInputStream fis;
	private int rowCount;
	private int columnCount;
	private ArrayList<String> columnNames;
	private ArrayList<String> data;
	private XSSFWorkbook workbook;

	
	
	public readExcel(File excelF) {
	try {
		this.fis = new FileInputStream(excelF);
		columnNames = new ArrayList<String>();
		data = new ArrayList<String>();
		GetCount(excelF);
		ReadFile();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	}
	
	public void ReadFile() {
		XSSFSheet sheet = this.workbook.getSheetAt(0);
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
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					data.add(cell.toString());
				}
			}
			Titles = false;
		}
	}
	
	public void GetCount(File ExcelFile) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			this.workbook = workbook;
			XSSFSheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			Row nextRow = iterator.next();
			this.rowCount = ((XSSFSheet) firstSheet).getLastRowNum();
			this.columnCount = nextRow.getLastCellNum();
		} catch (IOException e) {
			e.printStackTrace();
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
