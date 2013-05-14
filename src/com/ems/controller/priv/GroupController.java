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
import com.ems.model.Event;
import com.ems.model.Group;


/**
 * Servlet implementation class GroupController
 */
@WebServlet(urlPatterns = {
		"/private/groupList.html", 
		"/private/group.jsp", 
		"/private/groupAdd",
		"/private/groupDelete"
		})
public class GroupController extends HttpServlet {
	
	// commons logging references
	static Logger log = Logger.getLogger(GroupController.class.getName());
	
	private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/group.jsp";
    private static String LIST_USER = "/groupList.jsp";
    private GroupDao dao;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupController() {
        super();
        log.debug("###################################");
    	log.trace("START");
		dao = new GroupDao();
        log.debug("Dao object instantiated");
        log.trace("END");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.trace("START");
    	
    	log.debug("id_event: " + request.getParameter("id_event"));
    	

    	int id_event = 0;
    	if (request.getParameter("id_event") != null){
    		id_event = Integer.parseInt(request.getParameter("id_event").toString());
    	}
    	request.setAttribute("id_event", id_event);
    	
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
            request.setAttribute("records", dao.getAllRecordsById_event(id_event));  
            EventDao ed = new EventDao();
            request.setAttribute("events", ed.getAllRecords());
        } else if (action.equalsIgnoreCase("edit")){
            log.debug("action: EDIT - " + action);
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            Group record = dao.getRecordById(id);
            request.setAttribute("record", record);
        } else if (action.equalsIgnoreCase("insert")){
            log.debug("action: INSERT - " + action);
            request.removeAttribute("record");
            forward = INSERT_OR_EDIT;
        } else if (action.equalsIgnoreCase("listRecord")){
            log.debug("action: listRecord - " + action);
            forward = LIST_USER;
            request.setAttribute("records", dao.getAllRecordsById_event(id_event));
            EventDao ed = new EventDao();
            request.setAttribute("events", ed.getAllRecords());
        } else {
            log.debug("action: ELSE - " + action);
            forward = LIST_USER;
            request.setAttribute("records", dao.getAllRecordsById_event(id_event));
            EventDao ed = new EventDao();
            request.setAttribute("events", ed.getAllRecords());
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
    	Group record = new Group();
    	
    	int id_event = 0;
    	
    	log.debug("id_event: " + request.getParameter("id_event"));
    	
    	if (request.getParameter("id_event") != null){
    		id_event = Integer.parseInt(request.getParameter("id_event").toString());
    	}
    	
		record.setId_event(id_event);
    	record.setId_group_referent(Integer.parseInt(request.getParameter("id_group_referent")));
    	record.setName(request.getParameter("name"));
    	record.setMax_group_number(Integer.parseInt(request.getParameter("max_group_number")));
    	record.setBlocked(Boolean.parseBoolean(request.getParameter("blocked")));
        
    	String id = request.getParameter("id");
        
    	log.debug("id: " + id);
    	
        if(id == null || id.isEmpty()) {
        	log.debug("INSERT");
            dao.addRecord(id_event, record);
            request.setAttribute("id_event", id_event);
        }
        else
        {
        	log.debug("UPDATE");
        	record.setId(Integer.parseInt(id));
            dao.updateRecord(record);
            request.setAttribute("id_event", id_event);
        }
        
        EventDao ed = new EventDao();
        request.setAttribute("events", ed.getAllRecords());
        
        String forward = "/WEB-INF/jsp/private" + LIST_USER;
        log.debug("forward: " + forward);
        request.setAttribute("records", dao.getAllRecordsById_event(id_event));
		try {
			getServletConfig().getServletContext().getRequestDispatcher(forward).forward(request, response);
			} 
		catch (Exception ex) {
				ex.printStackTrace();
			}
    	log.trace("END");
	}

}
