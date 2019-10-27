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
			
			
			//leitura do ficheiro deve ser chamada aqui 
			//sendo que a parte da ler o ficheiro ecxel nao me pertence para ver se relanet o ficheiro é carregado vou imprimir o path dele na consola 
			System.out.println(Ecxelfile.getPath());
			//com isto vi que funciona quando se fizer leitura do ficheiro é favor apagar a linha a cima
		}
			else {
				System.out.println("nao selecionaram nenhum ficheiro");
			}
			
		}
	}

