package com.tg.sg.api.common;

import com.tg.sg.api.model.UserContext;

public class UserContextHolder {

	private static final ThreadLocal<UserContext> context = new ThreadLocal<UserContext>();
	
	public static void setContext(UserContext user) {
		context.set(user);
	}

	public static UserContext getContext() {
		if(context.get()==null) {
			UserContext user=new UserContext();
			user.setClientId("Anonymous");
		}
		return context.get();
	}

	public static void removeContext() {
		context.remove();
	}

}
