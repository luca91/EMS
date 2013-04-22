//Code inspired by http://danielniko.wordpress.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/

package com.ems.prototype;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ems.prototype.EntityDao;
import com.ems.prototype.Entity;

public class EntityController extends HttpServlet {
	
	// commons logging references
	static Logger log = Logger.getLogger(EntityController.class.getName());
	
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/prototype/entityForm.jsp";
    private static String LIST_RECORDS = "/prototype/entityList.jsp";
    private EntityDao dao;


    

	public EntityController(Connection conn) {
    	super();
    	log.trace("START");
		dao = new EntityDao(conn);
	
    	log.trace("END");
    }
    
    
	public EntityController() {
    	super();
    	log.trace("START");
        try {
			dao = new EntityDao();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			log.debug("NamingException");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.debug("SQLException");
			e.printStackTrace();
		}
    	log.trace("END");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.trace("START");
    	String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteRecord(id);
            forward = LIST_RECORDS;
            request.setAttribute("records", dao.getAllRecords());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            Entity record = dao.getRecordById(id);
            request.setAttribute("record", record);
        } else if (action.equalsIgnoreCase("listRecords")){
            forward = LIST_RECORDS;
            request.setAttribute("records", dao.getAllRecords());
        } else {
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    	log.trace("END");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.trace("START");
    	Entity record = new Entity();
    	record.setFname(request.getParameter("fname"));
    	record.setLname(request.getParameter("lname"));
    	record.setDate_of_birth(request.getParameter("date_of_birth"));
    	record.setPassword(request.getParameter("password"));
    	record.setEmail(request.getParameter("email"));
    	record.setRole(request.getParameter("role"));
        String id = request.getParameter("id");
        if(id == null || id.isEmpty())
        {
            dao.addRecord(record);
            log.debug("Record inserted");
        }
        else
        {
            record.setId(Integer.parseInt(id));
            dao.updateRecord(record);
            log.debug("Record updated");
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_RECORDS);
        request.setAttribute("records", dao.getAllRecords());
        view.forward(request, response);
    	log.trace("END");
    }
}