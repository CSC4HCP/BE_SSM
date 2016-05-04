package main.java.com.sap.ssm.web.controller;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.security.um.user.PersistenceException;
import com.sap.security.um.user.User;
import com.sap.security.um.user.UserProvider;

@RestController
public class UserController {

	@Autowired
	private InitialContext initialContext;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public User getLoginUser(HttpServletRequest request) throws NamingException, PersistenceException {
		UserProvider provider = (UserProvider) initialContext.lookup("java:comp/env/user/Provider");
		return provider.getCurrentUser();
	}
}
