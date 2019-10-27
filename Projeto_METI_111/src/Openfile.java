import java.io.File;
import javax.swing.JFileChooser;


public class Openfile {

	public static File Ecxelfile;
	JFileChooser escolherficheiro = new JFileChooser();
	
	
	public void Browser() throws Exception{
		if(escolherficheiro.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			
			//agora vamos buscar o ficheiro 
			java.io.File file = escolherficheiro.getSelectedFile();
			Ecxelfile = new File(file.getPath());
		}
			else {
				System.out.println("nao selecionaram nenhum ficheiro");
			}
			
		}
	}

