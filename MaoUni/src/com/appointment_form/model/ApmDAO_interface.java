package com.appointment_form.model;
import java.sql.Date;
import java.util.*;

import com.appointment_form_details.model.ApmdVO;

public interface ApmDAO_interface {
	 public void insert(ApmVO apmVO, List<ApmdVO> list);
     public Integer update(ApmVO apmVO, Date changeDate);  // 複合修改、交易綁定
     public List<ApmVO> getAll(Map<String, String[]> map);  // 複合查詢
//     public List<ApmVO> getMonth(Integer month, Integer groomerId);  // 用於行程日曆
}
