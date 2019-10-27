
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
	
	public Interface1 () {
		frame = new JFrame ("Aplica��o de avalia�ao de c�digo");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setSize(400,100);
		addFrameContent();
	}
	
	
	private void addFrameContent() {
		JLabel aviso = new JLabel ("Clique no bot�o browser para escolher o ficheiro Excel");
		frame.add(aviso,BorderLayout.NORTH);
		//painel sul para o botao nao ocupar mt espaco
		JPanel painelsul = new JPanel ();
		painelsul.setLayout(new BorderLayout());
		frame.add(painelsul,BorderLayout.CENTER);
		
		//botao de executa a janela
		JButton browser = new JButton ("Browser");
		painelsul.add(browser, BorderLayout.WEST);
		
		//acao do botao invoca o objecto da outra class para se ir buscar o ficheiro 
		browser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Openfile f = new Openfile();
				try {
					f.Browser();
				}catch(Exception a) {a.printStackTrace();}
			}
	});
		
		JButton finish = new JButton ("Finish");
		painelsul.add(finish, BorderLayout.CENTER);
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Avan�ar para nova janela?");
				//aqui devesse inicializar a nova interface
				frame.dispose();
			}
	});
		
	}
	
	public void fileConfirmed (String s) {
		JOptionPane.showMessageDialog(null, "O ficheiro foi carregado corretamente");
	}
	
	public static void main (String[] args) {
		Interface1 i = new Interface1 ();
	}
}
