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
import de.hsansbach.ecommerce.jsf.model.ProcessesModel;
import de.hsansbach.ecommerce.process.CamundaProcessService;

@Named
@Scope("request")
public class ProcessesBean {

	@Autowired
	private NavigationHelper navigationHelper;

	@Autowired
	private CamundaProcessService camundaProcessService;

	private ProcessesModel processesModel;

	public ProcessesBean() {
		processesModel = new ProcessesModel();
	}

	public String startProcess() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("assignee", "admin");
		variables.put("text", processesModel.getText());

		camundaProcessService.startProcess("Sample", variables);

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully started process 'Sample'.",""));
		return navigationHelper.navigateTo(NavigationKey.PROCESSES, true);
	}

	public ProcessesModel getProcessesModel() {
		return processesModel;
	}

	public void setProcessModel(ProcessesModel processesModel) {
		this.processesModel = processesModel;
	}

}
