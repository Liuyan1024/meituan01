package cn.edu.zhku.meituan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zhku.meituan.pojo.TbItem;
import cn.edu.zhku.meituan.service.CartService;
import cn.edu.zhku.meituan.service.ItemService;
import cn.edu.zhku.meituan.util.CookieUtils;
import cn.edu.zhku.meituan.util.E3Result;
import cn.edu.zhku.meituan.util.JsonUtils;

@Controller
public class CartController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CartService cartService;
	
	//保存到cookie中的购物车名字
	private String CART = "cart";
	//购物车保存到cookie中的时间
	private int EXPIRE = 3600;
	
	/**
	 * 添加购物车到cookie中
	 * @param itemId  商品id
	 * @param num     商品数量
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/add/{itemId}/{num}")
	@ResponseBody
	public String addCartItem(@PathVariable Long itemId, @PathVariable Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		// 1、从cookie中查询商品列表。
		List<TbItem> cartList = cartService.getCartList(request);
		// 2、判断商品在商品列表中是否存在。
		boolean hasItem = false;
		for (TbItem tbItem : cartList) {
			//对象比较的是地址，应该是值的比较
			if (tbItem.getId() == itemId.longValue()) {
				// 3、如果存在，商品数量相加。
				tbItem.setNum(tbItem.getNum() + num);
				hasItem = true;
				break;
			}
		}
		if (!hasItem) {
			// 4、不存在，根据商品id查询商品信息。
			TbItem tbItem = itemService.getItemById(itemId);
			//设置购买商品数量
			tbItem.setNum(num);
			// 5、把商品添加到购车列表。
			cartList.add(tbItem);
		}
		// 6、把购车商品列表写入cookie。  List列表转成json串
		CookieUtils.setCookie(request, response, CART, JsonUtils.objectToJson(cartList), 3600, true);
		return "success";
	}
	
	
	
	/**
	 * 展示购物车列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cart/cart")
	@ResponseBody
	public List<TbItem> showCartList(HttpServletRequest request,Model model) {
		//从cookie中取购物车商品列表
		List<TbItem> cartList = cartService.getCartList(request);
		//把商品列表传递给页面
//		model.addAttribute("cartList", cartList);
		return cartList;
	}

	/**
	 * 修改购物车商品数量
	 * @param itemId  商品id
	 * @param num     商品数量
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/update/{itemId}/{num}")
	@ResponseBody
	public String updateNum(@PathVariable Long itemId, @PathVariable Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		// 1、接收两个参数
		// 2、从cookie中取商品列表
		List<TbItem> cartList =cartService.getCartList(request);
		// 3、遍历商品列表找到对应商品
		for (TbItem tbItem : cartList) {
			if (tbItem.getId() == itemId.longValue()) {
				// 4、更新商品数量
				tbItem.setNum(num);
			}
		}
		// 5、把商品列表写入cookie。
		CookieUtils.setCookie(request, response, CART, JsonUtils.objectToJson(cartList), EXPIRE, true);
		// 6、响应e3Result。Json数据。
		return "success";
	}

	/**
	 * 删除购物车对应的商品
	 * @param itemId  商品id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/delete/{itemId}")
	@ResponseBody
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request,
			HttpServletResponse response) {
		// 1、从url中取商品id
		// 2、从cookie中取购物车商品列表
		List<TbItem> cartList = cartService.getCartList(request);
		// 3、遍历列表找到对应的商品
		for (TbItem tbItem : cartList) {
			if (tbItem.getId() == itemId.longValue()) {
				// 4、删除商品。
				cartList.remove(tbItem);
				break;
			}
		}
		// 5、把商品列表写入cookie。
		CookieUtils.setCookie(request, response, CART, JsonUtils.objectToJson(cartList), EXPIRE, true);
		// 6、返回逻辑视图：在逻辑视图中做redirect跳转。
		return "success";
	}
	
	/**
	 * 清空购物车
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/clear")
	@ResponseBody
	public String clearCartItem(HttpServletRequest request ,HttpServletResponse response) {
		System.out.println("111");
		CookieUtils.deleteCookie(request, response, CART);
		System.out.println("222");
		return "success";
	}

	
}
