/**
 * DailyController is responsible for adding/removing tasks from the
 * Daily view
 * 
 * @author Jade Manriquez
 * 
 * UTSA CS 3443 - Final_Project - TheLifeSaver
 */
package application.controller;

import application.controller.CalenderController;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.model.ToDoItem;
import application.model.ToDo;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class DailyController implements EventHandler<ActionEvent>, Initializable {
	
	/**
	 * Class/FXML variables
	 */
	@FXML AnchorPane rootPane, addEventRoot;
	@FXML Button backButton, addButton, addEventAdd, addEventX;
	@FXML TextField addEventName, addEventTime, addEventNotes;
	@FXML DatePicker addEventDate;
	@FXML Label MonthName, addEventError;
	@FXML CheckBox itemCheckbox;
	
	private ToDoItem myItems;

	@FXML private CheckBox item1;
	@FXML private CheckBox item2;
	@FXML private CheckBox item3;
	@FXML private CheckBox item4;
	@FXML private CheckBox item5;
	@FXML private CheckBox item6;
	@FXML private CheckBox item7;
	@FXML private CheckBox item8;
	@FXML private CheckBox item9;
	@FXML private CheckBox item10;
	@FXML private CheckBox item11;
	@FXML private CheckBox item12;
	@FXML private CheckBox item13;
	@FXML private CheckBox item14;
	@FXML private CheckBox item15;

	private CheckBox[] boxes;
	
		public void initialize(URL location, ResourceBundle resources) {
			addEventRoot.setVisible(false);
			addEventError.setVisible(false);
			boxes = new CheckBox[]{item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13, item14, item15};
			myItems = new ToDoItem();
			myItems.loadItems("data/items.csv");
			notVisible();
			ArrayList<ToDo> myItemsArr = myItems.getItems();
			System.out.println(myItemsArr.size());
			LocalDate ld = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String formattedString = ld.format(formatter);
			for(int i = 0; i < myItemsArr.size(); i++) {
				if(myItemsArr.get(i).getDate().equals(formattedString)) {
				//System.out.println(myItemsArr.get(i).getDate()  + myItemsArr.get(i).getName()+ myItemsArr.get(i).convertToStandard());
				boxes[i].setText(myItemsArr.get(i).getDate()  +" - " + myItemsArr.get(i).getName()+ " at " + myItemsArr.get(i).convertToStandard());
				boxes[i].setVisible(true);
				}
			}
		}
		
		@Override
		public void handle(ActionEvent event) {
			for(int i = 0; i < 15; i++) {
			if(boxes[i].isSelected()) {
				boxes[i].setDisable(true);
				//myItems.getItems().remove(i);

				}
			}
			
		}
	

		public void goBack(ActionEvent event) {
			try {
				AnchorPane root = new AnchorPane();
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( Main.class.getResource("view/Main.fxml") );
				root = (AnchorPane) loader.load();
		
				Scene scene = new Scene( root );
				Main.stage.setScene( scene );
				Main.stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		

		public void notVisible() {
			item1.setVisible(false);
			item2.setVisible(false);
			item3.setVisible(false);
			item4.setVisible(false);
			item5.setVisible(false);
			item6.setVisible(false);
			item7.setVisible(false);
			item8.setVisible(false);
			item9.setVisible(false);
			item10.setVisible(false);
			item11.setVisible(false);
			item12.setVisible(false);
			item13.setVisible(false);
			item14.setVisible(false);
			item15.setVisible(false);
		}	
		

		public void addEventGuiHandler(Event event) {
		
			if(event.getSource().equals(addButton)) {
				addEventRoot.setVisible(true);
				addEventError.setVisible(false);
			}
			else if(event.getSource().equals(addEventX)) {
				addEventName.clear();
				addEventDate.setValue(null);
				addEventTime.clear();
				addEventRoot.setVisible(false);
				
			}
			else if(event.getSource().equals(addEventAdd)) {
				
				if(addEventName.getText().trim().isEmpty() || ( addEventDate.getValue() == null && !CalenderController.isValidDate( addEventDate.getEditor().getText() ) ) || addEventTime.getText().trim().isEmpty()) {
					addEventName.clear();
					addEventDate.setValue(null);
					addEventTime.clear();
					addEventError.setVisible(true);
				}
				else {
					
					addEventRoot.setVisible(false);
					
					String temp[];
					String newDate;
					
					try {
					if(addEventDate.getValue() == null ){
						temp = addEventDate.getEditor().getText().split("/");
						newDate = String.format("%s/%s/%s", temp[0], temp[1], temp[2] );
					}
					else {
						temp = addEventDate.getValue().toString().split("-");
						newDate = String.format("%s/%s/%s", temp[1], temp[2], temp[0]);
					}
					
					myItems.addToFile(new ToDo(newDate, addEventTime.getText(),addEventName.getText()));
					
					FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Daily.fxml"));
					AnchorPane newPane = (AnchorPane) loader.load();
					rootPane.getChildren().setAll(newPane);
					
					
					}catch(Exception e) {
						e.printStackTrace();}
				}
				
			}
		}
		
}
