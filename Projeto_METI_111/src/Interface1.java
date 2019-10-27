
import javax.swing.JFrame ;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interface1 {
	private JFrame frame1 ;
	
	public Interface1 () {
		frame1= new JFrame ("interface1");
		frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame1.setLayout(new BorderLayout());
		frame1.setVisible(true);
		frame1.setSize(400,100);
		addFrameContent();
	}
	
	
	private void addFrameContent() {
		
		JLabel aviso = new JLabel (" clique no botao browser para esclher o ficheito ");
		frame1.add(aviso,BorderLayout.NORTH);
		//painel sul para o botao nao ocupar mt espaco
		JPanel painelsul = new JPanel ();
		painelsul.setLayout(new FlowLayout());
		frame1.add(painelsul,BorderLayout.SOUTH);
		
		//botao de executa a janela
		JButton browser = new JButton ("Browser");
		painelsul.add(browser);
		
		//acao do botao invoca o objecto da outra class para se ir buscar o ficheiro 
		browser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Openfile f = new Openfile();
				try {
					f.Browser();
				}catch(Exception a) {a.printStackTrace();}
				Interface1 i = new Interface1 ();
			}
	});
		
		JButton finish = new JButton ("finish");
		painelsul.add(finish);
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//aqui devesse inicializar a nova interface
				
				frame1.setVisible(false);
			}
	});
		
	}
	
	
	
	public static void main (String[] args) {
		Interface1 i = new Interface1 ();
	}
}
