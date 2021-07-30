package com.announcemet.model;

import java.sql.Date;

//���i  ANNOUNCEMET
public class AnnVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer   id;              //  ANN_ID        1.���iID ( PK )
	private String    content;         //  ANN_CONTENT   2.���i���e
	private Date      update;          //  ANN_UPDATE    3.�W���ק窱�A�ɶ�
	private byte[]    pic;             //  ANN_PIC       4.���i�Ϥ�
	
	
                                //  ANN_ID        1.���iID ( PK )
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
                                     //  ANN_CONTENT   2.���i���e
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
                                     //  ANN_UPDATE    3.�W���ק窱�A�ɶ�
	public Date getUpdate() {
		return update;
	}
	public void setUpdate(Date update) {
		this.update = update;
	}
                                      //  ANN_PIC       4.���i�Ϥ�
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
}
