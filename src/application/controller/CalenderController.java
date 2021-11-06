package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
//import java.util.List;
import java.util.ResourceBundle;
import application.model.Occation;
import application.model.*;
import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.Node;
//import javafx.scene.text.Text;

public class CalenderController implements javafx.event.EventHandler<Event>, Initializable{
	
	@FXML AnchorPane rootPane;
	@FXML Button backButton;
	@FXML Label MonthName;
	@FXML GridPane calenderGrid;
	
//	private ObservableList<ObservableList<>> weeks;
//	private ObservableList<> day; - 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//load in the data of the current month
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		LocalDateTime now = LocalDateTime.now();
		String month = now.format(dtf).split("/")[0];
		
		Occation eventlist = new Occation();
		eventlist.loadEvents("data/monthlyEvents.csv");
		
		//find the number of days in the month
		Calendar c = Calendar.getInstance();
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int fdaypos = -1;
		
		//find the position in the week we will start the month in
		// EDIT: Sunday is usually the start of a calendar, so index 
		switch( LocalDate.of( Integer.parseInt(now.format(dtf).split("/")[2]), Integer.parseInt(now.format(dtf).split("/")[0]), Integer.parseInt("01") ).getDayOfWeek().toString() ){
			case "MONDAY":		fdaypos = 1;
			break;
			case "TUESDAY": 	fdaypos = 2;
			break;
			case "WEDNESDAY":	fdaypos = 3;
			break;
			case "THURSDAY":	fdaypos = 4;
			break;
			case "FRIDAY":		fdaypos = 5;
			break;
			case "SATURDAY":	fdaypos = 6;
			break;
			case "SUNDAY":		fdaypos = 0;
			default: System.out.println("fdaypos reader is broken");
		}
		
		//find how many rows the calender grid will need
		
		int weeks = (fdaypos + days - 1 ) / 7;
		if((fdaypos + days - 1 ) % 7 != 0 ) {
			weeks++;
		}
		
		//load month data into the calender--------------------------------
		
		MonthName.setText(Month.of(Integer.parseInt(month)).name());
		
		int ct = 0, actual = 1;
		boolean flag = false;
		String filler;

		for (Node node: calenderGrid.getChildren()) {
			  
			 // The actual text that'll be put in the cell(s)
			  filler = "";
			  
			  // Don't start writing the day & event info until we reach the
			  // First day of the week that the month begins on
			  if (ct == fdaypos)
			    flag = true;

			  
			  // Sorry for the clusterfuck code, this took 4 hours to figure out and I'm slump

			  filler += String.format("%s\n", String.valueOf(actual)); 

			  
			  // Adds the event name to the correct date as long as the month and the day match the one on file
			  for (Plan event: eventlist.getEvents()) 
			    if (event.getDate().split("/")[0].equals(month) && (event.getDate().split("/")[1].equals("0" + String.valueOf(actual)) || event.getDate().split("/")[1].equals(String.valueOf(actual))))
			      filler += String.format("- %s\n", event.getName());

			  
			  // Actually set the text of the label(s)
			  // You literally need to specify the "instanceof" condition, otherwise ERROR ERROR ERROR
			  if (node instanceof Label && flag && actual <= days) {
			    ((Label) node).setText(filler);
			    actual++;
			  } else if (node instanceof Label && ct > days)
			    ((Label) node).setText("");

			  else if (node instanceof Label && !flag)
			    ((Label) node).setText("");

			  ct++;

			}
		
		//--------------------------------------------------------------
		
		
		//load data from eventlist into the calender
		
		for( int i = 0; i < eventlist.getEvents().size(); i++ ) {
			if(eventlist.getEvents().get(i).getDate().split("/")[0].equals(month)) {
				System.out.println(eventlist.getEvents().get(i).getName()+" - "+eventlist.getEvents().get(i).getDate() );
				System.out.println(eventlist.getEvents().get(i).getNote() + "\n");
			}
		}
		
	}

	@Override
	public void handle(Event event) {
		// TODO handle adding a new event to the calender
		// TODO handle deleting an event from the calender
		// TODO handle navigating to other pages
		Button temp = (Button) event.getSource();
		AnchorPane newPane;
		try {
		
			if(temp.equals(backButton)) {
				newPane = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/Main.fxml"));
				rootPane.getChildren().setAll(newPane);
			}
				
		}
		catch(Exception e) {e.printStackTrace();}
		// TODO handle navigating to a different month
		
	}
}
