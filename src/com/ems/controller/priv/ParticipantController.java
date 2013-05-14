package com.ems.controller.priv;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

import com.ems.dao.EventDao;
import com.ems.dao.GroupDao;
import com.ems.dao.ParticipantDao;
import com.ems.model.Event;
import com.ems.model.Group;
import com.ems.model.Participant;


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
    	
    	// Used for invitation
    	request.setAttribute("count", request.getParameter("count"));
    	request.setAttribute("showCount", request.getParameter("showCount"));
    	

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
	    	record.setEmail(request.getParameter("email"));
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
    		
    		String[] result = request.getParameter("listTo").toString().split(";", -1);
    		int count = 0;
    		
    		GroupDao gd = new GroupDao();
    		Group g = gd.getRecordById(id_group);
    		
    		EventDao ed = new EventDao();
    		Event e = ed.getRecordById(g.getId_event());
    		
    		for (int i = 0; i < result.length; i++){
    			try {
					if (sendEmail(result[i], g, e)){
						count++;
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			log.debug(result[i]);
    		}

	        String forward = "participantList.html?action=listRecord&id_group=" + id_group + "&count=" + count + "&showCount=y";
	        log.debug("forward: " + forward);
	        request.setAttribute("records", dao.getAllRecordsById_group(id_group));

	        
	        response.sendRedirect(forward);
    	}
    	else if (request.getParameter("action").equals("approve") ){
    		//approve enrollment
    		log.debug("APPROVE");
    		log.debug("action: " + request.getParameter("action"));
	        GroupDao gd = new GroupDao();
	        request.setAttribute("groups", gd.getAllRecords());
	        
	        ParticipantDao pd = new ParticipantDao();
	        List<Participant>  list = pd.getAllRecordsById_group(id_group);
	        Participant p = new Participant();
	        
	        for (int i = 0; i < list.size(); i++){
	        	p = list.get(i);
	        	if (!p.isApproved()){
	        		pd.approve(p.getId());
	        	}
	        }
	        
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

    private boolean sendEmail(String to, Group g, Event e) throws ParseException{ 
    	log.debug("address: " + to);

		DateFormat df = new SimpleDateFormat("dd MMM yyyy");
		Date fStart = new SimpleDateFormat("yyyy-MM-dd").parse(e.getStart());
		log.debug("fStart: " + df.format(fStart));
		Date fEnd = new SimpleDateFormat("yyyy-MM-dd").parse(e.getEnd());
		log.debug("fEnd: " + df.format(fEnd));
		Date fenrollment_start = new SimpleDateFormat("yyyy-MM-dd").parse(e.getEnrollment_start());
		log.debug("fenrollment_start: " + df.format(fenrollment_start));
		Date fenrollment_end = new SimpleDateFormat("yyyy-MM-dd").parse(e.getEnrollment_end());
		log.debug("fenrollment_end: " + df.format(fenrollment_end)); 	
		
		
		String from = "cestino@gmail.com";
        log.debug("TO: " + to);
        String subject = "Invitation to " + e.getName();
        
        String message = "";
        message += "You are invited to the " + e.getName() + " event.\n";
        message += "The event will take place from " + df.format(fStart) + " to " + df.format(fEnd) + "\n";
        message += "\nTo enroll to the event you have to click on the following link and fill up the registration form:\n";
        message += "\n" +
        			"http://localhost:8080/ems/public/enrollmentForm.html?id_group=" + 
        			g.getId() + 
        			"&email=" + to 
        			+ "\n";
        message += "\nDue date: " + df.format(fenrollment_start) +"\n";
        message += "\nEnrollment until: " + df.format(fenrollment_end) + "\n";
        message += "\n\nThe staff";
       
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
