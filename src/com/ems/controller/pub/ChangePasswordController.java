package com.ems.controller.pub;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ems.dao.UserDao;
import com.ems.model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = {
		"/public/changePassword.html", 
		"/public/changePasswordDo",
		"/public/changePasswordSend"
		})
public class ChangePasswordController extends HttpServlet {
	
	// commons logging references
	static Logger log = Logger.getLogger(ChangePasswordController.class.getName());
	
	private static final long serialVersionUID = 1L;
    
    private UserDao dao;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordController() {
        super();
        log.debug("UserController ###################################");
    	log.trace("START");
		dao = new UserDao();
        log.debug("Dao object instantiated");
        log.trace("END");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.trace("START");
    	String forward = "/WEB-INF/jsp/public/changePassword.jsp";   
		        
		log.debug("forward: " + forward);
        
		if (request.getParameter("action") == null) {
			log.debug(request.getParameter("action"));
		}
		
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
    	
    	String forward = "/WEB-INF/jsp/public/changePassword.jsp";
		String email = request.getParameter("email").toString();
		log.debug("email: " + email);
    	
    	String action = "";
    	if (request.getParameter("action") != null) {
        	log.trace("action is not null");
    		action = request.getParameter("action");

    	}
    	
    	
    	if (action.equals("")){
    		log.debug("form to send link");
    		if (sendEmail(email)){
        		log.debug("sent mail");
        		request.setAttribute("message", 1);
        	}
    	}
    	else if (action.equals("change")){    		    		
    		String oldPassword = request.getParameter("oldPassword").toString();
    		String newPassword = request.getParameter("newPassword").toString();
    		String confirmedPassword = request.getParameter("confirmedPassword").toString();
    		log.debug("oldPassword: " + oldPassword);
    		log.debug("newPassword: " + newPassword);
    		log.debug("confirmedPassword: " + confirmedPassword);
    		
    		UserDao ud = new UserDao();
			log.debug("get user");
    		User u = ud.getUserByEmail(email);
    		log.debug("old password: " + u.getPassword());
    		Integer id = u.getId();

    		
	    	if (id != null){		
		    	log.debug("form to change password");

	    		if ( u.getPassword().equals(oldPassword)){
		    		if (!newPassword.equals("")){
		    			if (newPassword.equals(confirmedPassword)){
		    				log.debug("try to change password");
		    				if (ud.updatePassword(u.getId(), newPassword)){
		    					log.debug("password changed");
		    		    		request.setAttribute("message", 2);
		    				}
		    	    		
		    			}
		    			else {
		    				log.debug("confirmed password is no equal to new password");
		    	    		request.setAttribute("message", 3);
		    			}
		    		}
		    		else {
		    			log.debug("password  vuota");
		        		request.setAttribute("message", 4);
		    		}
	    		}
	    		else{
	    			log.debug("incorrect password");
	        		request.setAttribute("message", 5);
	    		}	    			
	    	}
	    	else {
	    		log.debug("email not recognized");
	    		request.setAttribute("message", 6);
	    	}
	    	
	    	
	    	request.setAttribute("email", email);
	    	request.setAttribute("oldPassword", oldPassword);
	    	request.setAttribute("newPassword", newPassword);
	    	request.setAttribute("confirmedPassword", confirmedPassword);
    		
    	}
    	

    	
        log.debug("forward: " + forward);

		try {
			getServletConfig().getServletContext().getRequestDispatcher(forward).forward(request, response);
			} 
		catch (Exception ex) {
				ex.printStackTrace();
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

    private boolean sendEmail(String to){ 
    	log.debug("address: " + to);


		String from = "cestino@gmail.com";
        log.debug("TO: " + to);
        String subject = "Reset password for site EMS";
        
        String message = "";
        message += "You have asket to change the password for you user";
        message += "\nClick on the following link and change it:\n";
        message += "\n" +
        			"http://localhost:8080/ems/public/changePassword.html?email=" + to + "\n";
        message += "\n\nThe staff";
       
        String login = "ems2013.staff@gmail.com";
        String password = "PaSsWoRd";
		
		
		
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
        	log.debug(ex);
        	return false;


        } catch (AddressException ex) {
        	log.debug("AddressException");
        	log.debug(ex);
        	return false;


        } catch (MessagingException ex) {
        	log.debug("MessagingException");
        	log.debug(ex);
        	return false;
        }
        return true;
    }
	
	
}
