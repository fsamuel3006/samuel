package com.groomer.model;
import java.sql.Connection;
import java.util.*;

import com.appointment_form.model.ApmVO;

public interface GroDAO_interface {
	 public void insert(GroVO groVO);  
     public void updateStatus(Integer groomerId, Integer result);		// 更改狀態
     public Map<String, String> findPhotosBygmId(Integer groomerId);	// 取得審核所需的圖片共4張
     public List<GroVO> getAll();										// 取得所有資料，不含圖片
     public List<GroVO> getAll(Map<String, String[]> map);				// 複合查詢
     public Integer updateInfo(GroVO groVO); 								// groomerInfo.jsp 使用，美容師更新個人資料
     public void update(GroVO groVO, Connection con);  					// 複合修改、交易綁定;預約單狀態更動、評論更動、發生檢舉時，由  ApmDAO、GrepDAO 呼叫
     
     
// 	       以下為舊方法，已被上方取代
//     public void updateReped(Integer groomerId, Connection con);
     public GroVO findByPrimaryKey(Integer groomerId);
}
