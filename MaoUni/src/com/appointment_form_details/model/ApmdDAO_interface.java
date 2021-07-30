package com.appointment_form_details.model;

import java.sql.Connection;
import java.util.*;

public interface ApmdDAO_interface {
	public void insert(ApmdVO apmdVO, Connection con);		// 新增預約明細，綁定交易，新增預約單時自動新增
	public void update(ApmdVO apmdVO);						// undone
	public void delete(Integer apmId);						// undone
	public List<ApmdVO> getByAppointmentId(Integer apmId);  // 取得某筆預約單的所有預約項目

}
