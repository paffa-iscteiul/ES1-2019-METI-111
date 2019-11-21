package Projeto_METI_111.Projeto_METI_111;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Interface3 {
	private JFrame frame;
	JTextField limloc;
	JTextField limatfd;
	JTextField limcyclo;
	JTextField limlaa;
	
	/**
	 * startInstance Interface3
	 * criar a interface com t�tulo e dimens�o
	 */
	public Interface3 () {
		frame = new JFrame ("Aplica��o de avalia�ao de c�digo");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(500,300);
		addFrameContent();
	}	
	
	/**
	 * Cria��o de todos os paineis necess�rios para a janela
	 * de introdu��o dos par�metros pelo utilizador
	 */
	
	private void addFrameContent() {
		//painel onde se l� o excell
		JPanel centerPanel = new JPanel ();
		centerPanel.setLayout(new FlowLayout());
		frame.add(ExcelDisplay.getInstance().getSp(),BorderLayout.NORTH); //onde entra  excell (est� criado o espa�o) com scroll
		
		JLabel aviso = new JLabel ("Valores da Tabela selecionada");
		centerPanel.add(aviso,BorderLayout.NORTH);
		
		JPanel factors = new JPanel();
		factors.setLayout(new BorderLayout());
		frame.add(factors);

		JPanel thresholdsPanel = new JPanel();
		thresholdsPanel.setLayout(new GridLayout(4,3));
		factors.add(thresholdsPanel, BorderLayout.CENTER);
		
		JPanel calculator = new JPanel();  //onde entra a calculadora (est� criado o espa�o)
		calculator.setLayout(new BorderLayout());
		frame.add(calculator, BorderLayout.EAST);
		
		// Organiza��o do Painel da "Calculadora" - mais tarde poder� ter a sua propria classe
		calculator.setLayout(new BorderLayout());
		
		JPanel cpainel = new JPanel();
		
		
		/**
		 * Cria��o do mostrador para introduzir a calculadora 
		 * bot�es para f�cil utiliza��o e adi��o dos par�metros e condi��es
		 */
		
		//Mostrador
		JTextField screen = new JTextField();   
		calculator.add(screen, BorderLayout.NORTH);
		
		// Painel com os termos das regras
		JPanel terms = new JPanel();
		terms.setLayout(new GridLayout(3,4));
		
		JButton and = new JButton(" AND");
		and.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + and.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton smaller = new JButton(" <");
		smaller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + smaller.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton telse = new JButton(" ELSE");
		telse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + telse.getText();
				screen.setText(enterTerm);
			}
		});
		
		
		JButton or = new JButton(" OR");
		or.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + or.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton equal = new JButton(" =");
		equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + equal.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton ttrue = new JButton(" TRUE");     //double t propositado
		ttrue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTermo = screen.getText() + ttrue.getText();
				screen.setText(enterTermo);
			}
		});
		
		JButton tif = new JButton(" IF");    //botao if
		tif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + tif.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton tfalse = new JButton(" FALSE");
		tfalse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + tfalse.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton bigger = new JButton(" >");
		bigger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() +bigger.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton tso = new JButton(" THEN");					//'entao'
		tso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + tso.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton isfeatureenvy = new JButton(" is_feature_envy");
		isfeatureenvy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm =screen.getText() + isfeatureenvy.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton islongmethod = new JButton(" is_long_method");
		islongmethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + islongmethod.getText();
				screen.setText(enterTerm);
			}
		});
		
		/**
		 * adicionar os bot�es ao painel de vizualiza��o
		 */
		
		terms.add(and);
		terms.add(smaller);
		terms.add(telse);
		terms.add(or);
		terms.add(equal);
		terms.add(ttrue);
		terms.add(tif);
		terms.add(tfalse);
		terms.add(bigger);
		terms.add(tso);
		terms.add(isfeatureenvy);
		terms.add(islongmethod);
		
		cpainel.add(terms);
		
		
		/**
		 * cria��o de painel com as a��es do utilizador
		 * op�oes de submeter ou apagar
		 */
		//Painel com as a��es
		JPanel actions = new JPanel();
		actions.setLayout(new GridLayout(2,1));
		JButton submit = new JButton("Submeter");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String regra = screen.getText();
			}
		});
		
		
		JButton clean = new JButton("Apagar");
		clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(screen.getText().length() > 0) {
					
		
					String regra = screen.getText();
					System.out.println("regra: "+regra);
					
					ArrayList<String> arr = new ArrayList<String>(Arrays.asList(regra.split(" ")));
					System.out.println("array com as coisas: "+ arr);
					if(arr!=null) {
						if(arr.size() > 0) {
							arr.remove(arr.size() -1);
						}
					}
					
					System.out.println(arr);
					String apagado = String.join(" ", arr);
					System.out.println("apagado: "+apagado);

					screen.setText(apagado);
				}
				
			}
		});
		
		actions.add(submit);
		actions.add(clean);
		cpainel.add(actions);
		calculator.add(cpainel, BorderLayout.CENTER);
			
		
		JLabel inserir = new JLabel ("Insira os valores de limiares pretendidos");
		factors.add(inserir,BorderLayout.NORTH);
		
		JLabel labelloc = new JLabel("LOC");
		JLabel labelcyclo = new JLabel("CYCLO");
		JLabel labelatfd = new JLabel("ATFD");
		JLabel labellaa = new JLabel("LAA");
		
		limloc = new JTextField();
		limloc.setText("0");
		limcyclo = new JTextField();
		limcyclo.setText("0");
		limatfd = new JTextField();
		limatfd.setText("0");
		limlaa = new JTextField();
		limlaa.setText("0");
		
		
		JButton guardarLOC =new JButton("Guardar");
		JButton guardarCYCLO=new JButton("Guardar");
		JButton guardarATFD=new JButton("Guardar");
		JButton guardarLAA=new JButton("Guardar");
		
		
		/**
		 * introdu��o de todos os bot�es nas respetivas posi��es
		 */
		thresholdsPanel.add(labelloc,0);
		thresholdsPanel.add(limloc,1);
		thresholdsPanel.add(guardarLOC,2);
		thresholdsPanel.add(labelcyclo,3);
		thresholdsPanel.add(limcyclo,4);
		thresholdsPanel.add(guardarCYCLO,5);
		thresholdsPanel.add(labelatfd,6);
		thresholdsPanel.add(limatfd,7);
		thresholdsPanel.add(guardarATFD,8);
		thresholdsPanel.add(labellaa,9);
		thresholdsPanel.add(limlaa,10);
		thresholdsPanel.add(guardarLAA,11);
				
		limloc.setSize(20,20);
		limcyclo.setSize(20,20);
		limatfd.setSize(20,20);
		limlaa.setSize(20,20);
		
		frame.setVisible(true);
	}
}
		
