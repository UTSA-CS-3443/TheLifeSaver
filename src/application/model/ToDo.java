/**
 * ToDo is responsible for creating objects that contain all information pertaining
 * to a specific task, name, time, date
 * 
 * @author Jade Manriquez
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

public class ToDo { 
	
	/**
	 * Class variables
	 */
	private Date date;
	private String time;
	private String name;

	/**
	 * Constructor
	 * @param date
	 * @param time
	 * @param name
	 */
	public ToDo( Date date, String time, String name ) {
		
		this.date = date;
		this.time = time;
		this.name = name;

		
	}
	

	public ToDo( String date, String time, String name) {
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			this.date = (Date)formatter.parse(date);
		} catch (ParseException e) {
	
			e.printStackTrace();
		}
		this.time = time;
		this.name = name;

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
	

