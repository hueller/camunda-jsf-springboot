package de.hsansbach.ecommerce.jsf.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.camunda.bpm.engine.task.Task;
import org.springframework.context.annotation.Scope;

import de.hsansbach.ecommerce.jsf.NavigationHelper.NavigationKey;

@Named
@Scope("request")
public class TasksBean extends AbstractBean {
	
	public List<Task> tasks() {
		return camundaProcessService.getTasksForAssigne(getUsername());
	}

	public String complete(String taskId) {
		camundaProcessService.completeTask(taskId);

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully completed task id " + taskId + ".", ""));
		return navigationHelper.navigateTo(NavigationKey.TASKS, true);
	}
}
