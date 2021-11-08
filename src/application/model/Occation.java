package application.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
				
				Date date = null;
				try {
					date = format.parse(temp[0]);
				} catch (ParseException e) {
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
				System.out.println("temp[0]: " + temp[0] + "\nstrdate: " + strDate);
				System.out.println("temp[1]: " + temp[1] + "\nfinsihed time: " + finished.getTime());

				System.out.println((temp[0].equals(strDate) && temp[1].equals(finished.getTime())));
				if(temp[0].equals(strDate) && temp[1].equals(finished.getTime())) {
					System.out.println("removing...:");
					events.remove(count);
				}else {
					System.out.println("adding to file...");
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
	
	public void copyFiletoFile(File destination, File source) {
		try {
			FileWriter printer = new FileWriter(destination);
			Scanner scan = new Scanner( source );
			
			while(scan.hasNextLine()) {
				String fileLine = scan.nextLine();
				System.out.println("adding to me.csv" + fileLine);
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
