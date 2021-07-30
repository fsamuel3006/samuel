package com.gschedule.model;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;


public interface SchDAO_interface {
    public void insert();  													// 前端事件觸發時啟動遞迴指令自動新增當天至未來30日內資料
    public void updateByPresetVacation(Integer groomerId, String schDate);  // 啟動insert()後緊接著呼叫，修正預設休假日;只有在新增後才能呼叫，避免重置既有的日期行程
    public List<SchVO>  getByGroomerId(Integer groomerId);					// 取得某位美容師所有行程
    public List<SchVO>  getByGroomerId(Integer groomerId,Integer month);	// 取得某位美容師某月所有行程
    public void update(Integer groomerId,  Date date, Integer status, Integer stime, Integer etime);  				   // 美容師修正班表(尚未完成)
    public void update(Integer groomerId,  Date date, Integer status, Integer stime, Integer etime, Connection con);   // 因預約單狀態而更動班表
    public SchVO getOneDaySchdule(Integer schId);  																	   // 取得某天行程
    public SchVO getOneDaySchdule(String apmDate, Integer groomerId);
    
    
//    public void updateByAppointment(Integer groomerId, Integer stime, Integer etime); 
}
