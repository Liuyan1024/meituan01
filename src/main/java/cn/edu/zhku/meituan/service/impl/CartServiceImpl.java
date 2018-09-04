package cn.edu.zhku.meituan.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.edu.zhku.meituan.pojo.TbItem;
import cn.edu.zhku.meituan.service.CartService;
import cn.edu.zhku.meituan.util.CookieUtils;
import cn.edu.zhku.meituan.util.E3Result;
import cn.edu.zhku.meituan.util.JsonUtils;
@Service
public class CartServiceImpl implements CartService {

	//保存到cookie中的购物车名字
	private String CART = "cart";
	
	/**
	 * 取cookie中的购物车列表
	 */
	public List<TbItem> getCartList(HttpServletRequest request) {
		//取购物车列表
		String json = CookieUtils.getCookieValue(request, CART, true);
		//判断json是否为null
		if (StringUtils.isNotBlank(json)) {
			//把json转换成商品列表返回
			List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
			return list;
		}
		return new ArrayList<>();
	}

	
	/**
	 * 清空购物车
	 */
	public E3Result clearCartItem(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.deleteCookie(request, response, CART);
		return E3Result.ok();
	}

	
	
	
	

}
