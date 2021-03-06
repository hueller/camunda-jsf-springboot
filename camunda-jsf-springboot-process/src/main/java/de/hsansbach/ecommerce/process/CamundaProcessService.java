package de.hsansbach.ecommerce.process;

import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamundaProcessService {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	public String startProcess(ProcessKey processKey) {
		return startProcess(processKey, null);
	}

	public String startProcess(ProcessKey processKey, Map<String, Object> variables) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey.name(), variables);
		return processInstance.getId();
	}

	public List<Task> getTasks() {
		return taskService.createTaskQuery().list();
	}

	public List<Task> getTasksForAssigne(String assigne) {
		return taskService.createTaskQuery().taskAssignee(assigne).list();
	}
	
	public Task getActiveTaskForProcessInstanceId(String processInstanceId) {
		return taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
	}
	
	public Map<String, Object> getVariablesForProcessInstanceId(String processInstanceId) {
		return runtimeService.getVariables(processInstanceId);
	}

	public Object getVariableForProcessInstanceIdAndVariableKey(String processInstanceId, String variableKey) {
		return runtimeService.getVariable(processInstanceId, variableKey);
	}

	public void setVariableToProcessInstance(String processInstanceId, String variableKey, Object variableValue) {
		runtimeService.setVariable(processInstanceId, variableKey, variableValue);
	}

	public void completeTask(String taskId) {
		completeTask(taskId, null);
	}

	public void completeTask(String taskId, Map<String, Object> variables) {
		taskService.complete(taskId, variables);
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

}
