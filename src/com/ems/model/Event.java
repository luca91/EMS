package com.ems.model;

/**
* User is the JavaBean representing the record of the table Event
* 
* @author Luca Barazzuol
*/
public class Event {


	
	/**
	 * @uml.property  name="id"
	 */
	private int id;
	/**
	 * @uml.property  name="id_manager"
	 */
	private int id_manager;
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="description"
	 */
	private String description;
	/**
	 * @uml.property  name="start"
	 */
	private String start;
	/**
	 * @uml.property  name="end"
	 */
	private String end;
	/**
	 * @uml.property  name="enrollment_start"
	 */
	private String enrollment_start;
	/**
	 * @uml.property  name="enrollment_end"
	 */
	private String enrollment_end;

	/**
	* Constructor with NO parameters
	* 
	* @author Luca Barazzuol
	*/
	public Event() {

	}
	
	
	/**
	* Constructor with all parameters
	* 
	* @author Luca Barazzuol
	*/
	public Event(int id, int id_manager, String name, String description,
			String start, String end, String enrollment_start,
			String enrollment_end) {
		super();
		this.id = id;
		this.id_manager = id_manager;
		this.name = name;
		this.description = description;
		this.start = start;
		this.end = end;
		this.enrollment_start = enrollment_start;
		this.enrollment_end = enrollment_end;
	}



	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return
	 * @uml.property  name="id_manager"
	 */
	public int getId_manager() {
		return id_manager;
	}



	/**
	 * @param id_manager
	 * @uml.property  name="id_manager"
	 */
	public void setId_manager(int id_manager) {
		this.id_manager = id_manager;
	}



	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return
	 * @uml.property  name="description"
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return
	 * @uml.property  name="start"
	 */
	public String getStart() {
		return start;
	}



	/**
	 * @param start
	 * @uml.property  name="start"
	 */
	public void setStart(String start) {
		this.start = start;
	}



	/**
	 * @return
	 * @uml.property  name="end"
	 */
	public String getEnd() {
		return end;
	}



	/**
	 * @param end
	 * @uml.property  name="end"
	 */
	public void setEnd(String end) {
		this.end = end;
	}



	/**
	 * @return
	 * @uml.property  name="enrollment_start"
	 */
	public String getEnrollment_start() {
		return enrollment_start;
	}



	/**
	 * @param enrollment_start
	 * @uml.property  name="enrollment_start"
	 */
	public void setEnrollment_start(String enrollment_start) {
		this.enrollment_start = enrollment_start;
	}



	/**
	 * @return
	 * @uml.property  name="enrollment_end"
	 */
	public String getEnrollment_end() {
		return enrollment_end;
	}



	/**
	 * @param enrollment_end
	 * @uml.property  name="enrollment_end"
	 */
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
