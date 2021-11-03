package application.model;

import java.util.Date;

public class Plan { //java already has an Event class built in so i went with this instead
	
	private Date date;
	private String time;
	private String name;
	private boolean remind;
	private String note;
	
	public Plan( Date date, String time, String name, boolean remind, String note ) {
		
		this.date = date;
		this.time = time;
		this.name = name;
		this.remind = remind;
		this.note = note;
		
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate( Date date ) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRemind() {
		return remind;
	}

	public void setRemind(boolean remind) {
		this.remind = remind;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
