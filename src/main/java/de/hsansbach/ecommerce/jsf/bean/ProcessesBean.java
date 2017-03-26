package de.hsansbach.ecommerce.jsf.bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import de.hsansbach.ecommerce.jsf.NavigationHelper.NavigationKey;
import de.hsansbach.ecommerce.jsf.model.UserTaskModel;
import de.hsansbach.ecommerce.process.ProcessKey;

@Named
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
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully started process 'User Task' with id " + processInstanceId + ".", ""));
		return navigationHelper.navigateTo(NavigationKey.PROCESSES, true);
	}

	public String startProcessHelloWorld() {
		String processInstanceId = camundaProcessService.startProcess(ProcessKey.HELLO_WORLD);

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully started process 'Hello World' with id " + processInstanceId + ".", ""));
		return navigationHelper.navigateTo(NavigationKey.PROCESSES, true);
	}

	public UserTaskModel getUserTaskModel() {
		return userTaskModel;
	}

	public void setUserTaskModel(UserTaskModel userTaskModel) {
		this.userTaskModel = userTaskModel;
	}

}
