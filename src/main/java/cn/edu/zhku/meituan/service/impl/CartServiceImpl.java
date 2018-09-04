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

	//���浽cookie�еĹ��ﳵ����
	private String CART = "cart";
	
	/**
	 * ȡcookie�еĹ��ﳵ�б�
	 */
	public List<TbItem> getCartList(HttpServletRequest request) {
		//ȡ���ﳵ�б�
		String json = CookieUtils.getCookieValue(request, CART, true);
		//�ж�json�Ƿ�Ϊnull
		if (StringUtils.isNotBlank(json)) {
			//��jsonת������Ʒ�б���
			List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
			return list;
		}
		return new ArrayList<>();
	}

	
	/**
	 * ��չ��ﳵ
	 */
	public E3Result clearCartItem(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.deleteCookie(request, response, CART);
		return E3Result.ok();
	}

	
	
	
	

}
