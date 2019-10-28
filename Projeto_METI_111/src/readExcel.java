import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcel {
	private ArrayList<record> records=new ArrayList<record>();
	private Openfile of;
	
	public readExcel(File excelF, Openfile of) {
		try {
			this.of=of;
			FileInputStream fis=new FileInputStream(excelF);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet st = wb.getSheetAt(0);
			Iterator <Row> row = st.iterator();
			while(row.hasNext()) {
				Row r = row.next();
				Iterator<Cell> cel = r.cellIterator();
				record rec=new record();
				int i = 0;
				while(cel.hasNext()) {
					i++;
					Cell cell = cel.next();
					rec.add(i, cell.toString());
				}
				records.add(rec);
			}
			wb.close();
			fis.close();
			//As seguintes 3 linhas serão eliminadas futuramente pelo Fábio
			for(int j=0;j!=records.size();j++) {
				System.out.println(records.get(j).getRegisto());
			}
			} catch(Exception e) {
			
		}
	}
}
