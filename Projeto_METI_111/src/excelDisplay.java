
import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class excelDisplay {

	private JTable j;
	private JScrollPane sp;
	private static excelDisplay INSTANCE = null;
	
	//Constructor
	public excelDisplay(readExcel excel) {		
		//Column Names
		String [] columnNames = excel.getColumnNames();
		
		//Excel Data
		String [] [] data = excel.getData();
		
		//Initializing the JTable
		j = new JTable(data, columnNames);
		j.setBounds(30, 40, 200, 300);
		
		//Adding it to JScrollPane
		sp = new JScrollPane(j);
	}
	
	
	public static excelDisplay startInstance(readExcel readExcel) {
		if(INSTANCE == null) {
	         INSTANCE = new excelDisplay(readExcel);
	      }
	      return INSTANCE;
	}
	
	public static excelDisplay getInstance() {
		return INSTANCE;
	}


	public JTable getJ() {
		return j;
	}


	public void setJ(JTable j) {
		this.j = j;
	}


	public JScrollPane getSp() {
		return sp;
	}


	public void setSp(JScrollPane sp) {
		this.sp = sp;
	}
	
	
	
	
}