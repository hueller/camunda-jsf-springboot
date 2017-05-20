package de.hsansbach.ecommerce.jsf.bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.hsansbach.ecommerce.jsf.NavigationHelper.NavigationKey;
import de.hsansbach.ecommerce.process.ProcessKey;
import de.hsansbach.ecommerce.process.model.UserTaskModel;

@Component
@Scope("request")
public class ProcessesBean extends AbstractBean {

	private UserTaskModel userTaskModel;

	public ProcessesBean() {
		userTaskModel = new UserTaskModel();
	}

	public String startProcessUserTask() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("assignee", getUsername());
		variables.put("text", userTaskModel.getText());

		String processInstanceId = camundaProcessService.startProcess(ProcessKey.USER_TASK, variables);

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully started process '" + ProcessKey.USER_TASK.name() + "' with id " + processInstanceId + ".", ""));
		return navigationHelper.navigateToUsingRedirect(NavigationKey.PROCESSES);
	}

	public String startProcessSimple() {
		String processInstanceId = camundaProcessService.startProcess(ProcessKey.SIMPLE);

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully started process '" + ProcessKey.SIMPLE.name() + "' with id " + processInstanceId + ".", ""));
		return navigationHelper.navigateToUsingRedirect(NavigationKey.PROCESSES);
	}

	public UserTaskModel getUserTaskModel() {
		return userTaskModel;
	}

	public void setUserTaskModel(UserTaskModel userTaskModel) {
		this.userTaskModel = userTaskModel;
	}

}
