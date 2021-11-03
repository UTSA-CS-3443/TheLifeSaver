package application.controller;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.fxml.*;

public class MainController implements EventHandler<ActionEvent> {

	@FXML
	private AnchorPane rootPane;
	
	// Variable names will be btn1-4 until we actually finish the fxmls
	@FXML
	private Button btn1;
	
	@FXML
	private Button btn2;
	
	@FXML
	private Button btn3;
	
	@FXML
	private Button btn4;
	
	@Override
	public void handle(ActionEvent event) {
		
		Button tmp = (Button) event.getSource();
		AnchorPane newPane;
		
		try {
		
			switch(tmp.getText()) {
			// **We can edit the case names if needed**
			
			case "Daily": 
				newPane = FXMLLoader.load(getClass().getClassLoader().getResource("view/Daily.fxml"));
				rootPane.getChildren().setAll(newPane);
				break;
				
			case "Monthly": 
				newPane = FXMLLoader.load(getClass().getClassLoader().getResource("view/Monthly.fxml"));
				rootPane.getChildren().setAll(newPane);
				break;
				
			case "Reminders":
				newPane = FXMLLoader.load(getClass().getClassLoader().getResource("view/Reminders.fxml"));
				rootPane.getChildren().setAll(newPane);
				break;
				
			case "Weekly": 
				newPane = FXMLLoader.load(getClass().getClassLoader().getResource("view/Weekly.fxml"));
				rootPane.getChildren().setAll(newPane);
				break;
			}
			
			
		}
		catch(Exception e) {e.printStackTrace();}
		
		
	}

}
