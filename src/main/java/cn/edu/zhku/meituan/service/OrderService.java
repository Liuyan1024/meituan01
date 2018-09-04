package cn.edu.zhku.meituan.service;

import java.util.List;

import cn.edu.zhku.meituan.pojo.OrderInfo;
import cn.edu.zhku.meituan.pojo.TbOrder;
import cn.edu.zhku.meituan.pojo.TbOrderItem;
import cn.edu.zhku.meituan.pojo.TbOrderShipping;
import cn.edu.zhku.meituan.util.E3Result;

public interface OrderService {

	public E3Result createOrder(OrderInfo orderInfo);
	public List<TbOrder> findTbOrder();
	//public List<TbOrderItem> findTbOrderItemByOid(Long oid);
	public List<TbOrder> findTbOrderByUid(Long id);
	public List<TbOrder> findTbOrderBymid(Long id);
	public void updateOrder(Long id ,Integer status);
	public TbOrder findOrderById(Long id);
	
}
