package com.ems.controller.priv;

import java.io.IOException;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
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

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import org.apache.log4j.Logger;


import com.ems.dao.GroupDao;

import com.ems.dao.ParticipantDao;
import com.ems.model.Participant;
import com.sun.mail.smtp.SMTPSaslAuthenticator;


/**
 * Servlet implementation class ParticipantController
 */
@WebServlet(urlPatterns = {
		"/private/participantList.html", 
		"/private/participant.jsp", 
		"/private/participantAdd",
		"/private/participantDelete",
		"/private/participantInvite",
		"/private/participantApprove"		
		})
public class ParticipantController extends HttpServlet {
	
	// commons logging references
	static Logger log = Logger.getLogger(ParticipantController.class.getName());
	
	private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/participant.jsp";
    private static String LIST_USER = "/participantList.jsp";
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
    	log.trace("START");
    	
    	log.debug("id_group: " + request.getParameter("id_group"));
    	

    	int id_group = 0;
    	if (request.getParameter("id_group") != null){
    		id_group = Integer.parseInt(request.getParameter("id_group").toString());
    	}
    	request.setAttribute("id_group", id_group);
    	
    	String forward="";
        String action = request.getParameter("action");
        if (action == null){
        	log.debug("action is NULL");
        	action="";
        }
        
        if (action.equalsIgnoreCase("delete")){
            log.debug("action: DELETE - " + action);
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteRecord(id);
            forward = LIST_USER;
            request.setAttribute("records", dao.getAllRecords());  
            GroupDao ed = new GroupDao();
            request.setAttribute("groups", ed.getAllRecords());
        } else if (action.equalsIgnoreCase("edit")){
            log.debug("action: EDIT - " + action);
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            Participant record = dao.getRecordById(id);
            request.setAttribute("record", record);
        } else if (action.equalsIgnoreCase("insert")){
            log.debug("action: INSERT - " + action);
            request.removeAttribute("record");
            forward = INSERT_OR_EDIT;
        } else if (action.equalsIgnoreCase("listRecord")){
            log.debug("action: listRecord - " + action);
            forward = LIST_USER;
            request.setAttribute("records", dao.getAllRecordsById_group(id_group));
            GroupDao gd = new GroupDao();
            request.setAttribute("groups", gd.getAllRecords());
        } else {
            log.debug("action: ELSE - " + action);
            forward = LIST_USER;
            request.setAttribute("records", dao.getAllRecordsById_group(id_group));
            GroupDao gd = new GroupDao();
            request.setAttribute("groups", gd.getAllRecords());
        }
    	
        log.debug("forward: " + forward);
        log.debug("action: " + action);
    	/*
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    	log.trace("END");
    	 */	
    	
        forward = "/WEB-INF/jsp/private" + forward;
        log.debug("forward: " + forward);
        
		try {
			getServletConfig().getServletContext().getRequestDispatcher(forward).forward(request, response);
			} 
		catch (Exception ex) {
				ex.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.trace("START");
    	Participant record = new Participant();
    	
    	int id_group = 0;
    	
    	log.debug("id_group: " + request.getParameter("id_group"));
    	log.debug("action: " + request.getParameter("action"));
    	
    	if (request.getParameter("id_group") != null){
    		id_group = Integer.parseInt(request.getParameter("id_group").toString());
    	}
    	
    	if (request.getParameter("action") == null || request.getParameter("action").equals("edit")) {
    		//form for INSERT or UPDATE
	    	
	    	log.debug("----------------> id_group: " + request.getParameter("id_group"));
	    	
			record.setId_group(id_group);
	    	record.setFname(request.getParameter("fname"));
	    	record.setLname(request.getParameter("lname"));
	    	record.setDate_of_birth(request.getParameter("date_of_birth"));
	    	record.setRegistration_date(request.getParameter("registration_date"));
	    	record.setApproved(Boolean.parseBoolean(request.getParameter("approved")));
	    	record.setBlocked(Boolean.parseBoolean(request.getParameter("blocked")));
	    	
	    	String id = request.getParameter("id");
	        
	    	log.debug("id: " + id);
	    	
	        if(id == null || id.isEmpty()) {
	        	log.debug("INSERT");
	            dao.addRecord(id_group, record);
	            request.setAttribute("id_group", id_group);
	        }
	        else
	        {
	        	log.debug("UPDATE");
	        	record.setId(Integer.parseInt(id));
	            dao.updateRecord(record);
	            request.setAttribute("id_group", id_group);
	        }
	        
	        GroupDao gd = new GroupDao();
	        request.setAttribute("groups", gd.getAllRecords());
	        
	        String forward = "/WEB-INF/jsp/private" + LIST_USER;
	        log.debug("forward: " + forward);
	        request.setAttribute("records", dao.getAllRecordsById_group(id_group));
			try {
				getServletConfig().getServletContext().getRequestDispatcher(forward).forward(request, response);
				} 
			catch (Exception ex) {
					ex.printStackTrace();
				}
    	}
    	else if (request.getParameter("action").equals("invite") ){
    		//invite participant
    		log.debug("Invite Participant");
    		

    		String from = "cestino@gmail.com";
            String to = request.getParameter("to");
    		log.debug("TO: " + to);
            String subject = "Invitation";
            String message = "sdjnfkasjdnf aksdjf ask dfasdk fasdk fk2";;
            String login = "cestino@gmail.com";
            String password = "01abekul";
    		
    		
    		
            try {
            	log.debug("try sending");
                Properties props = new Properties();
                

                props.put("mail.smtp.starttls.enable", "true"); // added this line
                props.put("mail.smtp.host", "smtp.googlemail.com");
                props.put("mail.smtp.user", from);
                props.put("mail.smtp.password", password);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                
            	log.debug("1");
                Authenticator auth = new SMTPAuthenticator(login, password);
            	log.debug("2");
                Session session = Session.getInstance(props, auth);
            	log.debug("3");
                MimeMessage msg = new MimeMessage(session);
            	log.debug("4");
                msg.setText(message);
                msg.setSubject(subject);
                msg.setFrom(new InternetAddress(from));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            	log.debug("5");
                Transport.send(msg);
            	log.debug("6");

            } catch (AuthenticationFailedException ex) {
            	log.debug("AuthenticationFailedException");
                request.setAttribute("ErrorMessage", "Authentication failed");



            } catch (AddressException ex) {
            	log.debug("AddressException");
                request.setAttribute("ErrorMessage", "Wrong email address");



            } catch (MessagingException ex) {
            	log.debug("MessagingException");
                request.setAttribute("ErrorMessage", ex.getMessage());

            }

    	}
    	else if (request.getParameter("action").equals("approve") ){
    		//approve enrollment
    		log.debug("APPROVE");
    		log.debug("action: " + request.getParameter("action"));
	        GroupDao gd = new GroupDao();
	        request.setAttribute("groups", gd.getAllRecords());
	        
	        String forward = "participantList.html?action=listRecord&id_group=" + id_group;
	        log.debug("forward: " + forward);
	        request.setAttribute("records", dao.getAllRecordsById_group(id_group));

	        
	        response.sendRedirect(forward);
    		
    	}
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

}
