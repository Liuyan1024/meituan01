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
	 * ���涩�������ݿ�
	 */
	public E3Result createOrder(OrderInfo orderInfo) {
		List<TbOrderItem> orderItems = orderInfo.getOrderItems();
		TbOrderItem orderItem = orderItems.get(0);
		long itemid = orderItem.getItemId();
		TbItem tbItem = itemService.getItemById(itemid);
		//��ȫ�����������
		//1��δ���2���Ѹ��3��δ���ͣ�4�������ͣ�5��������ɣ�6�����׹ر�
		orderInfo.setStatus(2);
		System.out.println(tbItem.getCid());
		orderInfo.setMerid(tbItem.getCid());
		orderInfo.setCreateTime(new Date());
		orderInfo.setUpdateTime(new Date());
		//���붩����,���ض���id
		orderMapper.insert(orderInfo);
		long orderId = orderInfo.getOrderId();
		//System.out.println(orderId);
		//�򶩵���ϸ��������ݡ�
		for (TbOrderItem tbOrderItem : orderItems) {
			//��ȫ�����������		
			tbOrderItem.setOrderId(orderId);
			//����ϸ���������
			orderItemMapper.insert(tbOrderItem);
		}
		//�򶩵��������������
		TbOrderShipping orderShipping = orderInfo.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		orderShippingMapper.insert(orderShipping);
		//����E3Result������������
		return E3Result.ok(orderId);
	}

	/**
	 * ��ѯ���ж���
	 */
	@Override
	public List<TbOrder> findTbOrder() {
		TbOrderExample example = new TbOrderExample();
		example.createCriteria();
		List<TbOrder> list = orderMapper.selectByExample(example );
		return list;
	}

	

	/**
	 * �����û�id��ѯ����
	 */
	public List<TbOrder> findTbOrderByUid(Long id) {
		TbOrderExample example = new TbOrderExample();
		Criteria criteria  = example.createCriteria();
		criteria.andUserIdEqualTo(id);
		List<TbOrder> list = orderMapper.selectByExample(example );
		return list;
	}

	/**
	 * ���ݵ��id��ѯ����
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
	 * �޸Ķ���״̬
	 */
	public void updateOrder(Long id,Integer status) {
		//1��δ���2���Ѹ��3��δ���ͣ�4�������ͣ�5��������ɣ�6�����׹ر�
		TbOrder order = orderMapper.selectByPrimaryKey(id);
		order.setStatus(status);
		orderMapper.updateByPrimaryKey(order);
	}

	/**
	 * ͨ������id��ѯ����
	 */
	public TbOrder findOrderById(Long id) {
		TbOrder order = orderMapper.selectByPrimaryKey(id);
		return order;
	}

	
}
