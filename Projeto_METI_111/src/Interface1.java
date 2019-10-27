
import javax.swing.JFrame ;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
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
		
		JButton browser = new JButton ("Browser");
		frame1.add(browser);
		//acao do botao invoca o objecto da outra class para se ir buscar o ficheiro 
		browser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Openfile f = new Openfile();
				try {
					f.Browser();
				}catch(Exception a) {a.printStackTrace();}
				
				//aqui devesse inicializar a nova interface
				frame1.setVisible(false);
			}
	});
	}
	
	
	
	public static void main (String[] args) {
		Interface1 i = new Interface1 ();
	}
}
