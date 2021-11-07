package application.model;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Weekly {
	
	public Weekly() {
		
	}
	
	//method to find start of week
	public static Date getWeekStartDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//goes backward to monday, to start week
		while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        c.add(Calendar.DATE, -1);
	    }
		return c.getTime();
	}
	
	//method to find end of week
	public static Date getWeekEndDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//go forward until it gets to the monday
		while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        c.add(Calendar.DATE, 1);
	    }
		//go back one, since the loop above goes to monday
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}
	
	


}
