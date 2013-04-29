package com.ems.model;

public class Event {


	
	private int id;
	private int id_manager;
	private String name;
	private String description;
	private String start;
	private String end;
	private String enrollment_start;
	private String enrollment_end;

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getId_manager() {
		return id_manager;
	}



	public void setId_manager(int id_manager) {
		this.id_manager = id_manager;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getStart() {
		return start;
	}



	public void setStart(String start) {
		this.start = start;
	}



	public String getEnd() {
		return end;
	}



	public void setEnd(String end) {
		this.end = end;
	}



	public String getEnrollment_start() {
		return enrollment_start;
	}



	public void setEnrollment_start(String enrollment_start) {
		this.enrollment_start = enrollment_start;
	}



	public String getEnrollment_end() {
		return enrollment_end;
	}



	public void setEnrollment_end(String enrollment_end) {
		this.enrollment_end = enrollment_end;
	}



	@Override
    public String toString() {
        return "User [id=" + id + ", id_manager=" + id_manager
                + ", description=" + description + ", start=" + start + ", end="
                + end + ", " + "enrollment_start=" + enrollment_start + "enrollment_end=" + enrollment_end +"]";
    } 
	
	
}
