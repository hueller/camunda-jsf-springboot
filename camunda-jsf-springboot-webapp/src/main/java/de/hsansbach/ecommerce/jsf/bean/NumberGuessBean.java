package de.hsansbach.ecommerce.jsf.bean;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.task.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.hsansbach.ecommerce.jsf.NavigationHelper.NavigationKey;
import de.hsansbach.ecommerce.process.ProcessKey;
import de.hsansbach.ecommerce.process.model.NumberGuessModel;

@Component
@Scope("request")
public class NumberGuessBean extends AbstractBean {

	private String processInstanceId;

	private NumberGuessModel numberGuessModel;
	
	private int counter;
	
	private String hint;

	private NumberGuessBean() {
		numberGuessModel = new NumberGuessModel();
	}

	public String startProcess() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("assignee", getUsername());

		this.processInstanceId = camundaProcessService.startProcess(ProcessKey.NUMBER_GUESS, variables);

		return navigationHelper.navigateTo(NavigationKey.NUMBERGUESS_GUESSNUMBER);
	}
	
	public String guessNumber() {
		counter++;
		
		Task numberGuessTask = camundaProcessService.getActiveTaskForProcessInstanceId(this.processInstanceId);
		Integer randomNumber = (Integer) camundaProcessService.getVariableForProcessInstanceIdAndVariableKey(this.processInstanceId, "randomNumber");
		
		Integer numberGuess = this.numberGuessModel.getNumber();
		boolean correctGuess = randomNumber.equals(numberGuess);
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("numberGuess", numberGuess);
		variables.put("correctGuess", correctGuess);
		
		camundaProcessService.completeTask(numberGuessTask.getId(), variables);
		
		if (correctGuess) {
			return navigationHelper.navigateTo(NavigationKey.NUMBERGUESS_SUCCESS);
		}
		this.hint = randomNumber < numberGuess ? "LOWER" : "HIGHER";
		return navigationHelper.navigateTo(NavigationKey.NUMBERGUESS_GUESSNUMBER);
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public NumberGuessModel getNumberGuessModel() {
		return numberGuessModel;
	}

	public void setNumberGuessModel(NumberGuessModel numberGuessModel) {
		this.numberGuessModel = numberGuessModel;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

}