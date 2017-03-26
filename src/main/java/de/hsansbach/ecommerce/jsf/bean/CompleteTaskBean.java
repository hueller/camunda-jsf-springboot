package de.hsansbach.ecommerce.jsf.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import de.hsansbach.ecommerce.jsf.NavigationHelper;
import de.hsansbach.ecommerce.jsf.NavigationHelper.NavigationKey;
import de.hsansbach.ecommerce.process.service.CamundaProcessService;

@Named
@Scope("request")
public class CompleteTaskBean {
	
	@Autowired
	private NavigationHelper navigationHelper;
	
	@Autowired
	private CamundaProcessService camundaProcessService;
	
	public String completeTask(String taskId) {
		camundaProcessService.completeTask(taskId);
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully completed task id " + taskId + ".",""));
		return navigationHelper.navigateTo(NavigationKey.TASKS, true);
	}
}
