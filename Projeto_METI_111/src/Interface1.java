
import javax.swing.JFrame ;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interface1 {
	private JFrame frame;
	private Openfile of = new Openfile(this);
	private boolean fileSelected = false;
	
	public Interface1 () {
		frame = new JFrame ("Aplicação de avaliaçao de código");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setSize(400,100);
		addFrameContent();
	}
	
	
	private void addFrameContent() {
		JLabel aviso = new JLabel ("Clique no botão browser para escolher o ficheiro Excel");
		frame.add(aviso,BorderLayout.NORTH);
		//painel sul para o botao nao ocupar mt espaco
		JPanel painelsul = new JPanel ();
		painelsul.setLayout(new FlowLayout());
		frame.add(painelsul,BorderLayout.SOUTH);
		
		// painel centro
		JPanel painelcentro = new JPanel();
		painelcentro.setLayout(new FlowLayout());
		frame.add(painelcentro,BorderLayout.CENTER);
		
		
		//botao de executa a janela
		JButton browser = new JButton ("Browser");
		painelsul.add(browser, BorderLayout.WEST);
		
		//acao do botao invoca o objecto da outra class para se ir buscar o ficheiro 
		browser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					of.Browser();
					JLabel fille = new JLabel("ficheiro carregado:"+ of.Excelfile.getName());
					painelcentro.add(fille);
					frame.setSize(400,111);
				}catch(Exception a) {a.printStackTrace();}
			}
	});
		
		JButton finish = new JButton ("Finish");
		painelsul.add(finish, BorderLayout.CENTER);
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileSelected) {
					JOptionPane.showMessageDialog(null, "Avançar para nova janela?");
					Interface3 interface3 = new Interface3();
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Tem de selecionar algum ficheiro");
				}
				
			}
	});
		
	}
	
	public void fileConfirmed (String s) {
		JOptionPane.showMessageDialog(null, "O ficheiro " + s + " foi carregado corretamente. Carregue Finish para continuar");
		fileSelected=true;
	}
	
	public static void main (String[] args) {
		Interface1 i = new Interface1 ();
	}
}
