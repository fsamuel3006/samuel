package com.member.model;

import java.util.List;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberService {

	private MemberDAO_interface dao;
	public MemberService() {
		dao = new MemberJDBCDAO();
	}

	public void update(MemberVO memberVO) {	
		dao.update(memberVO);
	}

	public void delete(Integer memId) {
		dao.delete(memId);
	}
	
	public MemberVO findByPrimaryKey(Integer memId) { 
		return dao.findByPrimaryKey(memId);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
	public String checkemail(String memEmail) {
		return dao.checkemail(memEmail);
	}	
	

	public MemberVO getOneMember(Integer memid) { //查單筆
		return dao.findByPrimaryKey(memid);
	}	
	
	public void insert(String memName, String memEmail, String memPassword, String memIdenity, String memGender,
			Integer memPh, String memAddres, Date memBirthday, Integer memPosition, Integer memReserve, Integer memSurvive) {

		MemberVO memberVO = new MemberVO();
//		memberVO.setMemId(memId);
		memberVO.setMemName(memName);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemPassword(memPassword);
		memberVO.setMemIdenity(memIdenity);
		memberVO.setMemGender(memGender);
		memberVO.setMemPh(memPh);
		memberVO.setMemAddres(memAddres);
		memberVO.setMemBirthday(memBirthday);
		memberVO.setMemPosition(memPosition);
		memberVO.setMemReserve(memReserve);
		memberVO.setMemSurvive(memSurvive);
//		memberVO.setMemUpdate(memUpdate);
		dao.insert(memberVO);
	}

	
	public void signup(String memName, String memEmail, String memPassword, String memIdenity, String memGender,
			Integer memPh, String memAddres, Date memBirthday) { //註冊啦
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMemName(memName);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemPassword(memPassword);
		memberVO.setMemIdenity(memIdenity);
		memberVO.setMemGender(memGender);
		memberVO.setMemPh(memPh);
		memberVO.setMemAddres(memAddres);
		memberVO.setMemBirthday(memBirthday);
		
		dao.signup(memberVO);
	}

	public MemberVO findByUseremailAndpassword(String memEmail, String memPassword) { //會員登入
		return dao.findByUseremailAndpassword(memEmail,memPassword);
		
	}

	public MemberVO OutUser(String memEmail, String memPassword) { //登出
		return dao.OutUser(memEmail, memPassword);
	}

	public MemberVO MemberVO(String memEmail) {
		return null;
	}

	

	}
	

	
