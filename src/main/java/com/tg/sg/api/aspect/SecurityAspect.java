package com.tg.sg.api.aspect;

import java.lang.reflect.Method;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tg.sg.api.annotation.OAuthSecurity;
import com.tg.sg.api.common.UserContextHolder;
import com.tg.sg.api.exception.AccessForbiddenException;
import com.tg.sg.api.exception.UnAuthrizeException;

import lombok.extern.slf4j.Slf4j;

@Service
@Aspect
@Slf4j
public class SecurityAspect {
	

	@Around(" execution(* com.tg.sg.api.controller..*.*(..)) && (@annotation(security)||@target(security)) ")
	public Object aroundMethod(ProceedingJoinPoint joinPoint, OAuthSecurity security) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String authHeader = request.getHeader("Authorization");
		log.info("====Authorization====" + authHeader + "==" + security);

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		security = method.getAnnotation(OAuthSecurity.class);

		if (security == null) {
			security = joinPoint.getTarget().getClass().getAnnotation(OAuthSecurity.class);
		}
		if (!security.enabled()) {
			return joinPoint.proceed();
		}

		String scope = security.scope();

		try {

			if (authHeader == null || authHeader.isEmpty()) {
				throw new UnAuthrizeException("Please provide Authorization header ");
			}

			if (authHeader != null && !authHeader.isEmpty()) {

				String value = new String(Base64.getDecoder().decode(authHeader));
				String headerValues[] = value.split(":");
				String clientId = headerValues[0];
				String secret = headerValues[1];


				if (!clientId.equals("TgAdmin") || !secret.equals("assignment")) {  // usually clientId and secret will be stored in data base.
					throw new UnAuthrizeException("Invalid client id Or Secret");
				}
				if (!checkScope(scope)) {
					throw new AccessForbiddenException("you are not allowed to access this resource");
				}
			}

		} catch (AccessForbiddenException | UnAuthrizeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnAuthrizeException("Invalid client id Or Secret");
		}
		System.out.println("====before===" + request.getHeader("Authorization"));
		Object obj = joinPoint.proceed();
		System.out.println("====after===");
		UserContextHolder.removeContext();
		return obj;
	}

	private boolean checkScope(String scope) {

		

			if ("superadmin".equalsIgnoreCase(scope)) {
				return true;
			}

		return false;
	}

}
