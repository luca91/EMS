package com.ems.controller.priv;

import java.io.IOException;

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
		"/private/participantDelete"
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
    	
    	if (request.getParameter("id_group") != null){
    		id_group = Integer.parseInt(request.getParameter("id_group").toString());
    	}
    	
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
    	log.trace("END");
	}

}
