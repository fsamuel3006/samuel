package com.obuy.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ObuyVO implements java.io.Serializable {

	private Integer obuyId;//商品訂單ID
	private Integer oMemId; //購買商品的會員ID
	private Integer  oMoney; //總金額
	private Timestamp oDate; //購買時間
	private Integer oPaying; //購買方式：0:貨到付款  1:ATM轉帳  2:信用卡
	private Integer oSend; //送貨方式：0:宅配  1:便利商店
	private Integer oSurvive; //訂單狀態請看下面六個數字
//	0:等待處理
//	1:買家取消訂單
//	2:訂單成功
//	3:訂單已出貨
//	4:買家收到貨物
//	5:買家申請退貨/退款
//	6:訂單完成
	private String obuyOther; //備註
	
	
	public Integer getObuyId() {
		return obuyId;
	}
	public void setObuyId(Integer obuyId) {
		this.obuyId = obuyId;
	}
	public Integer getoMemId() {
		return oMemId;
	}
	public void setoMemId(Integer oMemId) {
		this.oMemId = oMemId;
	}
	public Integer getoMoney() {
		return oMoney;
	}
	public void setoMoney(Integer oMoney) {
		this.oMoney = oMoney;
	}
	public Timestamp getoDate() {
		return oDate;
	}
	public void setoDate(Timestamp oDate) {
		this.oDate = oDate;
	}
	public Integer getoPaying() {
		return oPaying;
	}
	public void setoPaying(Integer oPaying) {
		this.oPaying = oPaying;
	}
	public Integer getoSend() {
		return oSend;
	}
	public void setoSend(Integer oSend) {
		this.oSend = oSend;
	}
	public Integer getoSurvive() {
		return oSurvive;
	}
	public void setoSurvive(Integer oSurvive) {
		this.oSurvive = oSurvive;
	}
	public String getObuyOther() {
		return obuyOther;
	}
	public void setObuyOther(String obuyOther) {
		this.obuyOther = obuyOther;
	}
	
}
