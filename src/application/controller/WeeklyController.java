package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.EventHandler;
import application.model.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WeeklyController implements EventHandler<ActionEvent> {

	@FXML AnchorPane rootPane;
	@FXML Button backButton;
	@FXML GridPane weeklyGrid;
	@FXML Label MonthName;
    @FXML
    private Label SunLabel;
    @FXML
    private Label MonLabel;
	
	public void initialize() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		LocalDateTime now = LocalDateTime.now();
		String month = now.format(dtf).split("/")[0];
		
		//takes in date, gets the start and end of the week based
		//on current date
		Date today= new Date();
       Date w= Weekly.getWeekStartDate(today);
	   Date e= Weekly.getWeekStartDate(today);
		MonLabel.setText(w.toString());
		SunLabel.setText(e.toString());
	
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
