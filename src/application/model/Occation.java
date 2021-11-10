/**
 * Occation is responsible for accessing and modifying events from a designated file,
 * and assembles them into a list of events for access across the entire application
 * 
 * @author Molly Frost - iav811 
 * UTSA CS 3443 - Final_Project - TheLifeSaver
 * Fall 2021
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
 
public class Occation {

	ArrayList<Plan> events;
	
	/**
	 * Default Constructor
	 */
	public Occation() {
		events = new ArrayList<Plan>();
	}

	/**
	 * Loads and parses events from a designated file, and adds
	 * them to the events arraylist
	 * 
	 * @param filename
	 */
	public void loadEvents(String filename) {
		  try {

		    File file = new File(filename);
		    Scanner scan = new Scanner(file);

		    while (scan.hasNextLine()) {

		      String fileLine = scan.nextLine();
		      String[] temp = fileLine.split(",");

		      for (int j = 0; j < 5; j++) {
		        temp[j] = temp[j].trim();
		      }

		      boolean isRemind = false;
		      if (temp[3].equals("T")) {
		        isRemind = true;
		      }

		      SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		      Date date = null;

		      try {
		        date = format.parse(temp[0]);
		      } catch (ParseException e) {
		        e.printStackTrace();
		      }
		      Plan event = new Plan(date, temp[1], temp[2], isRemind, temp[4]);

		      boolean added = false;

		      for (int i = 0; i < events.size(); i++) {
		        if (event.getDateOb().compareTo(events.get(i).getDateOb()) < 0) {
		          events.add(i, event);
		          added = true;
		          break;
		        } else if (event.getDateOb().compareTo(events.get(i).getDateOb()) == 0) {
		          if (Integer.parseInt(event.getTime()) < Integer.parseInt(events.get(i).getTime())) {
		            events.add(i, event);
		            added = true;
		            break;
		          }
		        }
		      }

		      if (!added) {
		        events.add(event);
		      }
		    }
		    scan.close();
		  } catch (IOException e) {
		    e.printStackTrace();
		  }

		}
	
	/**
	 * Removes a specified event from a designated file
	 * 
	 * @param filename
	 * @param finished
	 */
	public void removeEvent(String filename, Plan finished){ 
        String strDate = finished.getDate();
        int count = 0;
		try {
			File file =  new File(filename);
			File tempFile = new File("data/temp.csv");
			FileWriter printer = new FileWriter(tempFile);
			Scanner scan = new Scanner( file );
			
			while(scan.hasNextLine()) {
				String fileLine = scan.nextLine();
				String[] temp = fileLine.split(",");
				for(int j = 0; j < 5; j++) {
					temp[j] = temp[j].trim();
				}
				//System.out.println("temp[0]: " + temp[0] + "\nstrdate: " + strDate);
				//System.out.println("temp[1]: " + temp[1] + "\nfinsihed time: " + finished.getTime());

				//System.out.println((temp[0].equals(strDate) && temp[1].equals(finished.getTime())));
				if(temp[0].equals(strDate) && temp[1].equals(finished.getTime())) {
					//System.out.println("removing...:");
					events.remove(count);
				}else {
					//System.out.println("adding to file...");
					printer.write(fileLine);
					printer.write(System.getProperty( "line.separator" ));
				}
				count++;
			}
				
				scan.close();
				printer.close();
				copyFiletoFile(file, tempFile);
				//tempFile.delete();
		}catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Copies the contents of a source file into a destination file
	 * 
	 * @param destination
	 * @param source
	 */
	public void copyFiletoFile(File destination, File source) {
		try {
			FileWriter printer = new FileWriter(destination);
			Scanner scan = new Scanner( source );
			
			while(scan.hasNextLine()) {
				String fileLine = scan.nextLine();
				//System.out.println("adding to me.csv" + fileLine);
				printer.write(fileLine);
				printer.write(System.getProperty( "line.separator" ));
			}
			
			scan.close();
			printer.close();
		}catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds an event to our events arraylist
	 * 
	 * @param newEvent
	 */
	public void addEvent(Plan newEvent) {
		events.add(newEvent);
	}

	/**
	 * Writes the contents of a plan object into a designated events CSV
	 * 
	 * @param newPlan
	 */
	public void appendToFile( Plan newPlan ) {
		FileWriter filewriter;
		try {
			
			filewriter = new FileWriter("data/monthlyEvents.csv", true);
			PrintWriter printWriter = new PrintWriter(filewriter);
		
			if( newPlan.getNote().isEmpty() ) {
				printWriter.println(String.format("%s,%s,%s,%s,%s", newPlan.getDate(), newPlan.getTime(), newPlan.getName(), newPlan.isRemind() ? "T":"F", "-" ));
			}
			else {
				printWriter.println(String.format("%s,%s,%s,%s,%s", newPlan.getDate(), newPlan.getTime(), newPlan.getName(), newPlan.isRemind() ? "T" : "F", newPlan.getNote() ) );
			}
			
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}

	}

	/**
	 * Gets the events
	 * 
	 * @return the events
	 */
	public ArrayList<Plan> getEvents() {
		return events;
	}

	/**
	 * Sets the events
	 * 
	 * @param the events to set
	 */
	public void setEvents(ArrayList<Plan> events) {
		this.events = events;
	}
	
	
}
