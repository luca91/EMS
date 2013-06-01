package com.ems.controller.priv;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ems.dao.EventDao;
import com.ems.dao.GroupDao;
import com.ems.dao.ParticipantDao;
import com.ems.dao.UserDao;
import com.ems.model.Event;
import com.ems.model.Group;
import com.ems.model.Participant;
import com.ems.model.User;
import com.ems.tools.Email;


/**
 * Servlet implementation class ParticipantController
 */
@WebServlet(urlPatterns = {
		"/private/participantsPrint.html"		
		})
public class ParticipantsPrintController extends HttpServlet {
	
	// commons logging references
	static Logger log = Logger.getLogger(ParticipantsPrintController.class.getName());
	
	private static final long serialVersionUID = 1L;
    
    /**
	 * @uml.property  name="dao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private ParticipantDao dao;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantsPrintController() {
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

		UserDao ud = new UserDao();
		User  systemUser = ud.getUserByEmail(request.getUserPrincipal().getName());
		
		int id_event = Integer.parseInt(request.getParameter("id_event"));
		
		HttpSession session = request.getSession(true);
		session.removeAttribute("systemUser");
		session.setAttribute("systemUser",systemUser);
		
		ParticipantDao pd = new ParticipantDao();
		
		List <Participant> l = pd.getAllRecordsById_event(id_event);
		
		log.debug("l: " + l.size());
		request.setAttribute("listOfParticipant", l);
		
		EventDao ed = new EventDao();
		Event e = ed.getRecordById(id_event);
		
		request.setAttribute("eventName", e.getName());
		
    	        
		String forward = "/WEB-INF/jsp/private/participantsPrint.jsp";

        
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

    	log.trace("END");
	}

}