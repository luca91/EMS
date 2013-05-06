package com.ems.model;

/**
* User is the JavaBean representing the record of the table Group
* 
* @author Luca Barazzuol
*/
public class Group {

	private int id;
	private int id_event;
	private int id_group_referent;
	private int max_group_number;
	private boolean blocked;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getId_event() {
		return id_event;
	}
	public void setId_event(int id_event) {
		this.id_event = id_event;
	}

	public int getId_group_referent() {
		return id_group_referent;
	}
	public void setId_group_referent(int id_group_referent) {
		this.id_group_referent = id_group_referent;
	}

	public int getMax_group_number() {
		return max_group_number;
	}
	public void setMax_group_number(int max_group_number) {
		this.max_group_number = max_group_number;
	}

	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	@Override
    public String toString() {
        return "User [id=" + id + ", id_event=" + id_event + ", id_group_referent=" + id_group_referent 
                + ", max_group_number=" + max_group_number + ", blocked=" + blocked  +"]";
    } 
	
	
}
