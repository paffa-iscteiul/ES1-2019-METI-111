package Projeto_METI_111.Projeto_METI_111;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ExcelDisplay {

	private JTable table;
	private JScrollPane sp;
	private static ExcelDisplay INSTANCE = null;
	
	//Constructor
	public ExcelDisplay(ReadExcel excel) {		
		//Column Names
		String [] columnNames = excel.getColumnNames();
		
		//Excel Data
		String [] [] data = excel.getData();
		
		//Initializing the JTable
		table = new JTable(data, columnNames);
		table.setBounds(30, 40, 200, 300);
		
		//Adding it to JScrollPane
		sp = new JScrollPane(table);
	}
	
	
	public static ExcelDisplay startInstance(ReadExcel readExcel) {
		if(INSTANCE == null) {
	         INSTANCE = new ExcelDisplay(readExcel);
	      }
	      return INSTANCE;
	}
	
	public static ExcelDisplay getInstance() {
		return INSTANCE;
	}


	public JTable getJ() {
		return table;
	}


	public void setJ(JTable j) {
		this.table = j;
	}


	public JScrollPane getSp() {
		return sp;
	}


	public void setSp(JScrollPane sp) {
		this.sp = sp;
	}
}