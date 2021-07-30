package com.groomer.model;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.MailService;

public class GroService {
	private GroDAO_interface dao;
	public GroService() {
		dao = new GroDAO();
	}

	public GroVO addGroomer(Integer memId, String gname, String center, Integer grange,
			byte[] license, byte[] pcrc, byte[] fid, byte[] bid ) {
		GroVO groVO = new GroVO();
		groVO.setMemId(memId);
		groVO.setGname(gname);
		groVO.setCenter(center);
		groVO.setGrange(grange);
		groVO.setLicense(license);
		groVO.setPcrc(pcrc);
		groVO.setFid(fid);
		groVO.setBid(bid);
		dao.insert(groVO);
		return groVO;
	}
	
	public Map<String, String> findPhotosBygmId(Integer groomerId){
		return dao.findPhotosBygmId(groomerId);
	}
	
	public List<GroVO> getAll(){
		return dao.getAll();
	}
	
	public void updateStatus(Integer groomerId, Integer result) {
		dao.updateStatus(groomerId, result);
		
		
		// ========================更新結果應寄出通知信告知美容師========================
				MailService mailSvc = new MailService();
				mailSvc.setTo("project60237@gmail.com");
				mailSvc.setSubject("MaoUni 毛孩有你  美容師資格審核結果通知");
				StringBuffer messageText = new StringBuffer();
				
				if(result == 1) 
					messageText.append("歡迎加入毛孩有你，恭喜通過美容師資格審核！") ;
				else if(result ==  2)
					messageText.append("很抱歉，您未通過美容師資格審核。") ;
					
				mailSvc.setMessageText(messageText.toString());
				mailSvc.start();

		// =====================使用執行續處理信件寄送曼導致畫面卡卡問題====================
		
		
		
	}
	
	public List<GroVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
	
	public Integer updateInfo(Integer groomerId, String gname, String[] schDateStr, Integer schStartTime, Integer schEndTime, byte[] avatar, String intro) {
		GroVO groVO = new GroVO();
		
		groVO.setGname(gname);
		
		// 處理 schDateStr 字串陣列  ex [0,1,2,3,4] 轉成01組成、長度為7的字串，1代表可預約，將有選擇到的index改為1
		StringBuffer schDate = new StringBuffer("0000000");
			for(int j = 0; j < schDateStr.length; j++) {
				Integer i = new Integer(schDateStr[j]);
				schDate.replace(i, (i+1), "1");
			}

		groVO.setSchDate(schDate.toString());
		
		// 處理 schTime 字串，轉成01組成、長度為48的字串，schStartTime~schEndTime的數字為1 (可預約)
		StringBuffer schTime = new StringBuffer("000000000000000000000000000000000000000000000000");
		StringBuffer startToEnd = new StringBuffer();
		
		for(int i = 0; i <= (schEndTime - schStartTime); i++) {
			startToEnd.append("1");
		}
		
		schTime.replace(schStartTime, (schEndTime + 1), startToEnd.toString());
		
//		replace(int start, int end, String str)
//		Replaces the characters in a substring of this sequence with characters in the specified String.

		groVO.setSchTime(schTime.toString());
		groVO.setAvatar(avatar);
		groVO.setIntro(intro);
		groVO.setGroomerId(groomerId);
		
		return dao.updateInfo(groVO);
		
	}
	
	public GroVO findByPrimaryKey(Integer groomerId) {
		return dao.findByPrimaryKey(groomerId);
	}
	
}
