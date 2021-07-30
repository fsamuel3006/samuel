package com.appointment_form_details.model;

import java.util.List;

public class ApmdService {
	ApmdDAO_interface dao;
	public ApmdService() {
		this.dao = new ApmdDAO();
	}
	
	public List<ApmdVO> getByAppointmentId(Integer apmId){
		return dao.getByAppointmentId(apmId);
	}

}
