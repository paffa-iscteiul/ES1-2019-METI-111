package Projeto_METI_111.Projeto_METI_111;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ResultDisplay {
	
	private JTable table;
	private JScrollPane sp;
	private JFrame frame;
	private String [] columnTitles;
	private String [] [] data;
	
	/**
	 * O que pode dar jeito?
	 * ->Saber o número de Methods
	 * ->Saber o número de defeitos em comparação ao nosso is_long_method
	 * ->Saber o número de defeitos em comparação ao nosso is_feature_envy
	 * @throws IOException 
	 * 
	 */
	
	public ResultDisplay() throws IOException {
		setTitles();
		setData();
		addFrameContent();
		setFrameVisible();
	}
	
	private void setFrameVisible() {
		frame = new JFrame ("Resultados");
		frame.setLayout(new BorderLayout());
		frame.add(sp);
		frame.pack();
		frame.setVisible(true);
		
	}

	/**
	 * Set do panel display
	 */
	
	public void addFrameContent() {
		//Initializing the JTable
		table = new JTable(data, columnTitles);
		table.setBounds(30, 40, 300, 600);
						
		//Adding it to JScrollPane
		sp = new JScrollPane(table);
	}
	
	/**
	 * Formata a parte dos títulos/nome das colunas
	 */
	public void setTitles() {
		columnTitles = new String[9];
		columnTitles[0] = "MethodID";
		columnTitles[1] = "is_long_Method";
		columnTitles[2] = "iPlasma";
		columnTitles[3] = "PMD";
		columnTitles[4] = "is_feature_envy";
		columnTitles[5] = "ES_long";
		columnTitles[6] = "ES_envy";
		columnTitles[7] = "Defeito is_long_Method";
		columnTitles[8] = "Defeito is_feature_envy";
	}
	
	/**
	 * Vai buscar informação necessária ao já formado display
	 * @throws IOException 
	 */
	public void setData() throws IOException {
		ExcelDisplay teste = new ExcelDisplay(new ReadExcel (new File("C:\\Users\\ES\\Downloads\\Long-Method.xlsx")));
		String [] [] aux = teste.getData();
		data = new String [aux.length] [columnTitles.length];
		for(int linha = 0; linha < aux.length; linha++) {				//this equals to the row in our matrix
			for(int coluna = 0; coluna < aux[0].length; coluna++) {				//this equals to the column in each row
				switch (coluna) {
				case 0: data[linha][coluna] = aux [linha][coluna];				//MethodID 			 / Em uso
					break;
				case 1: 														//Package 			 / Sem uso
					break;
				case 2: 														//Class 			 / Sem uso
					break;
				case 3: 														//method			 / Sem uso
					break;
				case 4: 														//LOC 				 / Sem uso
					break;
				case 5: 														//CYCLO 			 / Sem uso
					break;
				case 6: 														//ATFD				 / Sem uso
					break;
				case 7: 														//LAA 				 / Sem uso
					break;
				case 8: data[linha][1] = aux [linha][coluna];					//is_long_method 	 / Em uso
					break;
				case 9: data[linha][2] = aux [linha][coluna];					//iPlasma			 / Em uso
					break;
				case 10: data[linha][3] = aux [linha][coluna];					//PMD				 / Em uso
					break;
				case 11: data[linha][4] = aux [linha][coluna];					//is_feature_envy	 / Em uso
					break;
				default:
					break;
				}
				
			
			}
		}
		
	}
	
	
	
	
	
	

	public static void main(String[] args) throws IOException {
		
		ResultDisplay teste2 = new ResultDisplay();
		
	}

}
