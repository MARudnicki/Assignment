package com.tg.sg.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseResponse {
	
	@ApiModelProperty(required = true,notes="0000 is success code otherwise Fail")
	private String responseCode="0000";
	
	@ApiModelProperty(required = true,notes="Success is success message otherwise Fail")
	private String responseMessage="Success";

}
