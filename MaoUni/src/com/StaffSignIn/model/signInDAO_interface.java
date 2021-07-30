package com.StaffSignIn.model;

public interface signInDAO_interface {

	signInVO findByUsernameAndPassword(String username, String password);
	signInVO Logout(String username, String password);

}
