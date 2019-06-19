package com.hersa.sample.app.web;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ApplicationContext {

	private String projectName;
	private Map<String, Object> applicationMap;
	
	public ApplicationContext() {
		this.setProjectName("Bill Manager");
		this.applicationMap = new HashMap<String , Object>();
	}

	public void putApplicationItem(String key, Object value) {
		synchronized (this) {
			applicationMap.put(key, value);
		}
	}

	public Object getApplicationItem(String key) {
		Object item = null;
		synchronized (this) {
			item = applicationMap.get(key);
		}
		return item;
	}
	
	public int getApplicationMapSize() {
		int count = 0;
		synchronized (this) {
			count = applicationMap.size();
		}
		return count;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
