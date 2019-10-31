import java.io.File;
import javax.swing.JFileChooser;


public class Openfile {
	private Interface1 i;
	public static File Excelfile;
	JFileChooser escolherficheiro = new JFileChooser();
	
	public Openfile (Interface1 i) {
		this.i=i;
	}
	
	public void Browser() throws Exception{
		if(escolherficheiro.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			
			//agora vamos buscar o ficheiro 
			java.io.File file = escolherficheiro.getSelectedFile();
			Excelfile = new File(escolherficheiro.getSelectedFile().toString());
			//irei realizar aqui a leitura do ficheiro Excel para uma estrutura de dados (Pedro)
			readExcel readExcel = new readExcel(Excelfile);
			System.out.println("O ficheiro escolhido encontra-se na diretoria: " + Excelfile.getPath());
			i.fileConfirmed(file.getName());
			//com isto vi que funciona quando se fizer leitura do ficheiro é favor apagar a linha a cima
		}
			else {
				System.out.println("Não selecionaram nenhum ficheiro");
			}
			
		}
}

