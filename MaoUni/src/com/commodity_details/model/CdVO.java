package com.commodity_details.model;

public class CdVO implements java.io.Serializable{
	
	private Integer cdoId;//商品訂單ID
	private Integer cdItemId;//商品ID
	private Integer cdAmount;//商品數量
	private Integer cdMoney;//商品金額
	
	public Integer getCdoId() {
		return cdoId;
	}
	public void setCdoId(Integer cdoId) {
		this.cdoId = cdoId;
	}
	public Integer getCdItemId() {
		return cdItemId;
	}
	public void setCdItemId(Integer cdItemId) {
		this.cdItemId = cdItemId;
	}
	public Integer getCdAmount() {
		return cdAmount;
	}
	public void setCdAmount(Integer cdAmount) {
		this.cdAmount = cdAmount;
	}
	public Integer getCdMoney() {
		return cdMoney;
	}
	public void setCdMoney(Integer cdMoney) {
		this.cdMoney = cdMoney;
	}

}
