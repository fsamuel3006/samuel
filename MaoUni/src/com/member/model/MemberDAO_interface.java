package com.member.model;

import java.sql.Date;
import java.util.*;

public interface MemberDAO_interface {
	
    public void insert(MemberVO memberVo);
    public void update(MemberVO memberVo);
    public void delete(Integer memId); 
    public MemberVO findByPrimaryKey(Integer memId);
    public List<MemberVO>getAll(); 
    public MemberVO findByUseremailAndpassword(String memEmail, String memPassword);
    public MemberVO OutUser(String memEmail, String memPassword); 
    public void signup(MemberVO memberVo); 
    public MemberVO upemail(String memEmail);
    public String checkemail(String memEmail);//查詢資料是否有人註冊了
//  public String updatepassword(String memPassword); //會員前台更新密碼
//	public Integer checkmemPosition(Integer memPosition); //尋找身分

}
