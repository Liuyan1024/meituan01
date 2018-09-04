package cn.edu.zhku.meituan.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbMerchants implements Serializable{
    private Long cid;

    private Long categoryId;

    private String name;

    private String image;

    private String phone;

    private String address;

    private String description;

    private Integer saleNum;

    private Integer dist;

    private Integer deliverTime;

    private Long deliverPrice;

    private Long startPrice;

    private String estimate;

    private Date created;

    private Date updated;
    
    

    @Override
	public String toString() {
		return "TbMerchants [cid=" + cid + ", categoryId=" + categoryId + ", name=" + name + ", image=" + image
				+ ", phone=" + phone + ", address=" + address + ", description=" + description + ", saleNum=" + saleNum
				+ ", dist=" + dist + ", deliverTime=" + deliverTime + ", deliverPrice=" + deliverPrice + ", startPrice="
				+ startPrice + ", estimate=" + estimate + ", created=" + created + ", updated=" + updated + "]";
	}

	public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    public Integer getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Integer deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Long getDeliverPrice() {
        return deliverPrice;
    }

    public void setDeliverPrice(Long deliverPrice) {
        this.deliverPrice = deliverPrice;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate == null ? null : estimate.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}