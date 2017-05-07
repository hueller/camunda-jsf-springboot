package de.hsansbach.ecommerce.jsf.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import de.hsansbach.ecommerce.jsf.NavigationHelper;
import de.hsansbach.ecommerce.process.CamundaProcessService;

public abstract class AbstractBean {

	@Autowired
	protected NavigationHelper navigationHelper;

	@Autowired
	protected CamundaProcessService camundaProcessService;
	
	protected String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
	
}
