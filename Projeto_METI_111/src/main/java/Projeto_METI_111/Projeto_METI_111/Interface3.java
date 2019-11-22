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
	private String limlocLer="";
	private String limatfdLer="";
	private String limcycloLer="";
	private String limlaaLer="";
	ArrayList <String> regras ;
	String main ;
	String THENfinal;
	String ELSEfinal;
	

	/**
	 * startInstance Interface3
	 * criar a interface com título e dimensão
	 */
	public Interface3 () {
		frame = new JFrame ("Aplicação de avaliaçao de código");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(500,300);
		addFrameContent();
	}	
	
	/**
	 * Criação de todos os paineis necessários para a janela
	 * de introdução dos parâmetros pelo utilizador
	 */
	
	private void addFrameContent() {
		//painel onde se lê o excell
		JPanel centerPanel = new JPanel ();
		centerPanel.setLayout(new FlowLayout());
		frame.add(ExcelDisplay.getInstance().getSp(),BorderLayout.NORTH); //onde entra  excell (está criado o espaço) com scroll
		
		JLabel aviso = new JLabel ("Valores da Tabela selecionada");
		centerPanel.add(aviso,BorderLayout.NORTH);
		
		JPanel factors = new JPanel();
		factors.setLayout(new BorderLayout());
		frame.add(factors);

		JPanel thresholdsPanel = new JPanel();
		thresholdsPanel.setLayout(new GridLayout(4,3));
		factors.add(thresholdsPanel, BorderLayout.CENTER);
		
		JPanel calculator = new JPanel();  //onde entra a calculadora (está criado o espaço)
		calculator.setLayout(new BorderLayout());
		frame.add(calculator, BorderLayout.EAST);
		
		// Organização do Painel da "Calculadora" - mais tarde poderá ter a sua propria classe
		calculator.setLayout(new BorderLayout());
		
		JPanel cpainel = new JPanel();
		
		
		/**
		 * Criação do mostrador para introduzir a calculadora 
		 * botões para fácil utilização e adição dos parâmetros e condições
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
				String metric = null;
				if(screen.getText().length() > 0) {
					String regra = screen.getText();
					ArrayList<String> arr = new ArrayList<String>(Arrays.asList(regra.split(" ")));
					metric = arr.get(arr.size() -1);
					
					if(metric.equals("CYCLO")) {
						String enterTerm = screen.getText() + smaller.getText() + " " + limcycloLer;
						
						screen.setText(enterTerm);
					}
					if(metric.equals("LAA")) {
						String enterTerm = screen.getText() + smaller.getText() + " " + limlaaLer;
						screen.setText(enterTerm);
					}
						
					if(metric.equals("ATFD")) {
						String enterTerm = screen.getText() + smaller.getText() + " " + limatfdLer;
						screen.setText(enterTerm);
					}
						
					if(metric.equals("LOC")) {
						String enterTerm = screen.getText() + smaller.getText() + " " + limlocLer;
						screen.setText(enterTerm);
					}
				}
				
				
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
				String metric = null;
				if(screen.getText().length() > 0) {
					String regra = screen.getText();
					ArrayList<String> arr = new ArrayList<String>(Arrays.asList(regra.split(" ")));
					metric = arr.get(arr.size() -1);
					
					if(metric.equals("CYCLO")) {
						String enterTerm = screen.getText() + bigger.getText() + " " + limcycloLer;
						
						screen.setText(enterTerm);
					}
					if(metric.equals("LAA")) {
						String enterTerm = screen.getText() + bigger.getText() + " " + limlaaLer;
						screen.setText(enterTerm);
					}
						
					if(metric.equals("ATFD")) {
						String enterTerm = screen.getText() + bigger.getText() + " " + limatfdLer;
						screen.setText(enterTerm);
					}
						
					if(metric.equals("LOC")) {
						String enterTerm = screen.getText() + bigger.getText() + " " + limlocLer;
						screen.setText(enterTerm);
					}
				}
								
								
				
					
					
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
		
		JButton loc = new JButton(" LOC");
		loc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + loc.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton cyclo = new JButton(" CYCLO");
		cyclo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + cyclo.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton atfd = new JButton(" ATFD");
		atfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + atfd.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton laa = new JButton(" LAA");
		laa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + laa.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton open = new JButton(" (");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + open.getText();
				screen.setText(enterTerm);
			}
		});
		
		JButton close = new JButton(" )");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enterTerm = screen.getText() + close.getText();
				screen.setText(enterTerm);
			}
		});
		
		/**
		 * adicionar os botões ao painel de vizualização
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
		terms.add(loc);
		terms.add(cyclo);
		terms.add(atfd);
		terms.add(laa);
		terms.add(open);
		terms.add(close);
		
		cpainel.add(terms);
		
		
		/**
		 * criação de painel com as ações do utilizador
		 * opçoes de submeter ou apagar
		 */
		//Painel com as ações
		JPanel actions = new JPanel();
		actions.setLayout(new GridLayout(2,1));
		JButton submit = new JButton("Submeter");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String regra = screen.getText();
				String posicao[] = regra.split(" IF ");
				String posicao1[] = posicao[1].split(" THEN ");
				
				String temporary1= posicao1[0].replace("( ","");
				main = temporary1.replace(" )","");
				
				String posicao2 [] = posicao1[1].split(" ELSE ");
				
				String temporary2= posicao2[0].replace("( ", "");
				 THENfinal= temporary2.replace(" )", "");
				 
				 String temporary3= posicao2[1].replace("( ", "");
				 ELSEfinal= temporary3.replace(" )", "");
				
				 System.out.println(main);
				 System.out.println(THENfinal);
				 System.out.println(ELSEfinal);
			}
		});
		
		
		JButton clean = new JButton("Apagar");
		clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(screen.getText().length() > 0) {
							
					String regra = screen.getText();
					
					ArrayList<String> arr = new ArrayList<String>(Arrays.asList(regra.split(" ")));

					if(arr!=null) {
						if(arr.size() > 0) {
							arr.remove(arr.size() -1);
						}
					}
					
					String apagado = String.join(" ", arr);

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
		
		JTextField limloc = new JTextField();
		limloc.setText("0");
		limlocLer="0";
		JTextField limcyclo = new JTextField();
		limcyclo.setText("0");
		limcycloLer="0";
		JTextField limatfd = new JTextField();
		limatfd.setText("0");
		limatfdLer="0";
		JTextField limlaa = new JTextField();
		limlaa.setText("0");
		limlaaLer="0";
		
		JButton guardarLOC =new JButton("Guardar");
		guardarLOC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limlocLer=limloc.getText();
				System.out.println(limlocLer);
			}
		});
		
		JButton guardarCYCLO=new JButton("Guardar");
		guardarCYCLO.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limcycloLer=limcyclo.getText();
				System.out.println(limcycloLer);
			}
		});
		
		JButton guardarATFD=new JButton("Guardar");
		guardarATFD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limatfdLer=limatfd.getText();
				System.out.println(limatfdLer);
			}
		});
		
		
		JButton guardarLAA=new JButton("Guardar");
		guardarLAA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limlaaLer=limlaa.getText();
				System.out.println(limlaaLer);
			}
		});
		
		
		/**
		 * introdução de todos os botões nas respetivas posições
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
		
