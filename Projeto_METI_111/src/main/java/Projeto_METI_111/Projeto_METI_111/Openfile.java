package Projeto_METI_111.Projeto_METI_111;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;


public class Openfile {
	public Interface1 i;
	public static File Excelfile;
	JFileChooser chooseFile = new JFileChooser();
	public ArrayList<Record> records = new ArrayList<Record>();
	
	/**
	 * Construtor da classe
	 * Recebe a interface 1 como atributo apenas para realizar a confirmacao se o ficheiro se encontra carregado
	 * @param Interface1
	 */
	public Openfile (Interface1 i) {
		this.i=i;
	}
	/**
	 * Browser() neste metodo � aberta a janela browser onde se procura o ficheiro e
	 * e tambem � realizada a leitura do ficheiro excel para uma estrutura de dados
	 * tamb�m se realiza a confrma��o do carregamento do ficheiro
	 */
	public void Browser() throws Exception{
		if(chooseFile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			
			//agora vamos buscar o ficheiro 
			java.io.File file = chooseFile.getSelectedFile();
			Excelfile = new File(chooseFile.getSelectedFile().toString());
			//irei realizar aqui a leitura do ficheiro Excel para uma estrutura de dados (Pedro)
			ReadExcel readExcel = new ReadExcel(Excelfile);
			records = readExcel.getRecord();
			ExcelDisplay.startInstance(readExcel);
			System.out.println("O ficheiro escolhido encontra-se na diretoria: " + Excelfile.getPath());
			i.fileConfirmed(file.getName(),chooseFile.getSelectedFile().toString());
			//com isto vi que funciona quando se fizer leitura do ficheiro � favor apagar a linha a cima
		}
			else {
				System.out.println("N�o selecionaram nenhum ficheiro");
			}
			
		}

	public ArrayList<Record> getRecords() {
		return records;
	}
}

