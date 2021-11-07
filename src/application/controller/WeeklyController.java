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
import java.util.Date;
import java.util.ResourceBundle;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;
import static java.time.DayOfWeek.MONDAY;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WeeklyController implements EventHandler<ActionEvent>, Initializable {

	@FXML AnchorPane rootPane;
	@FXML Button backButton;
	@FXML GridPane weeklyGrid;
	@FXML Label MonthName;
    @FXML
    private Button monButton;
    @FXML
    private Button friButton;

    @FXML
    private Button satButton;

    @FXML
    private Button thursButton;

    @FXML
    private Button wedButton;

    @FXML
    private Button sunButton;

    @FXML
    private Button tuesButton;
 
	
    public void initialize(URL location, ResourceBundle resources){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		LocalDateTime now = LocalDateTime.now();
		String month = now.format(dtf).split("/")[0];
		
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
		case "TUESDAY":
			sun=sun.minusDays(2);
			mon=mon.minusDays(1);
			tue.getDayOfWeek();
			wed=wed.plusDays(1);
			thurs=thurs.plusDays(2);
			fri=fri.plusDays(3);
			end=end.plusDays(4);
		case "WEDNESDAY":
			sun=sun.minusDays(3);
			mon=mon.minusDays(2);
			tue=tue.minusDays(1);
			wed.getDayOfWeek();
			thurs=thurs.plusDays(1);
			fri=fri.plusDays(2);
			end=end.plusDays(3);
		case "THURSDAY":
			sun=sun.minusDays(4);
			mon=mon.minusDays(3);
			tue=tue.minusDays(2);
			wed=wed.minusDays(1);
			thurs.getDayOfWeek();
			fri=fri.plusDays(1);
			end=end.plusDays(2);
		case "FRIDAY":
			sun=sun.minusDays(5);
			mon=mon.minusDays(4);
			tue=tue.minusDays(3);
			wed=wed.minusDays(2);
			thurs=thurs.minusDays(1);
			fri.getDayOfWeek();
			end=end.plusDays(1);
		case "SATURDAY":
			sun=sun.minusDays(6);
			mon=mon.minusDays(5);
			tue=tue.minusDays(4);
			wed=wed.minusDays(3);
			thurs=thurs.minusDays(2);
			fri=fri.minusDays(1);
			end.getDayOfWeek();
		}
		
		
		
	    
	    

	    System.out.println("Today: " + now);
	    System.out.println("Start of the Week: " + sun);
	    System.out.println("End of the Week: " + end);
	    
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
	    
	

	    
		MonthName.setText(Month.of(Integer.parseInt(month)).name());
		
		Occation eventlist = new Occation();
		eventlist.loadEvents("data/monthlyEvents.csv");
		// Stopped here, coming back later
	}
	
	
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
