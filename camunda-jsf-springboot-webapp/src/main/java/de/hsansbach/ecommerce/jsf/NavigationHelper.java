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
	
	public String navigateTo(NavigationKey key, boolean doRedirect) {
		return navigateTo(key) + "?faces-redirect=true";
	}

	public enum NavigationKey {
		LOGIN("/login.jsf"), 
		HOME("/home.jsf"),
		PROCESSES("/processes.jsf"),
		REGISTER_USER("/registerUser.jsf"),
		TASKS("/tasks.jsf");
		
		private String fileName;
		private NavigationKey(String fileName) {
			this.fileName = fileName;
		}
		public String getFileName() {
			return fileName;
		}
	}

}