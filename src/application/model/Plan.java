package application.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Plan { 
	
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
	
	public Plan( String date, String time, String name, boolean remind, String note ) {
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			this.date = (Date)formatter.parse(date);
		} catch (ParseException e) {
	
			e.printStackTrace();
		}
		this.time = time;
		this.name = name;
		this.remind = remind;
		this.note = note;
		
	}

	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
		String sdate = format.format(date);
		
		return sdate;
	}
	public String getDay() {
		SimpleDateFormat format = new SimpleDateFormat("EEEE"); 
		String sday = format.format(date);
		
		return sday;
	}
	
	public String remindersDisplay() {
		return getDate() + " - " + convertToStandard() + ": " + getName() + "\n";

	}
	
	public Date getDateOb() {
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
	
	public String convertToStandard() {
		
		try {
		Date date = new SimpleDateFormat("hhmm").parse(String.format("%04d", Integer.parseInt(getTime())));
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		
		return sdf.format(date);
		}
		catch(ParseException e) {e.printStackTrace();}
		
		return null;
		
		
	}
	
}
