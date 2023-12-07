package File.operationPDF;

import java.awt.Color;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class WriteFilePDF {
	public static void main(String[] args) {
		PDDocument document =null;
		PDPage page = null ;
		try {
			document = new PDDocument();
			page = new PDPage();
			document.addPage(page);
			PDFont font = PDType1Font.HELVETICA_BOLD;
			PDPageContentStream content = new PDPageContentStream(document, page);
			content.beginText();
			content.setFont(font, 20);
			content.setNonStrokingColor(Color.BLUE);
			content.moveTextPositionByAmount(100, 700);
			content.drawString("Hi Im Java");
			content.endText();
			content.close();
			document.save("G:\\job\\BT_JAVA\\upload\\write.pdf");
			document.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
