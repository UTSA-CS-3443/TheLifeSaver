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
	
	// Variable names will be btn1-4 until we actually finish the fxmls
	@FXML
	private Button monthlyBtn;
	
	@FXML
	private Button weeklyBtn;
	
	@FXML
	private Button dailyBtn;
	
	@FXML
	private Button remindBtn;
	
	@Override
	public void handle(ActionEvent event) {
		
		Button tmp = (Button) event.getSource();
		FXMLLoader loader;
		AnchorPane newPane;
		
		try {
		
			switch(tmp.getText()) {
			// **We can edit the case names if needed**
			
			case "Daily": 
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
				
				// So we can dynamically set the month in the calendar controller instead of being stuck with "now"
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
