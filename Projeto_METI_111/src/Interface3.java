import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Interface3 {
	private JFrame frame;
	
	public Interface3 () {
		frame = new JFrame ("Aplicação de avaliaçao de código");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(500,300);
		frame.setVisible(true);
		addFrameContent();
	}	
	
	
	private void addFrameContent() {
				
		//painel onde se lê o excell
		JPanel painelcentro = new JPanel ();
		painelcentro.setLayout(new FlowLayout());
		frame.add(painelcentro,BorderLayout.NORTH);
		JLabel aviso = new JLabel ("Valores da Tabela selecionada");
		painelcentro.add(aviso,BorderLayout.NORTH);
		
		
		JPanel fatores = new JPanel();
		fatores.setLayout(new BorderLayout());
		frame.add(fatores);

		JPanel painelimiares = new JPanel();
		painelimiares.setLayout(new GridLayout(4,2));
		fatores.add(painelimiares, BorderLayout.CENTER);
		
		JPanel calculadora = new JPanel();
		calculadora.setLayout(new BorderLayout());
		fatores.add(calculadora, BorderLayout.EAST);
		
		
		JLabel inserir = new JLabel ("Insira os valores de limiares pretendidos");
		fatores.add(inserir,BorderLayout.NORTH);
		
		
		JLabel labelloc = new JLabel("LOC");
		JLabel labelcyclo = new JLabel("CYCLO");
		JLabel labelatfd = new JLabel("ATFD");
		JLabel labellaa = new JLabel("LAA");
		
		JTextField limloc = new JTextField();
		JTextField limcyclo = new JTextField();
		JTextField limatfd = new JTextField();
		JTextField limlaa = new JTextField();
		
		painelimiares.add(labelloc,0);
		painelimiares.add(limloc,1);
		painelimiares.add(labelcyclo,2);
		painelimiares.add(limcyclo,3);
		painelimiares.add(labelatfd,4);
		painelimiares.add(limatfd,5);
		painelimiares.add(labellaa,6);
		painelimiares.add(limlaa,7);
		
		limloc.setSize(100,20);

		
	}
	public static void main (String[] args) {
		Interface3 i3 = new Interface3 ();
	}
	
}
		
