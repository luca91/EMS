package com.ems.controller.pub;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ems.dao.ParticipantDao;
import com.ems.model.Participant;
import com.ems.tools.Email;


/**
 * Servlet implementation class ParticipantController
 */
@WebServlet(urlPatterns = {
		"/public/enrollmentForm.html", 
		"/public/participantAdd"
		})
public class ParticipantController extends HttpServlet {
	
	// commons logging references
	static Logger log = Logger.getLogger(ParticipantController.class.getName());
	
	private static final long serialVersionUID = 1L;

	private static String form = "/WEB-INF/jsp/public/participant.jsp";
	private static String showFeedback = "/WEB-INF/jsp/public/participantFeedback.jsp";
	
	private static String feedback = "enrollmentForm.html?message=y";
	
    /**
	 * @uml.property  name="dao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private ParticipantDao dao;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantController() {
        super();
        log.debug("###################################");
    	log.trace("START");
		dao = new ParticipantDao();
        log.debug("Dao object instantiated");
        log.trace("END");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("START");
		log.debug("-- " + request.getParameter("message"));
		
		boolean showForm = true;
		if (request.getParameter("message") != null){
			showForm = false;
		}
		
		if (showForm) {
			log.debug("show the form to enrollment");
			log.debug("id_group: " + request.getParameter("id_group"));
	    	if (request.getParameter("id_group") != null){
	    		log.debug("doget - load the form");
	    		int id_group = Integer.parseInt(request.getParameter("id_group").toString());
	        	request.setAttribute("id_group", id_group);
	        	request.setAttribute("email", request.getParameter("email"));
	    		try {
	    			getServletConfig().getServletContext().getRequestDispatcher(form).forward(request, response);
	    			} 
	    		catch (Exception ex) {
	    				ex.printStackTrace();
	    			}
	    	}
		}
		else {
			log.debug("show a message");
			try {
    			getServletConfig().getServletContext().getRequestDispatcher(showFeedback).forward(request, response);
    			} 
    		catch (Exception ex) {
    				ex.printStackTrace();
    			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.trace("START");
    	Participant record = new Participant();
    	
	

    	int	id_group = Integer.parseInt(request.getParameter("id_group").toString());

    	log.debug("----------------> id_group: " + id_group);
    	log.debug("----------------> email: " + request.getParameter("email"));
    	
		record.setId_group(id_group);
    	record.setFname(request.getParameter("fname"));
    	record.setLname(request.getParameter("lname"));
    	record.setEmail(request.getParameter("email"));
    	record.setDate_of_birth(request.getParameter("date_of_birth"));
    	
    	String currentdate = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    	log.debug("current date&time: " + currentdate);
    	record.setRegistration_date(currentdate);
    	record.setApproved(false);
    	record.setBlocked(false);
    	
    	String id = request.getParameter("id");
        
    	log.debug("id: " + id);
    	log.debug("INSERT");
    	
        dao.addRecord(id_group, record);
        request.setAttribute("record", record);
        
        try {
			sendEmail(record, request.getParameter("email").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	log.debug(feedback);
        response.sendRedirect(feedback);
    	
    	log.trace("END");
	}   
	
	
	//http://zetcode.com/tutorials/jeetutorials/sendingemail/
    private class SMTPAuthenticator extends Authenticator {

        private PasswordAuthentication authentication;

        public SMTPAuthenticator(String login, String password) {
            authentication = new PasswordAuthentication(login, password);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }

    private boolean sendEmail(Participant p, String to) throws ParseException{ 
    	log.debug("address: " + to);

	
        log.debug("TO: " + to);
        String subject = "Subscription registered";
        
        String message = "";
        message += "Hi " + p.getFname() + "\n";
        message += "\nYour subscription has been registered by the system\n";
        message += "\n\nThe staff";
		
		Email e = new Email();
		return e.sendEmail(to, subject, message);
    }
	
}
