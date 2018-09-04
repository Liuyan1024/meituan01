package cn.edu.zhku.meituan.pojo;

import java.io.Serializable;
import java.util.List;


/**
 * ������Ϣ
 * @author ����
 *
 */
public class OrderInfo extends TbOrder implements Serializable {

	//������
	private List<TbOrderItem> orderItems;
	//�ջ�����Ϣ
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
