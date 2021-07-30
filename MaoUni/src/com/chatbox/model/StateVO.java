package com.chatbox.model;

import java.util.Set;

public class StateVO implements java.io.Serializable{
	private String type;
	// the user changing the state
	private String user;
	// total users
	private Set<String> users;
	
	public StateVO(String type, String user, Set<String> users) {
		this.type = type;
		this.user = user;
		this.users = users;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Set<String> getUsers() {
		return users;
	}
	public void setUsers(Set<String> users) {
		this.users = users;
	}
	
	
}
