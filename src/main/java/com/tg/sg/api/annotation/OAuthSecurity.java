package com.tg.sg.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface OAuthSecurity {
	public boolean enabled() default true;
	public String scope() default "DEFAULT_SCOPE";
}
