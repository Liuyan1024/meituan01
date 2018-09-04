package cn.edu.zhku.meituan.service;

import cn.edu.zhku.meituan.pojo.TbOrderShipping;
import cn.edu.zhku.meituan.util.E3Result;

public interface ShippingService {

	public E3Result insertShipping(TbOrderShipping orderShipping);
	public TbOrderShipping findTbOrderShippingByOid(Long oid);
}
