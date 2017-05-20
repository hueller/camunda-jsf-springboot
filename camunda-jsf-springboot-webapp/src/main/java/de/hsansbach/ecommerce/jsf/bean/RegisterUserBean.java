package de.hsansbach.ecommerce.jsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import de.hsansbach.ecommerce.jsf.NavigationHelper.NavigationKey;
import de.hsansbach.ecommerce.process.model.RegisterUserModel;

@Component
@Scope("request")
public class RegisterUserBean extends AbstractBean {

	private RegisterUserModel registerUserModel;

	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;

	public RegisterUserBean() {
		this.registerUserModel = new RegisterUserModel();
	}

	public String createUser() {
		if (inMemoryUserDetailsManager.userExists(registerUserModel.getUser())) {
			// User already exists
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "User " + registerUserModel.getUser() + " already exists.", ""));
			return navigationHelper.navigateToUsingRedirect(NavigationKey.REGISTER_USER);
		}
		
		// User doesn't exists - create new user
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		UserDetails user = new User(registerUserModel.getUser(), registerUserModel.getPassword(), authorities);
		inMemoryUserDetailsManager.createUser(user);

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully registered user " + registerUserModel.getUser() + ".", ""));
		return navigationHelper.navigateToUsingRedirect(NavigationKey.LOGIN);
	}

	public RegisterUserModel getRegisterUserModel() {
		return registerUserModel;
	}

	public void setRegisterUserModel(RegisterUserModel registerUserModel) {
		this.registerUserModel = registerUserModel;
	}

}
