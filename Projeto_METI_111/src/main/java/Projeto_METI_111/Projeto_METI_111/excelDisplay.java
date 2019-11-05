package Projeto_METI_111.Projeto_METI_111;


import javax.swing.JScrollPane;
import javax.swing.JTable;

public class excelDisplay {

	private JTable table;
	private JScrollPane sp;
	private static excelDisplay INSTANCE = null;
	
	//Constructor
	public excelDisplay(readExcel excel) {		
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