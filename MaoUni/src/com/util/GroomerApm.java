package com.util;

public class GroomerApm {
	public GroomerApm() {
		
	}
	
	public String timeFormate(Integer dateIndex) {
		StringBuffer date = new StringBuffer();
		
		if(dateIndex % 2 == 1 ){
			if(dateIndex < 19) date.append("0");
			date.append((dateIndex+1)/2);
			date.append(":00");
		}
		if(dateIndex % 2 == 0 ){
			if(dateIndex < 19) date.append("0");
			date.append((dateIndex+1)/2);
			date.append(":30");
		}
		return date.toString();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
