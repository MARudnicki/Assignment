package com.tg.sg.api.model.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class BusinessFlightsInfo {
	private String uuid;
	private String flight;
	private String arrival;
	private String departure;
}
