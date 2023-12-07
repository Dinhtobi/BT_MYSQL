package File.operationPDF;

import java.io.FileInputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ConverImgeToPDF {
	public static void main(String[] args) {
		PDDocument doc = null;
		try {
			doc = new PDDocument();
			PDPage page = new PDPage();
			doc.addPage(page);
			PDImageXObject image = JPEGFactory.createFromStream(doc,new FileInputStream("G:\\job\\BT_JAVA\\upload\\102200044.jpg"));
			PDPageContentStream content = new PDPageContentStream(doc, page);
			content.drawImage(image,100,200,300,400);
			content.close();
			doc.save("G:\\job\\BT_JAVA\\upload\\Conver.pdf");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
