package com.gschedule.model;

import java.sql.Date;
import java.util.List;

import com.groomer.model.GroDAO;
import com.groomer.model.GroVO;

public class SchService {
	SchDAO_interface dao;
	public SchService() {
		this.dao = new SchDAO();
	}
	
	public void autoInsertData() {
		// 自動新增時是依據GROOMER表的SCHTIME新增
		dao.insert();
		// 需依據GROOMER表的SCHDATE(預設休假日)修正schstatus = repeat('0', 48)
		GroDAO groDao = new GroDAO();
		List<GroVO> list = groDao.getAll();
		for(GroVO groVO: list) {
			dao.updateByPresetVacation(groVO.getGroomerId(), groVO.getSchDate());
		}
	}
	
	public List<SchVO> getByGroomerId(Integer groomerId){
		return dao.getByGroomerId(groomerId);
	}
	
	public List<SchVO> getByGroomerId(Integer groomerId, Integer month){
		return dao.getByGroomerId(groomerId, month);
	}
	
//	Integer groomerId, Date date, Integer status, String schStatus
	public void update(Integer groomerId, Date date, Integer status, Integer stime, Integer etime) {
		dao.update(groomerId, date, status, stime, etime);
	}
	
//	public SchVO getOneDaySchdule(Integer schId) {
//		return dao.getOneDaySchdule(schId);
//	}
	
	public SchVO getOneDaySchdule(String apmDate, Integer groomerId) {
		return dao.getOneDaySchdule(apmDate, groomerId);
	}
	
	
//	public static void main(String[] args) {
//		SchService schSvc = new SchService();
//		schSvc.autoInsertData();
//		
//		List<SchVO> list = schSvc.getByGroomerId(3);
//		for(SchVO schVO: list) {
//			System.out.println("SCHID: " + schVO.getSchId());
//			System.out.println("GROOMERID: " + schVO.getGroomerId());
//			System.out.println("SCHDATE: " + schVO.getSchDate());
//			System.out.println("SCHSTATUS: " + schVO.getSchStatus());
//		}
//		
//		schSvc.update(1, java.sql.Date.valueOf("2021-08-05"), 2, 40, 48);
//		
//	}
}
