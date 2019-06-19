package com.hersa.sample.app.web;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.hersa.sample.app.bom.user.User;

public class SessionContext {

	public static final String SESSION_USER = "sample.app.session.user";
	
	public static void setSessionUser(User user) {
		setSessionObject(SESSION_USER, user);
	}
	
	public static User getSessionUser() {
		User user = null;
		try {
			user = (User) getSessionObject(SESSION_USER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	private static void setSessionObject(String key, Object object) {
		getSessionMap().put(key, object);
	}
	
	private static Object getSessionObject(String key) {
		Object object = getSessionMap().get(key);
		return object;
	}
	
	private static Map<String, Object> getSessionMap(){
		Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		return map;
	}
	
	public static HttpSession getSession() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return session;
	}
}
