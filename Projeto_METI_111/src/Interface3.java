import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Interface3 {
	private JFrame frame;
	
	public Interface3 () {
		frame = new JFrame ("Aplicação de avaliaçao de código");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(500,300);
		addFrameContent();
	}	
	
	
	private void addFrameContent() {
		//painel onde se lê o excell
		JPanel painelcentro = new JPanel ();
		painelcentro.setLayout(new FlowLayout());
		frame.add(excelDisplay.getInstance().getSp(),BorderLayout.NORTH); //onde entra  excell (está criado o espaço)
		
		JLabel aviso = new JLabel ("Valores da Tabela selecionada");
		painelcentro.add(aviso,BorderLayout.NORTH);
		
		
		JPanel fatores = new JPanel();
		fatores.setLayout(new BorderLayout());
		frame.add(fatores);

		JPanel painelimiares = new JPanel();
		painelimiares.setLayout(new GridLayout(4,2));
		fatores.add(painelimiares, BorderLayout.CENTER);
		
			
		JPanel calculadora = new JPanel();  //onde entra a calculadora (está criado o espaço)
		calculadora.setLayout(new BorderLayout());
		frame.add(calculadora, BorderLayout.EAST);
		
		// Organização do Painel da "Calculadora"
		calculadora.setLayout(new BorderLayout());
		
		JPanel cpainel = new JPanel();
		
		// Painel com os termos das regras
		JPanel termos = new JPanel();
		termos.setLayout(new GridLayout(3,4));
		
		JButton and = new JButton("AND");
		JButton menor = new JButton("<");
		JButton telse = new JButton("ELSE");
		JButton or = new JButton("OR");
		JButton igual = new JButton("=");
		JButton verdadeiro = new JButton("TRUE");
		JButton se = new JButton("IF");
		JButton falso = new JButton("FALSE");
		JButton maior = new JButton(">");
		JButton então = new JButton("THEN");
		JButton isfeatureenvy = new JButton("is_feature_envy");
		JButton islongmethod = new JButton("is_long_method");
		
		termos.add(and);
		termos.add(menor);
		termos.add(telse);
		termos.add(or);
		termos.add(igual);
		termos.add(verdadeiro);
		termos.add(se);
		termos.add(falso);
		termos.add(maior);
		termos.add(então);
		termos.add(isfeatureenvy);
		termos.add(islongmethod);
		
		cpainel.add(termos);
		
		//Painel com as ações
		JPanel acoes = new JPanel();
		acoes.setLayout(new GridLayout(2,1));
		JButton submeter = new JButton("Submeter");
		JButton apagar = new JButton("Apagar");
		acoes.add(submeter);
		acoes.add(apagar);
		cpainel.add(acoes);
		calculadora.add(cpainel, BorderLayout.CENTER);
		
		//Mostrador
		JTextField mostrador = new JTextField();
		calculadora.add(mostrador, BorderLayout.NORTH);
		
		
		
		
		
		
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
		
		limloc.setSize(20,20);
		limcyclo.setSize(20,20);
		limatfd.setSize(20,20);
		limlaa.setSize(20,20);
		
		frame.setVisible(true);
	}
	
	
}
		
