package com.tg.sg.api.service.flights;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.tg.sg.api.common.Constant;
import com.tg.sg.api.common.DateUtils;
import com.tg.sg.api.model.client.BusinessFlightsInfo;
import com.tg.sg.api.model.client.EconomyFlightsInfo;
import com.tg.sg.api.model.flights.FlightInfoType;
import com.tg.sg.api.model.flights.FlightListResponse;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	
	public FlightListResponse getFlights() {
		FlightListResponse response = new FlightListResponse();
		
		
		RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<EconomyFlightsInfo>> economyResponse =  restTemplate.exchange("https://obscure-caverns-79008.herokuapp.com/cheap",HttpMethod.GET, null, new ParameterizedTypeReference<List<EconomyFlightsInfo>>() {});
        List<EconomyFlightsInfo> ecoList = economyResponse.getBody();
        
        ResponseEntity<List<BusinessFlightsInfo>> BusinessResponse =  restTemplate.exchange("https://obscure-caverns-79008.herokuapp.com/business",HttpMethod.GET, null, new ParameterizedTypeReference<List<BusinessFlightsInfo>>() {});
        List<BusinessFlightsInfo> bnsList = BusinessResponse.getBody();
        
        List<FlightInfoType> aggregatedList = new ArrayList<FlightInfoType>();
        if(ecoList!=null && !ecoList.isEmpty()) {
        	for (EconomyFlightsInfo ecoType : ecoList) {
				FlightInfoType infoType = new FlightInfoType();
				infoType.setId(ecoType.getId());
				infoType.setArrival(ecoType.getArrival());
				infoType.setDeparture(ecoType.getDeparture());
				infoType.setArrivalTime(DateUtils.getMilliSecondsInFormat(ecoType.getArrivalTime(), Constant.DATE_FORMAT));
				infoType.setDepartureTime(DateUtils.getMilliSecondsInFormat(ecoType.getDepartureTime(), Constant.DATE_FORMAT));
				infoType.setType("ECONOMY");
				aggregatedList.add(infoType);
			}
        }
        
        if(bnsList!=null && !bnsList.isEmpty()) {
        	for (BusinessFlightsInfo bnsType : bnsList) {
				FlightInfoType infoType = new FlightInfoType();
				infoType.setId(bnsType.getUuid());
				infoType.setArrivalTime(DateUtils.getDateInFormat(bnsType.getArrival(), Constant.DATE_FORMAT));
				infoType.setDepartureTime(DateUtils.getDateInFormat(bnsType.getDeparture(), Constant.DATE_FORMAT));
				StringTokenizer strTokens = new StringTokenizer(bnsType.getFlight(), "->");
				while(strTokens.hasMoreTokens()) {
					infoType.setArrival(strTokens.nextToken());
					infoType.setDeparture(strTokens.nextToken());
				}			
				infoType.setType("BUSINESS");
				aggregatedList.add(infoType);
			}
        }
        sortList(aggregatedList);
        response.setFlights(aggregatedList);
		response.setResponseCode("0000");
		response.setResponseMessage("Success");
		
		return response;
	}
	
	private void sortList(List<FlightInfoType> list) {
		List<FlightInfoType> orders = list;

		orders.sort(Comparator.comparing(FlightInfoType::getDepartureTime).thenComparing(FlightInfoType::getDeparture));

	}
	
}
