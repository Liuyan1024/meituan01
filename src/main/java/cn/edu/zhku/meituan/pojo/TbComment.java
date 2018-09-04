package cn.edu.zhku.meituan.pojo;

import java.io.Serializable;
import java.util.List;

public class TbComment extends TbOrder implements Serializable {
	private Long id;

	private Integer star;

	private String comment;

	private Long orderid;

	private Long userid;

	private Long merid;
	
	

	@Override
	public String toString() {
		return "TbComment [id=" + id + ", star=" + star + ", comment=" + comment + ", orderid=" + orderid + ", userid="
				+ userid + ", merid=" + merid + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getMerid() {
		return merid;
	}

	public void setMerid(Long merid) {
		this.merid = merid;
	}

}