package cn.edu.zhku.meituan.pojo;

import java.io.Serializable;
import java.util.List;


/**
 * 订单信息
 * @author 刘言
 *
 */
public class OrderInfo extends TbOrder implements Serializable {

	//订单项
	private List<TbOrderItem> orderItems;
	//收货人信息
	private TbOrderShipping orderShipping;
	
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}
