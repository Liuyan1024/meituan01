package cn.edu.zhku.meituan.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.edu.zhku.meituan.mapper.TbMerchantsMapper;
import cn.edu.zhku.meituan.pojo.TbItem;
import cn.edu.zhku.meituan.pojo.TbItemExample;
import cn.edu.zhku.meituan.pojo.TbMerchants;
import cn.edu.zhku.meituan.pojo.TbMerchantsExample;
import cn.edu.zhku.meituan.pojo.TbUser;
import cn.edu.zhku.meituan.pojo.TbUserExample;
import cn.edu.zhku.meituan.pojo.TbMerchantsExample.Criteria;
import cn.edu.zhku.meituan.service.ItemService;
import cn.edu.zhku.meituan.service.MerchantsService;
import cn.edu.zhku.meituan.util.E3Result;
import cn.edu.zhku.meituan.util.EasyUIDataGridResult;
@Service
public class MerchantsServiceImpl implements MerchantsService {
	
	@Autowired
	private TbMerchantsMapper merchantsMapper;
	
	
	/**
	 * 查询所有店家（带分页）
	 */
	public EasyUIDataGridResult getMerchantsList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbMerchantsExample example = new TbMerchantsExample();
		List<TbMerchants> list = merchantsMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<TbMerchants> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	/**
	 * 根据店家分类id查询所有的店家
	 */
	public List<TbMerchants> getMerchantsByCid(Long cid) {
		TbMerchantsExample example = new TbMerchantsExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbMerchants> tbMerchantsList = merchantsMapper.selectByExample(example );
		return tbMerchantsList;
	}

	/**
	 * 查询所有店家
	 */
	public List<TbMerchants> getMerchantsList() {
		TbMerchantsExample example = new TbMerchantsExample();
		List<TbMerchants> tbMerchantsList = merchantsMapper.selectByExample(example );
		return tbMerchantsList;
	}
	
	//删除
	public void delMerchants(Long id) {
		merchantsMapper.deleteByPrimaryKey(id);
	}
	
	//
	public TbMerchants getMerchantsById(Long id) {
		TbMerchants merchants = merchantsMapper.selectByPrimaryKey(id);
		return merchants;
	}
	
	//修改
	public void updateMerchant(TbMerchants  merchants) {
		merchantsMapper.updateByPrimaryKeySelective(merchants);
	}
	
	/**
	 * 根据店家用户查询店家用户密码（店家用户登录）
	 */
	public E3Result MerchantsLogin(String username, String password) {
		// 1、判断用户和密码是否正确
		//根据用户名查询用户信息
		TbMerchantsExample example = new TbMerchantsExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(username);
		//执行查询
		List<TbMerchants> list = merchantsMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			//返回登录失败
			return E3Result.build(400, "用户名或密码错误");
		}
		//取用户信息
		 TbMerchants merchants = list.get(0);
		//判断密码是否正确
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(merchants.getEstimate())) {
			// 2、如果不正确，返回登录失败
			return E3Result.build(400, "用户名或密码错误");
		}
	
		// 6、把token返回
		return E3Result.ok(merchants);
	}
	
	
	/**
	 * 添加店家(店家注册)
	 */
	public Long addMerchants(TbMerchants merchants) {
		//数据有效性校验
		if (StringUtils.isBlank(merchants.getName()) || StringUtils.isBlank(merchants.getEstimate()) 
				|| StringUtils.isBlank(merchants.getPhone())) {
			E3Result.build(400, "用户数据不完整，注册失败");
		}
				
		//补全属性
		merchants.setCreated(new Date());
		merchants.setUpdated(new Date());
		//对密码进行md5加密
		String md5Pass = DigestUtils.md5DigestAsHex(merchants.getEstimate().getBytes());
		merchants.setEstimate(md5Pass);
		
		merchantsMapper.insert(merchants);
		return merchants.getCid();
	}

	/**
	 * 更新店家
	 */
	public void updateMerchants(TbMerchants merchants) {
		merchantsMapper.updateByPrimaryKey(merchants);
	}

	
	

	

}
