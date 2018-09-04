package cn.edu.zhku.meituan.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zhku.meituan.mapper.TbOrderItemMapper;
import cn.edu.zhku.meituan.mapper.TbOrderMapper;
import cn.edu.zhku.meituan.mapper.TbOrderShippingMapper;
import cn.edu.zhku.meituan.pojo.OrderInfo;
import cn.edu.zhku.meituan.pojo.TbItem;
import cn.edu.zhku.meituan.pojo.TbOrder;
import cn.edu.zhku.meituan.pojo.TbOrderExample;
import cn.edu.zhku.meituan.pojo.TbOrderExample.Criteria;
import cn.edu.zhku.meituan.pojo.TbOrderItem;
import cn.edu.zhku.meituan.pojo.TbOrderItemExample;
import cn.edu.zhku.meituan.pojo.TbOrderShipping;
import cn.edu.zhku.meituan.pojo.TbOrderShippingExample;
import cn.edu.zhku.meituan.service.ItemService;
import cn.edu.zhku.meituan.service.OrderService;
import cn.edu.zhku.meituan.util.E3Result;
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbOrderMapper orderMapper;
	
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 保存订单到数据库
	 */
	public E3Result createOrder(OrderInfo orderInfo) {
		List<TbOrderItem> orderItems = orderInfo.getOrderItems();
		TbOrderItem orderItem = orderItems.get(0);
		long itemid = orderItem.getItemId();
		TbItem tbItem = itemService.getItemById(itemid);
		//补全订单表的属性
		//1、未付款，2、已付款，3、未派送，4、已派送，5、订单完成，6、交易关闭
		orderInfo.setStatus(2);
		System.out.println(tbItem.getCid());
		orderInfo.setMerid(tbItem.getCid());
		orderInfo.setCreateTime(new Date());
		orderInfo.setUpdateTime(new Date());
		//插入订单表,返回订单id
		orderMapper.insert(orderInfo);
		long orderId = orderInfo.getOrderId();
		//System.out.println(orderId);
		//向订单明细表插入数据。
		for (TbOrderItem tbOrderItem : orderItems) {
			//补全订单项的属性		
			tbOrderItem.setOrderId(orderId);
			//向明细表插入数据
			orderItemMapper.insert(tbOrderItem);
		}
		//向订单物流表插入数据
		TbOrderShipping orderShipping = orderInfo.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		orderShippingMapper.insert(orderShipping);
		//返回E3Result，包含订单号
		return E3Result.ok(orderId);
	}

	/**
	 * 查询所有订单
	 */
	@Override
	public List<TbOrder> findTbOrder() {
		TbOrderExample example = new TbOrderExample();
		example.createCriteria();
		List<TbOrder> list = orderMapper.selectByExample(example );
		return list;
	}

	

	/**
	 * 根据用户id查询订单
	 */
	public List<TbOrder> findTbOrderByUid(Long id) {
		TbOrderExample example = new TbOrderExample();
		Criteria criteria  = example.createCriteria();
		criteria.andUserIdEqualTo(id);
		List<TbOrder> list = orderMapper.selectByExample(example );
		return list;
	}

	/**
	 * 根据店家id查询订单
	 * @param id
	 * @return
	 */
	public List<TbOrder> findTbOrderBymid(Long id) {
		TbOrderExample example = new TbOrderExample();
		Criteria criteria  = example.createCriteria();
		criteria.andMeridEqualTo(id);
		List<TbOrder> list = orderMapper.selectByExample(example );
		return list;
	}

	/**
	 * 修改订单状态
	 */
	public void updateOrder(Long id,Integer status) {
		//1、未付款，2、已付款，3、未派送，4、已派送，5、订单完成，6、交易关闭
		TbOrder order = orderMapper.selectByPrimaryKey(id);
		order.setStatus(status);
		orderMapper.updateByPrimaryKey(order);
	}

	/**
	 * 通过订单id查询订单
	 */
	public TbOrder findOrderById(Long id) {
		TbOrder order = orderMapper.selectByPrimaryKey(id);
		return order;
	}

	
}
