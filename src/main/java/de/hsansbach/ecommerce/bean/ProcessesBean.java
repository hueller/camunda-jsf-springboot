package de.hsansbach.ecommerce.bean;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import de.hsansbach.ecommerce.bean.NavigationHelper.NavigationKey;
import de.hsansbach.ecommerce.process.CamundaProcessService;

@Named
@Scope("request")
public class ProcessesBean {
	
	@Autowired
	private NavigationHelper navigationHelper;
	
	@Autowired
	private CamundaProcessService camundaProcessService;
	
	private String text;
	
	private String processInstanceId;
	
	public String startProcess() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("assignee", "admin");
		variables.put("text", text);
		
		this.processInstanceId = camundaProcessService.startProcess("Sample", variables);
		
		return navigationHelper.navigateTo(NavigationKey.PROCESSES);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

}
