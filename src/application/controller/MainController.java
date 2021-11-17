/**
 * MainController handles populating the main scene and handles navigation to 
 * other views of the application
 * 
 * @author Tanner Bibb - ybw098
 * 
 * UTSA CS 3443 - Final_Project - TheLifeSaver
 */
package application.controller;

import javafx.event.EventHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.fxml.*;

public class MainController implements EventHandler<ActionEvent> {

	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private Button monthlyBtn;
	
	@FXML
	private Button weeklyBtn;
	
	@FXML
	private Button dailyBtn;
	
	@FXML
	private Button remindBtn;
	
	/**
	 * Handles the button press events on the main view for navigating to other views
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {
		
		Button tmp = (Button) event.getSource();
		FXMLLoader loader;
		AnchorPane newPane;
		
		try {
		
			switch(tmp.getText()) {
			
			case "To-Do List": 
				loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Daily.fxml"));
				newPane = (AnchorPane) loader.load();
				rootPane.getChildren().setAll(newPane);
				break;
				
			case "Monthly": 
				loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Monthly.fxml"));
				newPane = (AnchorPane) loader.load();
				rootPane.getChildren().setAll(newPane);
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
				LocalDateTime now = LocalDateTime.now();
				String month = now.format(dtf).split("/")[0], year = now.format(dtf).split("/")[2];
				CalenderController controller = loader.getController();
				controller.initialize(month, year);
				break;
				
			case "Reminders":
				loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Reminders.fxml"));
				newPane = (AnchorPane) loader.load();
				rootPane.getChildren().setAll(newPane);
				break;
				
			case "Weekly": 
				loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Weekly.fxml"));
				newPane = (AnchorPane) loader.load();
				rootPane.getChildren().setAll(newPane);
				break;
			}
			
			
		}
		catch(Exception e) {e.printStackTrace();}
		
		
	}

}
