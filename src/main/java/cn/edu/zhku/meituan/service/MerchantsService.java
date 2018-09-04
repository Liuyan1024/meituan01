package cn.edu.zhku.meituan.service;

import java.util.List;

import cn.edu.zhku.meituan.pojo.TbItem;
import cn.edu.zhku.meituan.pojo.TbMerchants;
import cn.edu.zhku.meituan.util.E3Result;
import cn.edu.zhku.meituan.util.EasyUIDataGridResult;

public interface MerchantsService {

	public List<TbMerchants> getMerchantsByCid(Long cid);
	public EasyUIDataGridResult getMerchantsList(int page, int rows);
	public List<TbMerchants> getMerchantsList();
	public void delMerchants(Long id);
	public void updateMerchant(TbMerchants  merchants);
	public TbMerchants getMerchantsById(Long id);
	public Long addMerchants(TbMerchants merchants);
	public void updateMerchants(TbMerchants merchants);
	public E3Result MerchantsLogin(String username, String password);
	
}
