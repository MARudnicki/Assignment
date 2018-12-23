
package com.tg.sg.api.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tg.sg.api.annotation.OAuthSecurity;
import com.tg.sg.api.model.flights.FlightListResponse;
import com.tg.sg.api.service.flights.FlightService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/flightManagement")
@Slf4j
public class FlightController {

	@Autowired
	private FlightService service;

	@RequestMapping(value = "/flights", method = GET,produces = "application/json")
	@ApiOperation(value = "Flight List", produces = "application/json", consumes = "application/json",notes="Authorization: Base64(clientId:secret)")
	@OAuthSecurity(scope="superadmin")
	public FlightListResponse getFLights(@RequestHeader("Authorization")String authHeader) {
		log.info(authHeader);
		
		return service.getFlights();
	}

}
