package application.model;

import java.time.*;
import java.util.ArrayList;

public class Calendar {

    private String monthName;
    private int year;
    private ArrayList<Plan> plans;

    public Calendar() {
        this.monthName = "";
        this.year = 0;
        this.plans = new ArrayList<Plan>();
    }

    public Calendar(String month, int yearNo) {
        this.monthName = month;
        this.year = yearNo;
        this.plans = new ArrayList<Plan>();
    }

    public String getMonthName() {
        return this.monthName;
    }

    public int getYear() { 
        return this.year;
    }

    public ArrayList<Plan> getPlans() {
        return this.plans;
    }

    public void setMonthName(String newMonth) {
        this.monthName = newMonth;
    }

    public void setYear(int newYear) {
        this.year = newYear;
    }

    public void setPlans(ArrayList<Plan> newPlans) {
        this.plans = newPlans;
    }

    public void addPlan(Plan newPlan) {
        this.plans.add(newPlan);
    }
    
    public String toString() {
    	//TODO
    	String s = "";
    	return s;
    }

}
