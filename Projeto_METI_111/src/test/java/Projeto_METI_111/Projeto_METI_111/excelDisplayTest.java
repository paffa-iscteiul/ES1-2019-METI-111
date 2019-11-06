package Projeto_METI_111.Projeto_METI_111;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.junit.Before;
import org.junit.Test;

public class excelDisplayTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testExcelDisplayWorks() throws IOException {
		File excelfile = new File("files/Long-Method.xlsx");
		readExcel readExcel = new readExcel(excelfile);
		excelDisplay.startInstance(readExcel);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testExcelDisplayFileDontExists() throws IOException {
		File excelfile = new File("files/Long-Method2.xlsx");
		readExcel readExcel = new readExcel(excelfile);
	}

	@Test(expected = NotOfficeXmlFileException.class)
	public void testExcelDisplayFileInvalid() throws IOException {
		File excelfile = new File("files/teste.txt");
		readExcel readExcel = new readExcel(excelfile);
		excelDisplay.startInstance(readExcel);
	}
	
	@Test
	public void testStartInstance() throws IOException {
		File excelfile = new File("files/Long-Method.xlsx");
		readExcel readExcel = new readExcel(excelfile);
		excelDisplay object1 = excelDisplay.startInstance(readExcel);
		excelDisplay object2 = excelDisplay.startInstance(readExcel);
		assertEquals(object1, object2);	
	}
}
