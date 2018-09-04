package cn.edu.zhku.meituan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zhku.meituan.mapper.TbOrderShippingMapper;
import cn.edu.zhku.meituan.pojo.TbOrderShipping;
import cn.edu.zhku.meituan.pojo.TbOrderShippingExample;
import cn.edu.zhku.meituan.pojo.TbOrderShippingExample.Criteria;
import cn.edu.zhku.meituan.service.ShippingService;
import cn.edu.zhku.meituan.util.E3Result;

@Service
public class ShippingServiceImpl implements ShippingService{

	@Autowired
	private TbOrderShippingMapper shippingMapper;
	
	/**
	 * 插入收货信息到数据库
	 */
	public E3Result insertShipping(TbOrderShipping orderShipping) {
		if (orderShipping==null) {
			return E3Result.build(400, "用户收货信息不完整");
		}
		shippingMapper.insert(orderShipping);
		return E3Result.ok();
	}

	@Override
	public TbOrderShipping findTbOrderShippingByOid(Long oid) {
		TbOrderShippingExample example = new TbOrderShippingExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(oid);
		List<TbOrderShipping> list = shippingMapper.selectByExample(example);
		if(list!=null) {
			return list.get(0);
		}
		return null;
	}

}
