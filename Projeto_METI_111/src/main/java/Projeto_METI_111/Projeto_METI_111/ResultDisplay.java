package Projeto_METI_111.Projeto_METI_111;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ResultDisplay {
	
	private JTable table;
	private JScrollPane sp;
	private JFrame frame;
	private String [] columnTitles;
	private String [] [] data;
	private String string;
	private ArrayList<Regra> regras = new ArrayList<Regra>();
	private ArrayList<Record> records = new ArrayList<Record>();
	
	/**
	 * O que pode dar jeito?
	 * ->Saber o número de Methods
	 * ->Saber o número de defeitos em comparação ao nosso is_long_method
	 * ->Saber o número de defeitos em comparação ao nosso is_feature_envy
	 * @throws IOException 
	 * 
	 */
	
	public ResultDisplay(String string, ArrayList<Regra> regras, ArrayList<Record> records) throws IOException {
		setTitles();
		setData(string);
		addFrameContent();
		setFrameVisible();
		this.string=string;
		this.regras=regras;
		this.records=records;
		for(int i=5;i!=9;i++) {
			for(int j=0;j!=records.size();j++) {
				table.setValueAt("FALSE", j, i);
			}
		}
		analizarMetricas();
	}
	
	private void analizarMetricas() {
		for(int i=0;i!=regras.size();i++) {
			for(int j=0;j!=records.size();j++) {
				String [] vetor_metricas = regras.get(i).getVetor_metricas();
				if(occurrences(regras.get(i).getMetricas())==2) {
					verificar(records.get(j), vetor_metricas[0], regras.get(i).getEntao(),regras.get(i).getSenao());
				}else {
					if(regras.get(i).getOp().equals("AND")) {
						for(int k=0;k!=vetor_metricas.length-1;k++) {
							//verificarAND(records.get(j), vetor_metricas[k],vetor_metricas[k+1],regras.get(i).getEntao(),regras.get(i).getSenao());
						}
					}
					if(regras.get(i).getOp().equals("OR")) {
						for(int k=0;k!=vetor_metricas.length-1;k++) {
							//verificarOR(records.get(j), vetor_metricas[k],vetor_metricas[k+1],regras.get(i).getEntao(),regras.get(i).getSenao());
						}
					}
				}
			}
		}
	}

	private void verificar(Record record, String string2, String entao, String senao) {
		String [] tokens1 = string2.split("<|\\>");
		double d1 = Double.parseDouble(tokens1[1]);
		if((tokens1[0].equals("LAA"))) {
			if(string2.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))<d1)) {
					setVal(((int) Double.parseDouble(record.methodID)-1),entao);					
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1),senao);
				}
			}
			if(string2.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))>d1)) {
					setVal(((int) Double.parseDouble(record.methodID)-1),entao);					
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1),senao);
				}
			}
		}
		if((tokens1[0].equals("LOC"))) {
			if(string2.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))<d1)) {
					setVal(((int) Double.parseDouble(record.methodID)-1),entao);					
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1),senao);
				}
			}
			if(string2.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))>d1)) {
					setVal(((int) Double.parseDouble(record.methodID)-1),entao);					
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1),senao);
				}
			}
		}
		if((tokens1[0].equals("CYCLO"))) {
			if(string2.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)) {
					setVal(((int) Double.parseDouble(record.methodID)-1),entao);					
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1),senao);
				}
			}
			if(string2.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)) {
					setVal(((int) Double.parseDouble(record.methodID)-1),entao);					
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1),senao);
				}
			}
		}
		if((tokens1[0].equals("ATFD"))) {
			if(string2.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)) {
					setVal(((int) Double.parseDouble(record.methodID)-1),entao);					
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1),senao);
				}
			}
			if(string2.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)) {
					setVal(((int) Double.parseDouble(record.methodID)-1),entao);					
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1),senao);
				}
			}
		}
	}

	private void setVal(int i, String aux) {
		if(aux.contains("long_method")&&aux.contains("TRUE")) {
			table.setValueAt("TRUE", i, 5);
		}
		if(aux.contains("long_method")&&aux.contains("FALSE")) {
			table.setValueAt("FALSE", i, 5);
		}
		if(aux.contains("feature_envy")&&aux.contains("TRUE")) {
			table.setValueAt("TRUE", i, 6);
		}
		if(aux.contains("feature_envy")&&aux.contains("FALSE")) {
			table.setValueAt("FALSE", i, 6);
		}
		
	}

	private int occurrences(String metricas) {
		int count = 0;
		for(int i=0;i!=metricas.length();i++) {
			if(metricas.charAt(i)==' ') {
				count++;
			}
		}
		return count;
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
	public void setData(String string2) throws IOException {
		ExcelDisplay teste = new ExcelDisplay(new ReadExcel (new File(string2)));
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
	

}
