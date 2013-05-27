//Code inspired from: http://zetcode.com/tutorials/jeetutorials/sendingemail/

package com.ems.tools;

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

import org.apache.log4j.Logger;

public class Email {
	
	// commons logging references
	static Logger log = Logger.getLogger(Email.class.getName());
	
	String from = "ems2013.staff@gmail.com";
    String login = "ems2013.staff@gmail.com";
    String password = "PaSsWoRd";
	
    Properties props = new Properties();
    
	public Email(){
        props.put("mail.smtp.starttls.enable", "true"); // added this line
        props.put("mail.smtp.host", "smtp.googlemail.com");
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
	}
	
	
    public boolean sendEmail(String to, String subject, String message){ 
    	log.debug("address: " + to);

        Authenticator auth = new SMTPAuthenticator(login, password);

        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);

        try {
			msg.setText(message);
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            Transport.send(msg);
            return true;   
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
    }	

    private class SMTPAuthenticator extends Authenticator {

        private PasswordAuthentication authentication;

        public SMTPAuthenticator(String login, String password) {
            authentication = new PasswordAuthentication(login, password);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
    
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Email e = new Email();
		e.sendEmail("ems2013.staff@gmail.com", "subject", "message");

	}
}