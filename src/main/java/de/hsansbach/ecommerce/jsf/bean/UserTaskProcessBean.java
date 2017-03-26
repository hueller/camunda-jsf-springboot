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
import de.hsansbach.ecommerce.jsf.model.UserTaskModel;
import de.hsansbach.ecommerce.process.ProcessKey;
import de.hsansbach.ecommerce.process.service.CamundaProcessService;

@Named
@Scope("request")
public class UserTaskProcessBean {

	@Autowired
	private NavigationHelper navigationHelper;

	@Autowired
	private CamundaProcessService camundaProcessService;

	private UserTaskModel userTaskModel;

	public UserTaskProcessBean() {
		userTaskModel = new UserTaskModel();
	}

	public String startProcess() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("assignee", "admin");
		variables.put("text", userTaskModel.getText());

		String processInstanceId = camundaProcessService.startProcess(ProcessKey.USER_TASK, variables);

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully started process 'User Task' with id " + processInstanceId,""));
		return navigationHelper.navigateTo(NavigationKey.PROCESSES, true);
	}

	public UserTaskModel getUserTaskModel() {
		return userTaskModel;
	}

	public void setUserTaskModel(UserTaskModel userTaskModel) {
		this.userTaskModel = userTaskModel;
	}
	
}
