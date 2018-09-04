package cn.edu.zhku.meituan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zhku.meituan.mapper.TbOrderItemMapper;
import cn.edu.zhku.meituan.pojo.TbOrderItem;
import cn.edu.zhku.meituan.pojo.TbOrderItemExample;
import cn.edu.zhku.meituan.pojo.TbOrderItemExample.Criteria;
import cn.edu.zhku.meituan.service.OrderItemService;
@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	private TbOrderItemMapper orderItemMapper;
	
	/**
	 * 根据订单id查看订单详情
	 */
	public List<TbOrderItem> findTbOrderItem(Long oid) {
		TbOrderItemExample example = new TbOrderItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(oid);
		List<TbOrderItem> list = orderItemMapper.selectByExample(example);
		return list;
	}

}
