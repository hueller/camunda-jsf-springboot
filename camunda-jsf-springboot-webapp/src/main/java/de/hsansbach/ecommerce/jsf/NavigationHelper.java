package de.hsansbach.ecommerce.jsf;

import org.springframework.stereotype.Component;

@Component
public class NavigationHelper {

	public String navigateTo(String key) {
		return NavigationKey.valueOf(key).getFileName();
	}

	public String navigateTo(NavigationKey key) {
		return key.getFileName();
	}
	
	public String navigateToUsingRedirect(String key) {
		return navigateToUsingRedirect(NavigationKey.valueOf(key).getFileName());
	}
	
	public String navigateToUsingRedirect(NavigationKey key) {
		return navigateTo(key) + "?faces-redirect=true";
	}

	public enum NavigationKey {
		LOGIN("/login.jsf"), 
		HOME("/home.jsf"),
		PROCESSES("/processes.jsf"),
		REGISTER_USER("/registerUser.jsf"),
		TASKS("/tasks.jsf"),
		NUMBERGUESS_START("/numberguess_start.jsf"),
		NUMBERGUESS_GUESSNUMBER("/numberguess_guessnumber.jsf"),
		NUMBERGUESS_SUCCESS("/numberguess_success.jsf");
		
		private String fileName;
		private NavigationKey(String fileName) {
			this.fileName = fileName;
		}
		public String getFileName() {
			return fileName;
		}
	}

}