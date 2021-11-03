package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class CalenderController implements javafx.event.EventHandler<Event>, Initializable{
	
	@FXML GridPane CalenderGrid;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//load in the data of the current month
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		LocalDateTime now = LocalDateTime.now();
		String month = now.format(dtf).split("/")[0];
		
		
		//Occation eventlist = new Occation();
		//eventlist.loadData("data/monthlyEvents.csv");
		
		//find the number of days in the month
		Calendar c = Calendar.getInstance();
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int fdaypos = -1;
		//find the position in the week we will start the month in
		switch( LocalDate.of( Integer.parseInt(now.format(dtf).split("/")[2]), Integer.parseInt(now.format(dtf).split("/")[0]), Integer.parseInt("01") ).getDayOfWeek().toString() ){
			case "MONDAY":		fdaypos = 0;
			break;
			case "TUESDAY": 	fdaypos = 1;
			break;
			case "WENESDAY":	fdaypos = 2;
			break;
			case "THURSDAY":	fdaypos = 3;
			break;
			case "FRIDAY":		fdaypos = 4;
			break;
			case "SATURRDAY":	fdaypos = 5;
			break;
			case "SUNDAY":		fdaypos = 6;
			default: System.out.println("fdaypos reader is broken");
		}
		
		//find how many rows the calender grid will need
		
		int weeks = (fdaypos + days - 1 ) / 7;
		if((fdaypos + days - 1 ) % 7 != 0 ) {
			weeks++;
		}
		
		System.out.println(month);
		System.out.println(days);
		System.out.println(fdaypos);
		System.out.println(weeks);
		
		//load month data into the calender
		
		//load data from eventlist into the calender
		
	}

	@Override
	public void handle(Event event) {
		// TODO handle adding a new event to the calender
		// TODO handle deleting an event from the calender
		// TODO handle navigating to other pages
		// TODO handle navigating to a different month
		
	}
}
