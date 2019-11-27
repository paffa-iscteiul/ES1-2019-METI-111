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
	
	
	/**
	 * Construtor da classe
	 * Recebe o Ficheiro Excel e a partir deste conta o n� de linhas e colunas de modo a
	 * criar a matrix de informa��o que posteriormente ir� ser introduzida no ExcelDisplay.java
	 * @param excelF
	 * @throws IOException
	 */
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
	/**
	 * ReadFile() � uma fun��o que armazena toda a informa��o do ficheiro Excel
	 * nas listas de informa��o
	 * @throws IOException 
	 */
	public void ReadFile() throws IOException {
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
	
	/**
	 * getColumnNames() Ir� buscar toda a informa��o sobre os Titulos e 
	 * converter de ArrayList para Vector
	 * @return columnVector, s�o os t�tulos do excel, 1� Linha
	 */
	public String [] getColumnNames() {
		String [] columnVector = new String [columnCount];
		for(int i = 0; i < columnCount; i++) {
			columnVector [i] = columnNames.get(i);
		}
	return columnVector;
	}
	
	/**
	 * getData() Ir� buscar toda a informa��o, sem contar com os titulos.
	 * Para al�m de converter Arraylist em Matriz
	 * @return Matriz de Data do Excel
	 */
	public String [] [] getData(){
		String [] [] dataMatrix = new String [rowCount] [columnCount];
		int index = 0;
		for(int i = 0; i < (rowCount); i++) {
			for(int j = 0; j < columnCount; j++) {
				dataMatrix [i] [j] = this.data.get(index);
				index++;
			}
		}
		return dataMatrix;
	}
	
	public ArrayList<Record> getRecord() {
		return records;
	}
	
	
}
