package Projeto_METI_111.Projeto_METI_111;

import java.io.File;

import javax.swing.JFileChooser;


public class Openfile {
	private Interface1 i;
	public static File Excelfile;
	JFileChooser chooseFile = new JFileChooser();
	
	public Openfile (Interface1 i) {
		this.i=i;
	}
	
	public void Browser() throws Exception{
		if(chooseFile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			
			//agora vamos buscar o ficheiro 
			java.io.File file = chooseFile.getSelectedFile();
			Excelfile = new File(chooseFile.getSelectedFile().toString());
			//irei realizar aqui a leitura do ficheiro Excel para uma estrutura de dados (Pedro)
			readExcel readExcel = new readExcel(Excelfile);
			excelDisplay.startInstance(readExcel);
			System.out.println("O ficheiro escolhido encontra-se na diretoria: " + Excelfile.getPath());
			i.fileConfirmed(file.getName());
			//com isto vi que funciona quando se fizer leitura do ficheiro � favor apagar a linha a cima
		}
			else {
				System.out.println("N�o selecionaram nenhum ficheiro");
			}
			
		}
}

