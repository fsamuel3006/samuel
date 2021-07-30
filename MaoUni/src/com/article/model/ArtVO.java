package com.article.model;

import java.sql.Date;

//���Ѥ峹    article
public class ArtVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer  id;              //  ART_ID         1.�峹ID ( PK )
	private Integer  artt_id;         //  ART_ARTT_ID    2.����ID ( FK )
	private String   name;            //  ART_NAME       3.�峹�W��
	private byte[]   pic;             //  ART_PIC        4.�峹�Ϥ�
	private String   contnt;          //  ART_CONTENT    5.�峹���e
	private Date     update;          //  ART_UPDATE     6.�o�G�ɶ�
	
	
                                  //  ART_ID         1.�峹ID ( PK )
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
                                    //  ART_ARTT_ID    2.����ID ( FK )	
	public Integer getArtt_id() {
		return artt_id;
	}
	public void setArtt_id(Integer artt_id) {
		this.artt_id = artt_id;
	}
	                              //  ART_NAME       3.�峹�W��
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
                                     //  ART_PIC        4.�峹�Ϥ�
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
                                    //  ART_CONTENT    5.�峹���e
	public String getContnt() {
		return contnt;
	}
	public void setContnt(String contnt) {
		this.contnt = contnt;
	}
                                     //  ART_UPDATE     6.�o�G�ɶ�
	public Date getUpdate() {
		return update;
	}
	public void setUpdate(Date update) {
		this.update = update;
	}
	
	
	
}
