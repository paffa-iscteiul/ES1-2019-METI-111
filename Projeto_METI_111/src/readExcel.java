import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcel {
	
	public readExcel(File excelF) {
		try {
			FileInputStream fis=new FileInputStream(excelF);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet st = wb.getSheetAt(0);
			Iterator <Row> row = st.iterator();
			while(row.hasNext()) {
				Row r = row.next();
				Iterator<Cell> cel = r.cellIterator();
				int i = 0;
				while(cel.hasNext()) {
					i++;
					Cell cell = cel.next();
					//cell.getRow();
					if(cell.getRowIndex()==i) {
						System.out.println("Linha " + cell.getRowIndex() + ": " + cell.toString());
					}
					//System.out.println(cell.toString());
				}
			}
			wb.close();
			fis.close();
		} catch(Exception e) {
			
		}
	}
}
