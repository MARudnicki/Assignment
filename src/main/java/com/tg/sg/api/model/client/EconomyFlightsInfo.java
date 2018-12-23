package com.tg.sg.api.model.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class EconomyFlightsInfo {
	private String id;
	private String departure;
	private String arrival;
	private String departureTime;
	private String arrivalTime;	
}
