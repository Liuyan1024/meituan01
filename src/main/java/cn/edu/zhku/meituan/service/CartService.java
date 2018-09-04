package cn.edu.zhku.meituan.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.meituan.pojo.TbItem;
import cn.edu.zhku.meituan.util.E3Result;

public interface CartService {

	public List<TbItem> getCartList(HttpServletRequest request);
	public E3Result clearCartItem(HttpServletRequest request,HttpServletResponse response);
}
