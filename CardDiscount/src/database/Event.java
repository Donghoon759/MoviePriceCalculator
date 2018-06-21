package database;

import java.sql.Timestamp;

public class Event {
	String name;
	Timestamp startDate;
	Timestamp endDate;
	String company;
	int isOn;
	
	public Event(String name, Timestamp startDate, Timestamp endDate, String company, int isOn)
	{
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.company = company;
		this.isOn = isOn;
	}

}
