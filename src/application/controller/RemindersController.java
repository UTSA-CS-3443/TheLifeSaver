package application.controller;

import application.controller.CalenderController;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Occation;
import application.model.Plan;
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

/**

 * @author Molly Frost - iav811
 * UTSA CS 3443 - Final_Project
 *
 */


public class RemindersController implements EventHandler<ActionEvent>, Initializable {
		
	@FXML AnchorPane rootPane, addEventRoot;
	@FXML Button backButton, addButton, addEventAdd, addEventX, incrementMonth, decrementMonth;
	@FXML TextField addEventName, addEventTime, addEventNotes;
	@FXML DatePicker addEventDate;
	@FXML Label MonthName, addEventError;
	@FXML CheckBox reminderCheckbox;
	
	private Occation myEvents;
	private Occation remindEvents;
	@FXML 
	private CheckBox remind1;
	@FXML 
	private CheckBox remind2;
	@FXML 
	private CheckBox remind3;
	@FXML 
	private CheckBox remind4;
	@FXML 
	private CheckBox remind5;
	@FXML 
	private CheckBox remind6;
	@FXML 
	private CheckBox remind7;
	@FXML 
	private CheckBox remind8;
	@FXML 
	private CheckBox remind9;
	@FXML 
	private CheckBox remind10;
	@FXML 
	private CheckBox remind11;
	@FXML 
	private CheckBox remind12;
	@FXML 
	private CheckBox remind13;
	@FXML 
	private CheckBox remind14;
	@FXML 
	private CheckBox remind15;
	@FXML 
	private CheckBox remind16;
	@FXML 
	private CheckBox remind17;
	@FXML 
	private CheckBox remind18;
	@FXML 
	private CheckBox remind19;
	@FXML 
	private CheckBox remind20;
	
	private CheckBox[] boxes;// = {remind1, remind2, remind3, remind4, remind5, remind6, remind7, remind8, remind9, remind10, remind11, remind12, remind13, remind14, remind15, remind16, remind17, remind18, remind19, remind20};
	
		/**
		 *    
		 * 
		 * @param location, URL
		 * @param resources, ResourceBundle
		 */
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			addEventRoot.setVisible(false);
			addEventError.setVisible(false);
			boxes = new CheckBox[]{remind1, remind2, remind3, remind4, remind5, remind6, remind7, remind8, remind9, remind10, remind11, remind12, remind13, remind14, remind15, remind16, remind17, remind18, remind19, remind20};
			myEvents = new Occation();
			remindEvents = new Occation();
			myEvents.loadEvents("data/monthlyEvents.csv");
			notVisible();
			int count = 0;
				for(Plan event : myEvents.getEvents()) {
					if(count < 20 && event.isRemind()) {
						remindEvents.addEvent(event);
						boxes[count].setText(event.remindersDisplay());
						boxes[count].setVisible(true);
						count++;
					}
			}
		}
		
		/**
		 * 
		 * 
		 * @param event, ActionEvent
		 */
		@Override
		public void handle(ActionEvent event) {
			//TODO: check which checkbox was marked
			for(int i = 0; i < 20; i++) {
				if(boxes[i].isSelected()) {
					Plan finished = remindEvents.getEvents().get(i);
					//System.out.println(finished.remindersDisplay());
					myEvents.removeEvent("data/MonthlyEvents.csv", finished);
					remindEvents.getEvents().remove(i);
					boxes[i].setDisable(true);
					break;
				}
			}
		}
		
		/**
		 * Sends the user to a different view, Monthly Calender.
		 * 
		 * @param event, ActionEvent
		 */
		public void goToMonthly(ActionEvent event) {
			//System.out.println("will go to home");
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
		

		
		/**
		 *   Sets all the checkBoxes to not visible.
		 */
		public void notVisible() {
			remind1.setVisible(false);
			remind2.setVisible(false);
			remind3.setVisible(false);
			remind4.setVisible(false);
			remind5.setVisible(false);
			remind6.setVisible(false);
			remind7.setVisible(false);
			remind8.setVisible(false);
			remind9.setVisible(false);
			remind10.setVisible(false);
			remind11.setVisible(false);
			remind12.setVisible(false);
			remind13.setVisible(false);
			remind14.setVisible(false);
			remind15.setVisible(false);
			remind16.setVisible(false);
			remind17.setVisible(false);
			remind18.setVisible(false);
			remind19.setVisible(false);
			remind20.setVisible(false);
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
				addEventNotes.clear();
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
					
					//System.out.println(addEventDate.getEditor().getText());
					
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
					myEvents.appendToFile(new Plan(newDate, addEventTime.getText(),addEventName.getText(),reminderCheckbox.isSelected(), addEventNotes.getText() ));
					
					//reload the view
					FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Reminders.fxml"));
					AnchorPane newPane = (AnchorPane) loader.load();
					rootPane.getChildren().setAll(newPane);
					
					
					}catch(Exception e) {e.printStackTrace();}
				}
				
			}
		}
		
}
