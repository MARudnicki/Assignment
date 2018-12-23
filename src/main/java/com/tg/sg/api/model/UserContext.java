package com.tg.sg.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserContext {
   private String clientId;
   private String scope;
   private String userName;
   
}
