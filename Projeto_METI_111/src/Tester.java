
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;

public class Tester {

	JFrame f;
	JTable j;
	
	//Constructor
	Tester() throws InvalidFormatException, IOException{
	
		//Frame Initiallization
		f = new JFrame();
		
		//Frame Title
		f.setTitle("JTable Example");
		
		//Data to be displayed in the JTable
//		String [] [] data = {
//			{ "Fábio", "Joana", "Miguel"},
//			{ "Fábio", "Joana", "Miguel"}
//		};
		
		//Column Names
		
		
		readExcel read = new readExcel(new File("C:\\Users\\ES\\Downloads\\Long-Method.xlsx"));
		String [] columnNames = read.getColumnNames();
		String [] [] data = read.getData();
	//	String [] columnNames = {"Boy", "Girl", "Idk"};
		
		//Initializing the JTable
		j = new JTable(data, columnNames);
		j.setBounds(30, 40, 200, 300);
		
		//Adding it to JScrollPane
		JScrollPane sp = new JScrollPane(j);
		f.add(sp);
		//Frame Size
		f.setSize(500, 200);
		//Frame Visible = true
		f.setVisible(true);
	
	}
	
	public static void main(String [] args) throws InvalidFormatException, IOException
	{
		new Tester();
	}
	
}