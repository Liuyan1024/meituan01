package cn.edu.zhku.meituan.service;

import java.util.List;

import cn.edu.zhku.meituan.pojo.TbOrderItem;

public interface OrderItemService {
	public List<TbOrderItem> findTbOrderItem(Long oid);
}
