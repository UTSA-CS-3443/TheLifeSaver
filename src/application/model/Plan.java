package application.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
		String sdate = format.format(date);
		//its probably going to be easier if this returns a string so we dont have to call this code every time
		return sdate;
	}
	
	public String remindersDisplay() {
		return getDate() + " - " + getTime() + "\n\t" + getName() + "\n";
	}
	
	public Date getDateOb() {//just in case anyone wants the date object
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
	
	/* This may or may not be needed, gonna keep it for a little longer
	 * 
	public String convertToMilitary(String time) throws ParseException { // Make sure time is in format: "10:30 PM"
		
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
		Date date = parseFormat.parse(time);
		
		return displayFormat.format(date);
	}
	*/
	
	public String convertToStandard() {
		
		try {
		Date date = new SimpleDateFormat("hhmm").parse(String.format("%04d", Integer.parseInt(this.getTime())));
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		
		return sdf.format(date);
		}
		catch(ParseException e) {e.printStackTrace();}
		
		return null;
		
		
	}
	
}
