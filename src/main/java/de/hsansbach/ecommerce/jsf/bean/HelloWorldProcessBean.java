package de.hsansbach.ecommerce.jsf.bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import de.hsansbach.ecommerce.jsf.NavigationHelper;
import de.hsansbach.ecommerce.jsf.NavigationHelper.NavigationKey;
import de.hsansbach.ecommerce.process.ProcessKey;
import de.hsansbach.ecommerce.process.service.CamundaProcessService;

@Named
@Scope("request")
public class HelloWorldProcessBean {

	@Autowired
	private NavigationHelper navigationHelper;

	@Autowired
	private CamundaProcessService camundaProcessService;

	public String startProcess() {
		Map<String, Object> variables = new HashMap<>();

		String processInstanceId = camundaProcessService.startProcess(ProcessKey.HELLO_WORLD, variables);

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully started process 'Hello World' with id " + processInstanceId,""));
		return navigationHelper.navigateTo(NavigationKey.PROCESSES, true);
	}
	
}
