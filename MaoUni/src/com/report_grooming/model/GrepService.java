package com.report_grooming.model;

import java.util.List;

import com.groomer.model.GroService;

public class GrepService {
	private GrepDAO_interface dao;
	public GrepService() {
		this.dao = new GrepDAO();
	}
	
	public Integer addGreport(Integer apmId, Integer memId, Integer groomerId, String content) {
		GrepVO grepVO = new GrepVO();
		grepVO.setApmId(apmId);
		grepVO.setMemId(memId);
		grepVO.setGroomerId(groomerId);
		grepVO.setContent(content);
		
		return dao.insert(grepVO);
	}
	
	public String update(Integer rptId, Integer rptStatus, Integer groomerId) {
		GrepVO grepVO = new GrepVO();
		grepVO.setRptId(rptId);
		grepVO.setRptStatus(rptStatus);;
		grepVO.setGroomerId(groomerId);;
		return dao.update(grepVO);
	}
	
	public List<GrepVO> getAll(){
		return dao.getAll();
	}

	public static void main(String[] args) {
		GrepService grepSvc = new GrepService();
//		grepSvc.addGreport(4, 4, 2, "沒有把我的帥寶貝剪的帥帥的！！！！");
//		grepSvc.addGreport(4, 4, 2, "沒有把我的帥寶貝剪的帥帥的！！！！");
//		grepSvc.addGreport(4, 4, 2, "沒有把我的帥寶貝剪的帥帥的！！！！");
//		grepSvc.update(8, 1, 2);
//		grepSvc.update(9, 1, 2);
//		grepSvc.update(10, 1, 2);
		List<GrepVO> list = grepSvc.getAll();
		for(GrepVO f:list) {
			System.out.println(f.getApmId());
		}
	}
}
