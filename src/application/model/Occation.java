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
				
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				
				Date date = null;
				
				try {
					date = format.parse(temp[0]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Plan event = new Plan(date, temp[1], temp[2], isRemind, temp[4]);
				
				boolean added = false;
				
				for( int i = 0; i < events.size(); i++ ) {
					if(event.getDateOb().compareTo(events.get(i).getDateOb()) < 0 ){
						events.add(i,event);
						added = true;
						break;
					}
					else if(event.getDateOb().compareTo(events.get(i).getDateOb() ) == 0 ) {
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
//		return events;
	}
	
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
				if(!(temp[0].equals(strDate) && temp[1].equals(finished.getTime()))) {
					printer.write(fileLine);
				}else {
					events.remove(count);
				}
				count++;
			}
				copyFiletoFile(file, tempFile);
				//tempFile.delete();
				scan.close();
				printer.close();
		}catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	public void copyFiletoFile(File dest, File source) {
		try {
			FileWriter printer = new FileWriter(dest);
			Scanner scan = new Scanner( source );
			
			while(scan.hasNextLine()) {
				String fileLine = scan.nextLine();
				printer.write(fileLine);
				
			}
			scan.close();
			printer.close();
		}catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
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
