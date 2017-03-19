package de.hsansbach.ecommerce.bean;

import org.springframework.stereotype.Component;

@Component
public class NavigationHelper {

	public String navigateTo(String key) {
		return NavigationKey.valueOf(key).getFileName();
	}

	public String navigateTo(NavigationKey key) {
		return key.getFileName();
	}

	public enum NavigationKey {
		LOGIN("/login.jsf"), 
		HOME("/home.jsf"),
		PROCESSES("/processes.jsf"),
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