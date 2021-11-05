package application.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Weekly {
	
	//class variable
	private String month;
	private int day;
    private ArrayList<Plan> plans;
	
    public Weekly() {
    	this.month = "";
    	this.day=0;
    	this.plans = new ArrayList<Plan>();
    }

    public Weekly(String month, int day) {
        this.month = month;
        this.day = day;
        this.plans = new ArrayList<Plan>();
    }
	
	
	//read in file and get the date, create that as a weekly object
	/*public static Plan loadPlan (String filename) {
		try {
			Scanner scan = new Scanner(new File(filename));
			
			while(scan.hasNextLine()) {
				String line= scan.nextLine();
				String[] tokens = line.split( "," );
				if(tokens!=null && tokens.length==5) {
					String date = tokens[0];
					String time = tokens[1];
					String name = tokens[2];
					String remind = tokens[3];
					String note = tokens[4];
					
					Plan event = new Plan (date,time,name,remind,note);
				}
				
			}
		
	}catch (IOException e) {
		e.printStackTrace();
	}
		return null;

	}
	public String toString() {
		
	}*/

	/**
	 * @return the plans
	 */
	public ArrayList<Plan> getPlans() {
		return plans;
	}



	/**
	 * @param plans the plans to set
	 */
	public void setPlans(ArrayList<Plan> plans) {
		this.plans = plans;
	}



	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}



	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}



	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}



	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	
	//

}
