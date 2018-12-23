package com.tg.sg.api.model.flights;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FlightInfoType {
	private String id;
	private String type;
	private String departure;
	private String arrival;
	private String departureTime;
	private String arrivalTime;	
}
