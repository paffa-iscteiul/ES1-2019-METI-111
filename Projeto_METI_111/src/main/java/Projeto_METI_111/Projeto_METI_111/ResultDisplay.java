package Projeto_METI_111.Projeto_METI_111;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

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
	 * ->Saber o n�mero de Methods
	 * ->Saber o n�mero de defeitos em compara��o ao nosso is_long_method
	 * ->Saber o n�mero de defeitos em compara��o ao nosso is_feature_envy
	 * @param ficheiro
	 * @param regras
	 * @param records
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
		rulesResults();
	}
	
	private void analizarMetricas() {
		for(int i=0;i!=regras.size();i++) {
			for(int j=0;j!=records.size();j++) {
				String [] vetor_metricas = regras.get(i).getVetor_metricas();
				if(occurrences(regras.get(i).getMetricas())==2) {
					if(aux(regras.get(i),regras)==true) {
						verificar(records.get(j), vetor_metricas[0], regras.get(i).getEntao(),regras.get(i).getSenao());
					}
				}else {
					if(regras.get(i).getOp().equals("AND")) {
						for(int k=0;k!=vetor_metricas.length-1;k++) {
							verificarAND(records.get(j), vetor_metricas[k],vetor_metricas[k+1],regras.get(i).getEntao(),regras.get(i).getSenao());
						}
					}
					if(regras.get(i).getOp().equals("OR")) {
						for(int k=0;k!=vetor_metricas.length-1;k++) {
							verificarOR(records.get(j), vetor_metricas[k],vetor_metricas[k+1],regras.get(i).getEntao(),regras.get(i).getSenao());
						}
					}
				}
			}
		}
	}

	private boolean aux(Regra regra, ArrayList<Regra> regras2) {
		int c=0;
		String regra1 = regra.getMetricas().replaceAll(" ", "");
		String [] s = regra1.split("<|\\>");
		String [] s1 = regra1.split(s[1]);
		String [] s2 = s1[0].split(s[0]);
		String simbolo = s1[0].substring(s1[0].length()-1);
		for(int i=0;i!=regras2.size();i++) {
			String regra2 = regras2.get(i).getMetricas().replaceAll(" ", "");
			String [] s3 = regra2.split("<|\\>");
			String [] s4 = regra2.split(s3[1]);
			String [] s5 = s4[0].split(s3[0]);
			String simboloRegra = s4[0].substring(s4[0].length()-1);
			if(regras2.get(i).getEntao().contains(regra.getEntao())&&regras2.get(i).getSenao().contains(regra.getSenao())&&regras2.get(i).getMetricas().contains(s[0])&&simbolo.contains(simboloRegra)) {
				if(simboloRegra.equals("<")) {
					String [] s7 = regras.get(i).getMetricas().split("<|\\>");
					if((Double.parseDouble(s7[1].replaceAll(" ", "")))<(Double.parseDouble(s[1]))) {
						c++;
					}
				}else {
					String [] s7 = regras.get(i).getMetricas().split("<|\\>");
					if((Double.parseDouble(s7[1].replaceAll(" ", "")))>(Double.parseDouble(s[1]))) {
						c++;
					}
				}
			}
		}
		if(c==0) {
			return true;
		}
		else {
			return false;
		}
	}

	private void verificarOR(Record record, String string2, String string3, String entao, String senao) {
		String [] tokens1 = string2.split("<|\\>");
		String [] tokens2 = string3.split("<|\\>");
		double d1 = Double.parseDouble(tokens1[1]);
		double d2 = Double.parseDouble(tokens2[1]);
		if((tokens1[0].equals("LAA")) && (tokens2[0].equals("LAA"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))<d1)||((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))<d1)||((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))>d1)||((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))>d1)||((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LAA")) && (tokens2[0].equals("LOC"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))<d1)||((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))<d1)||((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))>d1)||((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))>d1)||((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LAA")) && (tokens2[0].equals("CYCLO"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))<d1)||((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))<d1)||((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))>d1)||((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))>d1)||((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LAA")) && (tokens2[0].equals("ATFD"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))<d1)||((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))<d1)||((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))>d1)||((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))>d1)||((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LOC")) && (tokens2[0].equals("LAA"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))<d1)||((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))<d1)||((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))>d1)||((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))>d1)||((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LOC")) && (tokens2[0].equals("LOC"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))<d1)||((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))<d1)||((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))>d1)||((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))>d1)||((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LOC")) && (tokens2[0].equals("CYCLO"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))<d1)||((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))<d1)||((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))>d1)||((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))>d1)||((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LOC")) && (tokens2[0].equals("ATFD"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))<d1)||((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))<d1)||((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))>d1)||((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))>d1)||((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("CYCLO")) && (tokens2[0].equals("LAA"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)||((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)||((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)||((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)||((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("CYCLO")) && (tokens2[0].equals("LOC"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)||((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)||((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)||((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)||((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("CYCLO")) && (tokens2[0].equals("CYCLO"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)||((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)||((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)||((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)||((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("CYCLO")) && (tokens2[0].equals("ATFD"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)||((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)||((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)||((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)||((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("ATFD")) && (tokens2[0].equals("LAA"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)||((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)||((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)||((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)||((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("ATFD")) && (tokens2[0].equals("LOC"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)||((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)||((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)||((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)||((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("ATFD")) && (tokens2[0].equals("CYCLO"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)||((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)||((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)||((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)||((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("ATFD")) && (tokens2[0].equals("ATFD"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)||((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)||((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)||((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)||((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		
	}

	private void verificarAND(Record record, String string2, String string3, String entao, String senao) {
		String [] tokens1 = string2.split("<|\\>");
		String [] tokens2 = string3.split("<|\\>");
		double d1 = Double.parseDouble(tokens1[1]);
		double d2 = Double.parseDouble(tokens2[1]);
		if((tokens1[0].equals("LAA")) && (tokens2[0].equals("LAA"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))<d1)&&((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))<d1)&&((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))>d1)&&((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))>d1)&&((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LAA")) && (tokens2[0].equals("LOC"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))<d1)&&((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))<d1)&&((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))>d1)&&((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))>d1)&&((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LAA")) && (tokens2[0].equals("CYCLO"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))<d1)&&((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))<d1)&&((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))>d1)&&((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))>d1)&&((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LAA")) && (tokens2[0].equals("ATFD"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))<d1)&&((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))<d1)&&((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLaa()))>d1)&&((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLaa()))>d1)&&((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LOC")) && (tokens2[0].equals("LAA"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))<d1)&&((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))<d1)&&((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))>d1)&&((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))>d1)&&((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LOC")) && (tokens2[0].equals("LOC"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))<d1)&&((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))<d1)&&((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))>d1)&&((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))>d1)&&((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LOC")) && (tokens2[0].equals("CYCLO"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))<d1)&&((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))<d1)&&((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))>d1)&&((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))>d1)&&((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("LOC")) && (tokens2[0].equals("ATFD"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))<d1)&&((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))<d1)&&((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getLoc()))>d1)&&((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getLoc()))>d1)&&((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("CYCLO")) && (tokens2[0].equals("LAA"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)&&((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)&&((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)&&((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)&&((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("CYCLO")) && (tokens2[0].equals("LOC"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)&&((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)&&((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)&&((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)&&((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("CYCLO")) && (tokens2[0].equals("CYCLO"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)&&((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)&&((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)&&((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)&&((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("CYCLO")) && (tokens2[0].equals("ATFD"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)&&((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))<d1)&&((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)&&((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getCyclo()))>d1)&&((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("ATFD")) && (tokens2[0].equals("LAA"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)&&((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)&&((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)&&((Double.parseDouble(record.getLaa()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)&&((Double.parseDouble(record.getLaa()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("ATFD")) && (tokens2[0].equals("LOC"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)&&((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)&&((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)&&((Double.parseDouble(record.getLoc()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)&&((Double.parseDouble(record.getLoc()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("ATFD")) && (tokens2[0].equals("CYCLO"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)&&((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)&&((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)&&((Double.parseDouble(record.getCyclo()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)&&((Double.parseDouble(record.getCyclo()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
		}
		if((tokens1[0].equals("ATFD")) && (tokens2[0].equals("ATFD"))) {
			if(string2.contains("<")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)&&((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains("<")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))<d1)&&((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains("<")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)&&((Double.parseDouble(record.getAtfd()))<d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
				}
			}
			if(string2.contains(">")&&string3.contains(">")) {
				if(((Double.parseDouble(record.getAtfd()))>d1)&&((Double.parseDouble(record.getAtfd()))>d2)) {
					setVal(((int) Double.parseDouble(record.methodID)-1), entao);
				}else {
					setVal(((int) Double.parseDouble(record.methodID)-1), senao);
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
	 * Formata a parte dos t�tulos/nome das colunas
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
	 * Vai buscar informa��o necess�ria ao j� formado display
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
	
	public void rulesResults() {

		String [] [] aux = data;
		
		for(int linha = 0; linha < aux.length; linha++) {	
			String valor1 = (String) table.getValueAt(linha,1);
			String valor2 = (String) table.getValueAt(linha,5);
			
			if( valor1.equals(valor2)) {
				table.setValueAt("FALSE", linha, 7);
			} else {
				table.setValueAt("TRUE", linha, 7);
			}
		
			
			String valor3 = (String) table.getValueAt(linha,4);
			String valor4 = (String) table.getValueAt(linha,6);
			
			if( valor3.equals(valor4)) {
				table.setValueAt("FALSE", linha, 8);
			} else {
				table.setValueAt("TRUE", linha, 8);
			}
		}
	}
	
	public void comparatorsResults() { //contadores para os defeitos 
			
		String [] [] aux1 = data;
		int countDCI=0; //iPlasma
		int countDII=0; 
		int countADII=0;
		int countADCI=0;
		
		int countDCI1=0; //PMD
		int countDII1=0;
		int countADII1=0;
		int countADCI1=0;
		
		int countDCI2=0;  //ES_is_long_method
		int countDII2=0;
		int countADII2=0;
		int countADCI2=0;

		int countDCI3=0; //ES_is_feature_envy
		int countDII3=0;
		int countADII3=0;
		int countADCI3=0;

		for(int linha=0; linha<aux1.length;linha++) {
			String valorILM = (String) table.getValueAt(linha, 1); //is_long_method
			String valoriPlasma = (String) table.getValueAt(linha, 2); //iPlasma
			String valorPMD = (String) table.getValueAt(linha, 3); //PMD
			String valorESILM = (String) table.getValueAt(linha, 5); //ES_is_longMethod
			String valorESIFE = (String) table.getValueAt(linha, 6); //ES_is_longMethod
			
			
			
			
			if(valorILM.equals("TRUE")) {							//iPlasma
				if(valoriPlasma.equals("TRUE")) { //Def.corretos
					countDCI++;
				}else {
					countADII++; //ausencias de def.corretos
				}
				
				if(valorPMD.equals("TRUE")) { //Def.corretos 		//PMD
					countDCI1++;
				}else {
					countADII1++; //ausencias de def.corretos
				}
				
				if(valorESILM.equals("TRUE")) { //Def.corretos 		//ES_is_long_method
					countDCI2++;
				}else {
					countADII2++; //ausencias de def.corretos
				}
				if(valorESIFE.equals("TRUE")) { //Def.corretos 		//ES_is_feature_envy
					countDCI3++;
				}else {
					countADII3++; //ausencias de def.corretos
				}
							
			}
			
			if(valorILM.equals("FALSE")) { 
				if(valoriPlasma.equals("TRUE")){ 				//iPlasma
					countDII++; //defeitos incorretos
				}else {
					countADCI++; //aus.def.incorr
				}
				
				if(valorPMD.equals("TRUE")){ 						//PMD
					countDII1++; //defeitos incorretos
				}else {
					countADCI1++; //aus.def.incorr
				}
				
				if(valorESILM.equals("TRUE")){ 						//ES_is_long_method
					countDII2++; //defeitos incorretos
				}else {
					countADCI2++; //aus.def.incorr
				}	
				if(valorESIFE.equals("TRUE")){ 						//ES_is_feature_envy
					countDII3++; //defeitos incorretos
				}else {
					countADCI3++; //aus.def.incorr
				}	
			}
			

		}
		
	}
	
}
	
	
