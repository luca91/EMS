package com.ems.test.pdf;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import net.glxn.qrgen.image.ImageType;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;

import com.ems.pdf.PDFGenerator;
import com.ems.pdf.QRGenerator;

public class PDFGeneratorTest {
	
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	/**
	 * @uml.property  name="test"
	 * @uml.associationEnd  
	 */
	private PDFGenerator test;
	private Document aux;

	@Before
	public void setUp() throws Exception {
		test = new PDFGenerator("Luca", "Bellettati", 5, "UniBz", "Snowdays 2014", "/home/luca/");
		test.createDocument();
	}

	@Before
	public void createTempFile() throws DocumentException, MalformedURLException, IOException{
		Rectangle page = new Rectangle(280f, 360f);
		aux = new Document(page, 0f, 0f, 221f, 0f);
	    PdfWriter.getInstance(aux, new FileOutputStream("/home/luca"+"/"+"Luca"+"Bellettati"+50+".pdf"));
	    aux.open();
	    Paragraph container = new Paragraph();
		Paragraph event = new Paragraph("Snowdays 2014", subFont);
		Paragraph data = new Paragraph("Luca"+"Bellettati", catFont);
		Paragraph group = new Paragraph(String.valueOf(50), smallBold);
		container.add(event);
		container.add(data);
		container.add(group);
		container.setAlignment(Paragraph.ALIGN_RIGHT);
		aux.add(container);
		QRGenerator aQR = QRGenerator.from("Luca"+"Bellettati"+String.valueOf(50));
		aQR.to(ImageType.PNG);
		File qrFile = aQR.file();
		Image qrImage = Image.getInstance(qrFile.getPath());
		qrImage.scaleAbsolute(80f, 80f);
		qrImage.setAbsolutePosition(200f, 280f);
		aux.add(qrImage);
		Image anImage = Image.getInstance("/home/luca/emlogo.png");
		anImage.scaleAbsolute(100f, 70f);
		anImage.setAbsolutePosition(20f, 270f);
		aux.add(anImage);
	    aux.close();
	}
	
	@Test
	public void addQRTest() throws MalformedURLException, IOException, DocumentException{
		boolean check = test.addQR();
		assertEquals(true, check);
	}
	
	@Test
	public void addDataTest() throws DocumentException, MalformedURLException, IOException{
		boolean check = test.addData();
		assertEquals(true, check);
	}
	
	@Test
	public void addImagetest() throws MalformedURLException, IOException, DocumentException{
		boolean check = test.addImage();
		assertEquals(true, check);
	}
	
	@Test
	public void getDocumentTest() throws MalformedURLException, DocumentException, IOException{
		test = new PDFGenerator("Luca", "Bellettati", 5, "UniBz", "Snowdays 2014", "/home/luca/");
	    test.createDocument();
	    aux = test.getDocument();
		assertEquals(aux, test.getDocument());
	}

}
