package File.operationPDF;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class ReadFilePDF {
	public static String path = "G:\\job\\BT_JAVA\\upload\\test.pdf";
	
	
	public static void ReadPDF(String path) throws Exception {
		String expectedText = "Hello\n";
		File file = new File(path);
		PDDocument document = PDDocument.load(file);
		PDFTextStripper stripper = new PDFTextStripper();
		String text = stripper.getText(document);
		document.close();
		System.out.println(text);
	}
	public static void main(String[] args) {
		try {
			ReadPDF(path);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
