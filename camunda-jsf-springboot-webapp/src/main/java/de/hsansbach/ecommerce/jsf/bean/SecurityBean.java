package de.hsansbach.ecommerce.jsf.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SecurityBean {
	
	private Authentication authentication;

	public SecurityBean() {
		authentication = SecurityContextHolder.getContext().getAuthentication();
	}

	public Authentication getAuthentication() {
		return authentication;
	}

}
