package com.appointment_form.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.appointment_form_details.model.ApmdVO;
import com.gschedule.model.SchDAO;
import com.gschedule.model.SchVO;
import com.service_list.model.SvcListDAO;
import com.service_list.model.SvcListVO;

public class ApmService {
	ApmDAO_interface dao;
	public ApmService() {
		this.dao = new ApmDAO();
	}
	
	public void addAppointment(Integer memId, Integer groomerId, Integer pid, Integer schId, Integer stime, Integer etime, Integer total,List<Integer> svcIdList) {
		ApmVO apmVO = new ApmVO();
		apmVO.setMemId(memId);
		apmVO.setGroomerId(groomerId);
		apmVO.setPid(pid);
		
		// 將取得的schId轉為日期
		SchDAO schDao = new SchDAO();
		SchVO schVO = schDao.getOneDaySchdule(schId);
		
		apmVO.setApmDate(schVO.getSchDate());
		apmVO.setStime(stime);
		apmVO.setEtime(etime);
		apmVO.setTotal(total);
		
		List<ApmdVO> list = new ArrayList<ApmdVO>();
		// list 裡為svcid,要取得其價格和名稱放入VO
		SvcListDAO svcListDao = new SvcListDAO();
		
		for(Integer svcId:svcIdList) {
			SvcListVO svcListVO = svcListDao.getItem(groomerId, svcId);
			ApmdVO apmdVO = new ApmdVO();
			apmdVO.setSvcId(svcId);
			apmdVO.setPrice(svcListVO.getPrice());
			apmdVO.setItem(svcListVO.getSvcItem());;
			
			list.add(apmdVO);
		}
		
		dao.insert(apmVO, list);
	}

	public Integer updateStatus(Integer apmId, Integer apmStatus) {
		
		Map<String, String[]> map = new LinkedHashMap();
		String[] apmIdStr = {apmId.toString()};
		map.put("apmId", apmIdStr);
		List<ApmVO> list = dao.getAll(map);
		Date changeDate = list.get(0).getApmDate();
		
		ApmVO apmVO = new ApmVO();
		apmVO.setApmId(apmId);
		apmVO.setApmStatus(apmStatus);
		apmVO.setApmDate(list.get(0).getApmDate());
		apmVO.setStime((list.get(0).getStime()));
		apmVO.setEtime((list.get(0).getEtime()));
		apmVO.setGroomerId(list.get(0).getGroomerId());
		
		 return dao.update(apmVO, changeDate);
	}
	
	
	public Integer addComment(Integer apmId, Integer groomerId, Integer star, String apmComment, byte[] photo) {
		

		Date changeDate = null;
		
		ApmVO apmVO = new ApmVO();
		apmVO.setApmId(apmId);
		apmVO.setGroomerId(groomerId);
		apmVO.setStar(star);
		apmVO.setApmComment(apmComment);
		if(photo.length != 0) {
			apmVO.setPhoto(photo);
		}
		
		return dao.update(apmVO, changeDate);
	}
	
	
	
	
	
	
	public List<ApmVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
	

	
//	public static void main(String[] args) {
//		ApmService apmSvc = new ApmService();
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(2);
//		apmSvc.addAppointment(2, 1, 2, java.sql.Date.valueOf("2021-08-06"), 45, 48, 910,list);
		
//		ApmVO apmVO = new ApmVO();
//		apmVO.setApmId(3);
//		apmVO.setGroomerId(1);;
//		apmVO.setApmStatus(2);
//		apmVO.setApmDate(java.sql.Date.valueOf("2021-07-10"));  
//		apmVO.setStime(1);
//		apmVO.setEtime(3);
//		apmVO.setStar(5);  
//		apmVO.setApmComment("成功複合修改!!!!!");
//		
//		File file = new File("D:\\專題\\MaoUni\\MaoUni.png");
//		try {
//			FileInputStream fin = new FileInputStream(file);
//			BufferedInputStream bfin = new BufferedInputStream(fin);
//			
//			byte[] photo = new byte[fin.available()];
//			fin.read(photo);
//			apmVO.setPhoto(photo);
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace(System.err);
//		} catch (IOException e) {
//			e.printStackTrace(System.err);
//		}
//		
//		
//		apmSvc.update(apmVO, java.sql.Date.valueOf("2021-07-10"));
//		
//		
//		Map<String, String[]> map = new LinkedHashMap<String, String[]>();
//		String[] month = {"7"};
//		map.put("month", month);
		
//		String[] apmId = {"4"};
//		map.put("apmId", apmId);
		
//		String[] memId = {"4"};
//		map.put("memId", memId);
		
//		String[] groomerId = {"1"};
//		map.put("groomerId", groomerId);
//		
//		String[] pid = {"2"};
//		map.put("pid", pid);
		
//		String[] apmstatus = {"3"};
//		map.put("apmstatus", apmstatus);
		
//		List<ApmVO> list = apmSvc.getAll(map);
//		for(ApmVO apmVO:list) {
//			System.out.println(apmVO.getApmId());
//			System.out.println(apmVO.getMemId());
//			System.out.println(apmVO.getGroomerId());
//			System.out.println(apmVO.getPid());
//			System.out.println(apmVO.getApmDate());
//			System.out.println(apmVO.getStime());
//			System.out.println(apmVO.getEtime());
//			System.out.println(apmVO.getTotal());
//			System.out.println(apmVO.getApmStatus());
//			System.out.println(apmVO.getStar());
//			System.out.println(apmVO.getApmComment());
//			System.out.println(apmVO.getPhoto());
//
//		}
//
//		
//	}

}
