package de.hsansbach.ecommerce.bean;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class JsfBackingBean {
	public String getMessageFromBackingBean() {
		return "Hallo JSF mit Spring Boot";
	}
}