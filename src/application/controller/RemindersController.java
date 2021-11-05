package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Occation;
import application.model.Plan;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
		
	private Occation myEvents;
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
	
	private CheckBox boxes[] = {remind1, remind2, remind3, remind4, remind5, remind6, remind7, remind8, remind9, remind10, remind11, remind12, remind13, remind14, remind15, remind16, remind17, remind18, remind19, remind20};
	
		/**
		 *    
		 * 
		 * @param location, URL
		 * @param resources, ResourceBundle
		 */
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			myEvents = new Occation();
			myEvents.loadEvents("data/monthlyEvents.csv");
			notVisible();
			int count = 0;
			//TODO: read from file && while count <=20
			for(Plan event : myEvents.getEvents()) {
				while(count < 20) {
					//TODO: check if isRemind = true if true 
					if(event.isRemind()) {
						//TODO: if true print remind increment count (make visible)
						boxes[count].setText(event.toString());
						boxes[count].setVisible(true);
						count++;
					}
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
					//remove from file
					//remove from arraylist
					Plan finished = myEvents.getEvents().get(i);
					myEvents.removeEvent("data/MonthlyEvents.csv", finished);
					boxes[i].setDisable(true);
				}else {
					System.out.println("something went wrong...");
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
		 * Sends the user to a different view, Weekly Calender.
		 * 
		 * @param event, ActionEvent
		 */
		public void goToWeekly(ActionEvent event) {
			//System.out.println("will go to home");
			try {
				AnchorPane root = new AnchorPane();
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( Main.class.getResource("view/Weekly.fxml") );
				root = (AnchorPane) loader.load();
		
				Scene scene = new Scene( root );
				Main.stage.setScene( scene );
				Main.stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Sends the user to a different view, Daily To Do List.
		 * 
		 * @param event, ActionEvent
		 */
		public void goToDos(ActionEvent event) {
			//System.out.println("will go to home");
			try {
				AnchorPane root = new AnchorPane();
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( Main.class.getResource("view/ToDo.fxml") );
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
			//remind1.setVisible(false);
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
		
		
		
		
		
		
		
		
		
}
