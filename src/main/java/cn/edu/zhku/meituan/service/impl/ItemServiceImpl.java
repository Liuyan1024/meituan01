package cn.edu.zhku.meituan.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zhku.meituan.mapper.TbItemMapper;
import cn.edu.zhku.meituan.pojo.TbItem;
import cn.edu.zhku.meituan.pojo.TbItemExample;
import cn.edu.zhku.meituan.pojo.TbItemExample.Criteria;
import cn.edu.zhku.meituan.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	/**
	 * 根据店家的id查询所有的商品
	 */
	public List<TbItem> getItemListByCid(long cid) {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(cid);
		List<TbItem> tbItemList = itemMapper.selectByExample(example );
		return tbItemList;
	}

	/**
	 * 根据商品id查询商品信息
	 */
	public TbItem getItemById(Long itemid) {
		TbItem item = itemMapper.selectByPrimaryKey(itemid);
		return item;
	}
	
	/**
	 * 店家添加商品
	 */
	public Long addItem(TbItem item) {
		//补全属性
		item.setCreated(new Date());
		item.setUpdated(new Date());
		item.setStatus((byte) 1);
		item.setImage("/image/items/4.jpg");
		itemMapper.insert(item);
		//System.out.println(i);
		
		Long itemId = item.getId();
		//itemMapper.updateByPrimaryKey(item);
		return itemId;
	}

	/**
	 * 店家更新商品
	 */
	public void updateItem(TbItem item) {
		itemMapper.updateByPrimaryKey(item);	
	}


	/**
	 * 删除商品
	 */
	public void deleteItem(Long id) {
		itemMapper.deleteByPrimaryKey(id);
	}
	
	
	
	

}
