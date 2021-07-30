package com.StaffSignIn.model;

public class signInService {
	
	signInJDBCODAO dao = new signInJDBCODAO();
	public signInVO findByUsernameAndPassword(String username,String password) {
		return dao.findByUsernameAndPassword(username, password);
}
	public signInVO logout(String username,String password) {
		return dao.Logout(username, password);
		
	}
}