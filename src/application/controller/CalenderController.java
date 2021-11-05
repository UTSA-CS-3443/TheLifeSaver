package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import application.model.Occation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CalenderController implements javafx.event.EventHandler<Event>, Initializable{
	
	@FXML Text MonthName;
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
		switch( LocalDate.of( Integer.parseInt(now.format(dtf).split("/")[2]), Integer.parseInt(now.format(dtf).split("/")[0]), Integer.parseInt("01") ).getDayOfWeek().toString() ){
			case "MONDAY":		fdaypos = 0;
			break;
			case "TUESDAY": 	fdaypos = 1;
			break;
			case "WEDNESDAY":	fdaypos = 2;
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
		
		//load month data into the calender
		
		MonthName.setText(month);
		
		Node[][] gridPaneNodes = new Node[calenderGrid.getRowCount()][calenderGrid.getColumnCount()];
		for(Node child : calenderGrid.getChildren()) {
			
			Integer column = calenderGrid.getColumnIndex(child);
			Integer row = calenderGrid.getRowIndex(child);
			
			if(column != null && row != null)
				gridPaneNodes[column][row] = child;
		}
		
		for(int row = 0; row < calenderGrid.getRowCount(); row++) {
			for(int column = 0; column < calenderGrid.getColumnCount(); column++) {
				Node node = gridPaneNodes[column][row]; 
				
				// start accessing/modifying the node here...
			}
		}
		
		
		
		
		
		//load data from eventlist into the calender
		
		for( int i = 0; i < eventlist.getEvents().size(); i++ ) {
			if(eventlist.getEvents().get(i).getDate().split("/")[0].equals(month)) {
				System.out.println(eventlist.getEvents().get(i).getName()+"-"+eventlist.getEvents().get(i).getDate() );
				System.out.println(eventlist.getEvents().get(i).getNote());
			}
		}
		
	}

	@Override
	public void handle(Event event) {
		// TODO handle adding a new event to the calender
		// TODO handle deleting an event from the calender
		Button temp = (Button) event.getSource();
		AnchorPane newPane;
		try {
		
		switch(temp.getText()) {
		
		case "Monthly": 
			newPane = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/Monthly.fxml"));
			rootPane.getChildren().setAll(newPane);
			break;
		
		case "Weekly": 
			newPane = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/Weekly.fxml"));
			rootPane.getChildren().setAll(newPane);
			break;
			
		case "Daily": 
			newPane = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/Daily.fxml"));
			rootPane.getChildren().setAll(newPane);
			break;
			
		case "Reminders": 
			newPane = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/Reminders.fxml"));
			rootPane.getChildren().setAll(newPane);
			break;
			
		}
		
		}
		catch(Exception e) {e.printStackTrace();}
		// TODO handle navigating to other pages
		// TODO handle navigating to a different month
		
	}
}
