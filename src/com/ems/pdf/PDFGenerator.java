package com.ems.pdf;

import java.io.File;
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

public class PDFGenerator {
	 
	static Logger log = Logger.getLogger(PDFGenerator.class.getName());
	
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="surname"
	 */
	private String surname;
	/**
	 * @uml.property  name="id"
	 */
	private int id;
	/**
	 * @uml.property  name="eventName"
	 */
	private String eventName;
	/**
	 * @uml.property  name="path"
	 */
	private String path;
	/**
	 * @uml.property  name="aDocument"
	 * @uml.associationEnd  
	 */
	private Document aDocument;
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	
	/**
	 * Empty constructor.
	 */
	public PDFGenerator(){ }
	
	/**
	 * It constructs an instance of the object with all the info.
	 * @param fname String
	 * @param lname String 
	 * @param groupId int
	 * @param eventName String
	 * @param path String 
	 * @throws MalformedURLException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public PDFGenerator(String fname, String lname, int groupId, String eventName, String path) throws MalformedURLException, DocumentException, IOException{
		log.debug("###################################");
	    log.trace("START");
		name = fname;
		surname = lname;
		id = groupId;
		this.eventName = eventName;
		this.path = path;
		Rectangle page = new Rectangle(280f, 360f);
		aDocument = new Document(page, 0f, 0f, 221f, 0f);
		log.debug("PDF generator istantiated");
	    log.trace("END");
	}
	
	/**
	 * It creates the final pdf and stores it in the given path.
	 * @return boolean
	 * @throws DocumentException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public boolean createDocument() throws DocumentException, MalformedURLException, IOException{
		log.trace("START");
		boolean check = false;
	    PdfWriter.getInstance(aDocument, new FileOutputStream(path+"/"+name+surname+id+".pdf"));
	    check = addData();
	    if(!check)
	    	return false;
	    check = addQR();
	    if(!check)
	    	return false;
	    check = addImage();
	    if(!check)
	    	return false;
	    log.debug("Output set");
	    return check;
	}
	
	/**
	 * It adds the text part to the badge.
	 * @return boolean
	 * @throws DocumentException
	 */
	public boolean addData() throws DocumentException{
		log.trace("START");
		aDocument.open();
		Paragraph container = new Paragraph();
		Paragraph event = new Paragraph(eventName, subFont);
		Paragraph data = new Paragraph(name+surname, catFont);
		Paragraph group = new Paragraph(String.valueOf(id), smallBold);
		container.add(event);
		container.add(data);
		container.add(group);
		container.setAlignment(Paragraph.ALIGN_RIGHT);
		boolean added = aDocument.add(container);
		log.debug("Container set");
		log.trace("END");
		aDocument.close();
		return added;
	}
	
	/**
	 * It adds the qr image to the badge. 
	 * @return boolean
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws DocumentException
	 */
	public boolean addQR() throws MalformedURLException, IOException, DocumentException{
		log.trace("START");
		aDocument.open();
		 QRGenerator aQR = QRGenerator.from(name+surname+String.valueOf(id));
		 aQR.to(ImageType.PNG);
		 File qrFile = aQR.file();
		 log.debug("QR location: "+qrFile.getPath());
		 Image qrImage = Image.getInstance(qrFile.getPath());
		 qrImage.scaleAbsolute(80f, 80f);
		 qrImage.setAbsolutePosition(200f, 280f);
		 boolean added = aDocument.add(qrImage);
		 log.debug("QR added");
		 log.trace("END");
		 aDocument.close();
		 return added;
	}
	
	/**
	 * It returns the name of the final file.
	 * @return String
	 */
	public String getFilePath(){
		return name+surname+String.valueOf(id)+".pdf";
	}
	
	/**
	 * It sets the image/logo for the event.
	 * @return boolean
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws DocumentException
	 */
	public boolean addImage() throws MalformedURLException, IOException, DocumentException{
		log.trace("START");
		aDocument.open();
		Image anImage = Image.getInstance("/home/luca/emlogo.png");
		anImage.scaleAbsolute(100f, 70f);
		anImage.setAbsolutePosition(20f, 270f);
		boolean added = aDocument.add(anImage);
		log.debug("Image added");
		log.trace("END");
		aDocument.close();
		return added;
	}
	
	public Document getDocument(){
		return this.aDocument;
	}

}
