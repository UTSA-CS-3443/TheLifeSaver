/**
 * WeeklyController takes the current day and gives a weekly layout of the specific events
 * of that week. 
 * 
 * @author Tanner Bibb - ybw098
 * @author Leann Dunaway - ajb733
 * 
 * UTSA CS 3443 - Final_Project - TheLifeSaver
 */
package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.EventHandler;
import application.model.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;


import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WeeklyController implements EventHandler<ActionEvent>, Initializable {

	/**
	 * Class/FXML variables
	 */
	@FXML AnchorPane rootPane;
	@FXML Button backButton;
	@FXML GridPane weeklyGrid;
	@FXML Label MonthName;
	@FXML Button incrementWeekh;
    @FXML Button decrementWeek;
	
    @FXML private Button monButton;
    @FXML private Button friButton;
    @FXML private Button satButton;
    @FXML private Button thursButton;
    @FXML private Button wedButton;
    @FXML private Button sunButton;
    @FXML private Button tuesButton;
   
	/**
	 * Populates the weekly view with the correct dates of that week
	 * and the events of that week before the scene fully loads
	 * @param location, URL
	 * @param resources, ResourceBundle
	 */
	@Override
    public void initialize(URL location, ResourceBundle resources){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		LocalDateTime now = LocalDateTime.now();
		String month = now.format(dtf).split("/")[0];
		String day = now.format(dtf).split("/")[1];
		
	    LocalDateTime sun=now;
		LocalDateTime mon = now;
		LocalDateTime tue = now;
		LocalDateTime wed = now;
		LocalDateTime thurs = now;
		LocalDateTime fri = now;
		LocalDateTime end = now;
		
		switch(now.getDayOfWeek().toString()) {
		case "SUNDAY":
			sun.getDayOfWeek();
			mon=mon.plusDays(1);
			tue=tue.plusDays(2);
			wed=wed.plusDays(3);
			thurs=thurs.plusDays(4);
			fri=fri.plusDays(5);
			end=end.plusDays(6);
			break;
		case "MONDAY":
			sun=sun.minusDays(1);
			mon.getDayOfWeek();
			tue=tue.plusDays(1);
			wed=wed.plusDays(2);
			thurs=thurs.plusDays(3);
			fri=fri.plusDays(4);
			end=end.plusDays(5);
			break;
		case "TUESDAY":
			sun=sun.minusDays(2);
			mon=mon.minusDays(1);
			tue.getDayOfWeek();
			wed=wed.plusDays(1);
			thurs=thurs.plusDays(2);
			fri=fri.plusDays(3);
			end=end.plusDays(4);
			break;
		case "WEDNESDAY":
			sun=sun.minusDays(3);
			mon=mon.minusDays(2);
			tue=tue.minusDays(1);
			wed.getDayOfWeek();
			thurs=thurs.plusDays(1);
			fri=fri.plusDays(2);
			end=end.plusDays(3);
			break;
		case "THURSDAY":
			sun=sun.minusDays(4);
			mon=mon.minusDays(3);
			tue=tue.minusDays(2);
			wed=wed.minusDays(1);
			thurs.getDayOfWeek();
			fri=fri.plusDays(1);
			end=end.plusDays(2);
			break;
		case "FRIDAY":
			sun=sun.minusDays(5);
			mon=mon.minusDays(4);
			tue=tue.minusDays(3);
			wed=wed.minusDays(2);
			thurs=thurs.minusDays(1);
			fri.getDayOfWeek();
			end=end.plusDays(1);
			break;
		case "SATURDAY":
			sun=sun.minusDays(6);
			mon=mon.minusDays(5);
			tue=tue.minusDays(4);
			wed=wed.minusDays(3);
			thurs=thurs.minusDays(2);
			fri=fri.minusDays(1);
			end.getDayOfWeek();
			break;
		default:
			break;
		}
	    
	    String suns= sun.format(dtf).split("/")[1];
	    sunButton.setText(suns);
	    String ends= end.format(dtf).split("/")[1];
	    satButton.setText(ends);
	    String mons= mon.format(dtf).split("/")[1];
	    monButton.setText(mons);
	    String tues= tue.format(dtf).split("/")[1];
	    tuesButton.setText(tues);
	    String weds= wed.format(dtf).split("/")[1];
	    wedButton.setText(weds);
	    String thurss= thurs.format(dtf).split("/")[1];
	    thursButton.setText(thurss);
	    String fris= fri.format(dtf).split("/")[1];
	    friButton.setText(fris);
	    
	    String [] days= {suns,mons,tues,weds,thurss,fris,ends};
        MonthName.setText(Month.of(Integer.parseInt(month)).name());
		
		Occation eventlist = new Occation();
		eventlist.loadEvents("data/monthlyEvents.csv");
		
		for(int i = 0; i < days.length; i++) {
			checkEvent(days, eventlist, month);
		}
		
	}
	/**
	 * Method to check if any events are present in the current week
	 * @param String[]
	 * @param Occation
	 * @param String
	 */
	public void checkEvent(String[] days, Occation eventlist, String month) {
		
		int i = 0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String actualCurrentMonth = now.format(dtf).split("/")[1];
		String actualCurrentDay = now.format(dtf).split("/")[2].split(" ")[0];
		
		for(Node node : weeklyGrid.getChildren()) {
			
			String curr_day = "", total = "";
			
			if(node instanceof Label)
			curr_day = days[i++];
			
			for(Plan event : eventlist.getEvents()) {
				
				if(event.getDate().split("/")[1].equals(curr_day) && event.getDate().split("/")[0].equals(month)) 
					total += String.format("(%s)\n-%s\n-%s\n+%s\n\n", event.getDate(), event.getName(), event.convertToStandard(), event.getNote());
				else total += "";
			}
			
			if(node instanceof Label) {
				if( Integer.parseInt(month) < Integer.parseInt(actualCurrentMonth) )
					((Label) node).setStyle("-fx-text-fill: red;");
				else if(Integer.parseInt(month) == Integer.parseInt(actualCurrentMonth))
					if(Integer.parseInt(actualCurrentDay) > Integer.parseInt(curr_day))
						((Label) node).setStyle("-fx-text-fill: red;");
				
				
				((Label) node).setText(total);
			}
		}
		
	}
	
	/**
	 * Handles the click events/Button press events on the Weekly calendar scene
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {
		Button temp = (Button) event.getSource();
		AnchorPane newPane;
		
		try {
		
		if(temp.equals(backButton)) {
			newPane = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/Main.fxml"));
			rootPane.getChildren().setAll(newPane);
		}
		
		}catch(Exception e) {e.printStackTrace();}
		
	}
}
