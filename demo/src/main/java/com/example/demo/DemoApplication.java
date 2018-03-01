package com.example.demo;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import org.seuksa.itextkhmer.KhmerLigaturizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.applet.Applet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.net.URL;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		Document document = new Document();
		String filePath = new ClassPathResource("kh_battambang.ttf").getPath();
		FileOutputStream fos = null;
		String name = "khmer-unicode-example";
		try {
			//File file = new File("C:\\Users\\d.dim\\Desktop/tmp/khmer-unicode-example.pdf");
			final File outFile = File.createTempFile(name,".pdf", new File("D:\\home\\sharejee-prepare-teach\\demo\\src\\main\\resources"));
			fos = new FileOutputStream(outFile);
			ITextRenderer renderer = new ITextRenderer();
			renderer.getFontResolver().addFont(filePath, com.lowagie.text.pdf.BaseFont.IDENTITY_H, com.lowagie.text.pdf.BaseFont.EMBEDDED);
			renderer.setDocumentFromString(
					"<!DOCTYPE html>\n" +
					"<html>\n" +
					"<head>\n" +
					"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
					"</head>\n" +
					"\n" +
						"<body>\n" +
							"<p> " +
								"Hello<span style=\"font-family: 'Nokora'\">" + new KhmerLigaturizer().process("សួស្ដី") +
								"</span>everybody <span style=\"font-family: 'Nokora'\">"+ new KhmerLigaturizer().process("បងប្អូនទាំងអស់គ្នា") +"</span>to be here with" +
								"<span style=\"font-family: 'Nokora'\">"+new KhmerLigaturizer().process("រីករាយមកកាន់គ្រួសារ")+"</span>"+
							"</p>\n" +
						"</body>\n" +
					"\n" +
					"</html>");
			renderer.layout();
			renderer.createPDF(fos, false);
			renderer.finishPDF();//Helloសួស្តីeverybodyបងប្អូនទាំងអស់គ្នា to be here withរីករាយមកកាន់គ្រួសារ

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		document.close();
	}
}
