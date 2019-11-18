package Projeto_METI_111.Projeto_METI_111;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ExcelDisplay {

	private JTable table;
	private JScrollPane sp;
	private static ExcelDisplay INSTANCE = null;
	
	/**
	 * Inicializa todos os parametros do Display
	 * @param excel
	 */
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
	
	/**
	 * startInstance()
	 * getInstance()
	 * Foi criada uma instância de display para que o display 
	 * seja de fácil acesso e facilmente actualizavel
	 * @param readExcel
	 * @return Instância do Display
	 */
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