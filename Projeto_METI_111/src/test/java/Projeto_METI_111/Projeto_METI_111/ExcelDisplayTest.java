package Projeto_METI_111.Projeto_METI_111;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.junit.Before;
import org.junit.Test;

public class ExcelDisplayTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testExcelDisplayWorks() throws IOException {
		File excelfile = new File("files/Long-Method.xlsx");
		ReadExcel readExcel = new ReadExcel(excelfile);
		ExcelDisplay.startInstance(readExcel);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testExcelDisplayFileDontExists() throws IOException {
		File excelfile = new File("files/Long-Method2.xlsx");
		ReadExcel readExcel = new ReadExcel(excelfile);
	}

	@Test(expected = NotOfficeXmlFileException.class)
	public void testExcelDisplayFileInvalid() throws IOException {
		File excelfile = new File("files/teste.txt");
		ReadExcel readExcel = new ReadExcel(excelfile);
		ExcelDisplay.startInstance(readExcel);
	}
	
	@Test
	public void testStartInstance() throws IOException {
		File excelfile = new File("files/Long-Method.xlsx");
		ReadExcel readExcel = new ReadExcel(excelfile);
		ExcelDisplay object1 = ExcelDisplay.startInstance(readExcel);
		ExcelDisplay object2 = ExcelDisplay.startInstance(readExcel);
		assertEquals(object1, object2);	
	}
}
