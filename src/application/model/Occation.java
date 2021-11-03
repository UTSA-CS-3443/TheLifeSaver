package application.model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Molly Frost - iav811 
 * UTSA CS 3443 - Project 
 * Fall 2021
 */
public class Occation {

	ArrayList<Plan> events;
	
	public Occation() {
		events = new ArrayList<Plan>();
	}

	/**
	 * 
	 * @param filename, String
	 * @param actNum, int
	 */
	public void loadEvents(String filename) {	
		try {
			
			File file =  new File(filename);
			Scanner scan = new Scanner( file );
			
			while(scan.hasNextLine()) {
				String fileLine = scan.nextLine();
				String[] temp = fileLine.split(",");
				for(int j = 0; j < 5; j++) {
					temp[j] = temp[j].trim();
				}
				boolean isRemind = false;
				if(temp[3].equals("T")) {
					isRemind = true;
				}
				SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
				Date date = format.parse(temp[0]);
				Plan event = new Plan(date, temp[1], temp[2], isRemind, temp[4]);
				boolean added = false;
				for(int i = 0; i < events.size(); i++) {
					if(event.getDate().compareTo(events.get(i).getDate()) <= 0) {
						if(Integer.parseInt(event.getTime()) < Integer.parseInt(events.get(i).getTime())){
							events.add(i, event);
							added = true;
							break;
						}
					}
				}
				if(!added) {
					events.add(event);
				}
			}
			scan.close();
		}catch( IOException e ) {
			e.printStackTrace();
		}
		return events;
	}

	/**
	 * @return the events
	 */
	public ArrayList<Plan> getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public void setEvents(ArrayList<Plan> events) {
		this.events = events;
	}
	
	
}
