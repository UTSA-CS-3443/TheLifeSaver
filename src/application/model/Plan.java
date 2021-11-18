/**
 * Plan is responsible for creating "event" objects that contain all information pertaining
 * to a specific event, e.g. name, time, date, notes etc.
 * 
 * @author Calvin Newcomb - vbr868
 * @author Tanner Bibb - ybw098
 * @author Molly Frost - iav811
 * @author Leann Dunaway -ajb733
 * 
 * UTSA CS 3443 - Final_Project - TheLifeSaver
 */

package application.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Plan { 
	
	/**
	 * Class variables
	 */
	private Date date;
	private String time;
	private String name;
	private boolean remind;
	private String note;
	
	/**
	 * Constructor
	 * @param date
	 * @param time
	 * @param name
	 * @param remind
	 * @param note
	 */
	public Plan( Date date, String time, String name, boolean remind, String note ) {
		
		this.date = date;
		this.time = time;
		this.name = name;
		this.remind = remind;
		this.note = note;
		
	}
	
	/**
	 * Overload Constructor (Kinda)
	 * @param date
	 * @param time
	 * @param name
	 * @param remind
	 * @param note
	 */
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

	/**
	 * Gets the date
	 * @return the date
	 */
	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
		String sdate = format.format(date);
		
		return sdate;
	}
	
	/**
	 * Gets the day
	 * @return the day
	 */
	public String getDay() {
		SimpleDateFormat format = new SimpleDateFormat("EEEE"); 
		String sday = format.format(date);
		
		return sday;
	}
	
	/**
	 * Outputs a Stringified instance of an event
	 * @return String interpretation of an event
	 */
	public String remindersDisplay() {
		return getDate() + " - " + convertToStandard() + ": " + getName() + "\n";

	}
	
	/**
	 * Gets the date as an object
	 * @return date object
	 */
	public Date getDateOb() {
		return date;
	}
	
	/**
	 * Sets the date
	 * @param date
	 */
	public void setDate( Date date ) {
		this.date = date;
	}
	
	/**
	 * Gets the time
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * Sets the time
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Gets the name of an event
	 * @return name of event
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of an event
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the boolean value determining whether an event is a reminder
	 * @return boolean
	 */
	public boolean isRemind() {
		return remind;
	}

	/**
	 * Sets the boolean value of an event reminder
	 * @param remind
	 */
	public void setRemind(boolean remind) {
		this.remind = remind;
	}

	/**
	 * Gets any additional notes attached to an event object
	 * @return event note(s)
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the notes of an event
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * Converts the time of an event (written in military) to standard time for easy reading
	 * @return Standard time in string format
	 */
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
