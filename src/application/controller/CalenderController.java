/**
 * CalenderController handles the creation and adding of events to the cells of the Monthly calendar,
 * as well as general formatting of the calendar itself
 * 
 * @author Tanner Bibb - ybw098
 * @author Calvin Newcomb - <ENTER abc123 HERE>
 * 
 * UTSA CS 3443 - Final_Project - TheLifeSaver
 */

package application.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import application.model.*;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.event.Event;
import javafx.scene.control.CheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.GregorianCalendar;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.Node;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CalenderController implements javafx.event.EventHandler<Event>{
	
	/**
	 * Class/FXML Variables
	 */
	@FXML AnchorPane rootPane, addEventRoot, removeEventRoot;
	@FXML Button backButton, addButton, addEventAdd, addEventX, incrementMonth, decrementMonth;
	@FXML Button removeButton, removeEventX, removeEventRemove;
	@FXML TextField addEventName, addEventTime, addEventNotes;
	@FXML ComboBox removeEventScroller;
	@FXML DatePicker addEventDate;
	@FXML Label MonthName, addEventError, removeEventError;
	@FXML CheckBox reminderCheckbox;
	@FXML GridPane calenderGrid;
	static String globalMonth;
	Occation eventlist;
	String year;
	
	/**
	 * Populates the monthly calendar view when scene loads
	 * @param month
	 * @param year
	 */
	public void initialize(String month, String year) {
		// TODO Auto-generated method stub
		//load in the data of the current month
		this.year = year;

		if(month.charAt(0) == '0')
			month = String.valueOf(month.charAt(1));
			
		globalMonth = month;
		
		if(globalMonth.equals("12"))
			incrementMonth.setVisible(false);
		else if(globalMonth.equals("1"))
			decrementMonth.setVisible(false);
		
		addEventRoot.setVisible(false);
		addEventError.setVisible(false);
		
		removeEventRoot.setVisible(false);
		
		eventlist = new Occation();
		eventlist.loadEvents("data/monthlyEvents.csv");
		
		//find the number of days in the month
		Calendar c = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int fdaypos = -1;
		
		//find the position in the week we will start the month in
		
		switch( LocalDate.of( Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt("01") ).getDayOfWeek().toString() ){
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
		
		
		//load month data into the calendar--------------------------------
		
		MonthName.setText(Month.of(Integer.parseInt(month)).name());
		
		int ct = 0, currDay = 1;
		boolean flag = false;
		String filler;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String actualCurrentMonth = now.format(dtf).split("/")[1];
		String actualCurrentDay = now.format(dtf).split("/")[2].split(" ")[0];

		System.out.println(actualCurrentDay);
		if(month.length() == 1)
			month = "0" + month;
		
		for (Node node: calenderGrid.getChildren()) {
	
			 // The actual text that'll be put in the cell(s)
			  filler = "";
			  
			  // Don't start writing the day & event info until we reach the
			  // First day of the week that the month begins on
			  if (ct == fdaypos)
			    flag = true;

			  filler += String.format("%s\n", String.valueOf(currDay)); 
			  
			  // Adds the event name to the correct date as long as the month and the day match the one on file
			  for (Plan event: eventlist.getEvents()) 
			    if (event.getDate().split("/")[2].equals(year) && event.getDate().split("/")[0].equals(month) && (event.getDate().split("/")[1].equals("0" + String.valueOf(currDay)) || event.getDate().split("/")[1].equals(String.valueOf(currDay))))
			      filler += String.format("-%s\n+%s\n", event.getName(), event.convertToStandard());

			  
			  // Actually set the text of the label(s)
			  // You literally need to specify the "instanceof" condition, otherwise ERROR ERROR ERROR
			  if (node instanceof Label && flag && currDay <= days) {
				  
				if( Integer.parseInt(month) < Integer.parseInt(actualCurrentMonth) )
					((Label) node).setStyle("-fx-text-fill: red;");
				else if(Integer.parseInt(month) == Integer.parseInt(actualCurrentMonth))
					if(Integer.parseInt(actualCurrentDay) > currDay)
						((Label) node).setStyle("-fx-text-fill: red;");
					
			    ((Label) node).setText(filler);
			    currDay++;
			  } 
			  else if (node instanceof Label && ct >= days) {
				  ((Label) node).setText("");
				  ((Label) node).setStyle("-fx-background-color:#d4d4d4;");
			  }
			    

			  else if (node instanceof Label && !flag) {
				  ((Label) node).setText(""); 
				  ((Label) node).setStyle("-fx-background-color:#d4d4d4;");
			  }

			  ct++;

			}
		
		//--------------------------------------------------------------
		
		
		//Print event data to console for testing purposes
		
		for( int i = 0; i < eventlist.getEvents().size(); i++ ) {
			if(eventlist.getEvents().get(i).getDate().split("/")[0].equals(month)) {
				System.out.print(eventlist.getEvents().get(i).getName()+" - "+eventlist.getEvents().get(i).getDate() );
				System.out.print(" - "+eventlist.getEvents().get(i).convertToStandard());
				System.out.println("\n" + eventlist.getEvents().get(i).getNote() + "\n");
			}
		}
		
		addEventDate.setConverter(new StringConverter<LocalDate>() {
			
			String pattern = "MM/dd/yyyy";
			
		    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		    
		    {
		    	addEventDate.setPromptText(pattern.toLowerCase() );
		    }
		    
		    @Override public String toString(LocalDate Date) {
		    	if( Date != null ) {
		    		return dateFormatter.format(Date);
		    	}else { return ""; }
		    		
		    }
		    
		    @Override public LocalDate fromString(String string) {
		    	if(string != null && !string.isEmpty()) {
		    		return LocalDate.parse(string,dateFormatter);
		    	}else {
		    		return null;
		    	}
		    }
		});
		
	}

	/**
	 * Handles the click events/Button press events on the Monthly calendar scene
	 * @param event
	 */
	@Override
	public void handle(Event event) {
	
		Button temp = (Button) event.getSource();
		FXMLLoader loader;
		AnchorPane newPane;
		try {
			
			if(temp.equals(backButton)) {
				newPane = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/Main.fxml"));
				rootPane.getChildren().setAll(newPane);
			}	
			
			else if(temp.equals(incrementMonth)) {
			
				String newMonth = String.valueOf(Integer.parseInt(globalMonth) + 1);
				
			    loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Monthly.fxml"));
			    newPane = (AnchorPane) loader.load();
				rootPane.getChildren().setAll(newPane);
				
				CalenderController controller = loader.getController();
				controller.initialize(newMonth, year);
			}
			
			else if(temp.equals(decrementMonth)) {
				
				String newMonth = String.valueOf(Integer.parseInt(globalMonth) - 1);
				
			    loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Monthly.fxml"));
			    newPane = (AnchorPane) loader.load();
				rootPane.getChildren().setAll(newPane);
				
				CalenderController controller = loader.getController();
				controller.initialize(newMonth, year);
			}
		}
		catch(Exception e) {e.printStackTrace();}
		
	}
	
	
	/**
	 * Handles click events/Button press events on the event adder gui 
	 * @param event
	 */
	public void addEventGuiHandler(Event event) {
		
		
		
		
		if(event.getSource().equals(addButton)) {
			addEventRoot.setVisible(true);
			addEventError.setVisible(false);
			
			if(removeEventRoot.isVisible())
				removeEventRoot.setVisible(false);
		}
		else if(event.getSource().equals(addEventX)) {
			addEventName.clear();
			addEventDate.setValue(null);
			addEventTime.clear();
			addEventNotes.clear();
			addEventRoot.setVisible(false);
			
		}
		else if(event.getSource().equals(addEventAdd)) {
			
			if(addEventName.getText().trim().isEmpty() || ( addEventDate.getValue() == null && !isValidDate( addEventDate.getEditor().getText() ) ) || addEventTime.getText().trim().isEmpty()) {
				addEventName.clear();
				addEventDate.setValue(null);
				addEventTime.clear();
				addEventError.setVisible(true);
			}
			else {
				
				System.out.println(addEventDate.getEditor().getText());
				
				addEventRoot.setVisible(false);
				
				String vals[];
				String newDate;
				
				// CREATE THE EVENT WITH THE INFO HERE
				try {
				if(addEventDate.getValue() == null ){
					vals = addEventDate.getEditor().getText().split("/");
					newDate = String.format("%s/%s/%s", vals[0], vals[1], vals[2] );
				}
				else {
					vals = addEventDate.getValue().toString().split("-");
					newDate = String.format("%s/%s/%s", vals[1], vals[2], vals[0]);
				}
				
				//append the new event to the end of the file
				eventlist.appendToFile(new Plan(newDate, addEventTime.getText(),addEventName.getText(),reminderCheckbox.isSelected(), addEventNotes.getText() ));
				
				//reload the view
				FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Monthly.fxml"));
				AnchorPane newPane = (AnchorPane) loader.load();
				rootPane.getChildren().setAll(newPane);
				
				CalenderController controller = loader.getController();
				controller.initialize(newDate.split("/")[0], year);
				
				}catch(Exception e) {e.printStackTrace();}
			}
			
		}
	}
	
	public void removeEventGuiHandler(Event event) {
		
		Occation eventList = new Occation(), copyList = new Occation();
		eventList.loadEvents("data/monthlyEvents.csv");
		copyList.loadEvents("data/monthlyEvents.csv");
		
		
		int i = 1;
		for(Plan eventObj : eventList.getEvents()) {
			removeEventScroller.getItems().add(i + " .) " + eventObj.remindersDisplay());
			i++;
		}
		
		if(event.getSource().equals(removeButton)) {

			if(addEventRoot.isVisible())
				addEventRoot.setVisible(false);
			
			removeEventError.setVisible(false);
			removeEventScroller.valueProperty().set(null);
			removeEventRoot.setVisible(true);	
		}
		else if(event.getSource().equals(removeEventX))
			removeEventRoot.setVisible(false);
		
		else if(event.getSource().equals(removeEventRemove)) {
			
			if(removeEventScroller.getValue() == null)
				removeEventError.setVisible(true);
			
			else {
				
				Plan eventToRemove = copyList.getEvents().get(Integer.parseInt(removeEventScroller.getValue().toString().split(" ")[0]) - 1);
				copyList.removeEvent("data/monthlyEvents.csv", eventToRemove);
				
				try {
				FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Monthly.fxml"));
				AnchorPane newPane = (AnchorPane) loader.load();
				rootPane.getChildren().setAll(newPane);
				CalenderController control = loader.getController();
				control.initialize(eventToRemove.getDate().split("/")[0], year);
				}catch(Exception e) {e.printStackTrace();}
				
			}
			
		}
		
	}
	
	/**
	 * Checks if a date typed in a DatePicker field is valid
	 * @param date
	 * @return boolean value of a date string
	 */
	public static boolean isValidDate(String date) {
		String split[] = date.split("/");
		if( split.length < 3 ) {
			return false;
		}
		if( Integer.parseInt(split[0]) > 12 ) {
			return false;
		}
		Calendar d = new GregorianCalendar(Integer.parseInt(split[0]), Integer.parseInt(split[0]) - 1, 1);
		int maxPosDays = d.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(Integer.parseInt(split[1]) > maxPosDays ) {
			return false;
		}
		return true;
	}
}
