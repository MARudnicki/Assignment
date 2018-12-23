package com.tg.sg.api.model.flights;

import java.util.List;

import com.tg.sg.api.model.BaseResponse;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FlightListResponse extends BaseResponse {
	private List<FlightInfoType> flights;

}
