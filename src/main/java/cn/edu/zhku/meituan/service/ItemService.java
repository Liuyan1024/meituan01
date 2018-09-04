package cn.edu.zhku.meituan.service;

import java.util.List;

import cn.edu.zhku.meituan.pojo.TbItem;

public interface ItemService {
	
	public List<TbItem> getItemListByCid(long cid);
	public TbItem getItemById(Long itemid);
	public Long addItem(TbItem item);
	public void updateItem(TbItem item);
	public void deleteItem(Long id);
	
}
