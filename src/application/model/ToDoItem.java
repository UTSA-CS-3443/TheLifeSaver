/**
 * ToDoItem is responsible for accessing and modifying items from a designated file,
 * and assembles them into a list of events for access across the entire application
 * 
 * @author Jade Manriquez - pwz823
 * 
 * UTSA CS 3443 - Final_Project - TheLifeSaver
 */
package application.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
 
public class ToDoItem {

	public ArrayList<ToDo> items;
	
	/**
	 * Default Constructor
	 */
	public ToDoItem() {
		this.items = new ArrayList<ToDo>();
	}
	
	/**
	 * Loads and parses items from a designated file, and adds
	 * them to the items arraylist
	 * 
	 * @param filename
	 */
	public void loadItems(String filename) {
		  try {

		    File file = new File(filename);
		    Scanner scan = new Scanner(file);

		    while (scan.hasNextLine()) {

		      String fileLine = scan.nextLine();
		      String[] next = fileLine.split(",");

		      SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		      Date date = null;

		      try {
		        date = format.parse(next[0]);
		      } catch (ParseException e) {
		        e.printStackTrace();
		      }
		      ToDo item = new ToDo(date, next[1], next[2]);
		      items.add(item);
		      
		    }
		    scan.close();
		  } catch (IOException e) {
		    e.printStackTrace();
		  }

		}
	       
	/**
	 * Adds an item to our item arraylist
	 * 
	 * @param newEvent
	 */
	public void addItem(ToDo newItem) {
		items.add(newItem);
	}

	/**
	 * Writes the contents of a new Item into the csv file
	 * 
	 * @param newPlan
	 */
	public void addToFile( ToDo newToDo ) {
		FileWriter filewriter;
		try {
			
			filewriter = new FileWriter("data/items.csv", true);
			PrintWriter printWriter = new PrintWriter(filewriter);
		
			printWriter.println(String.format("%s,%s,%s", newToDo.getDate(), newToDo.getTime(), newToDo.getName() ) );
			
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

	/**
	 * Gets the items
	 * 
	 * @return the items
	 */
	public ArrayList<ToDo> getItems() {
		return items;
	}
	
	/**
	 * Sets the items
	 * 
	 * @param the items to set
	 */
	public void setItems(ArrayList<ToDo> items) {
		this.items = items;
	}
	
	
}
