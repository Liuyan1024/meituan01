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
	
	//���浽cookie�еĹ��ﳵ����
	private String CART = "cart";
	//���ﳵ���浽cookie�е�ʱ��
	private int EXPIRE = 3600;
	
	/**
	 * ��ӹ��ﳵ��cookie��
	 * @param itemId  ��Ʒid
	 * @param num     ��Ʒ����
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/add/{itemId}/{num}")
	@ResponseBody
	public String addCartItem(@PathVariable Long itemId, @PathVariable Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		// 1����cookie�в�ѯ��Ʒ�б�
		List<TbItem> cartList = cartService.getCartList(request);
		// 2���ж���Ʒ����Ʒ�б����Ƿ���ڡ�
		boolean hasItem = false;
		for (TbItem tbItem : cartList) {
			//����Ƚϵ��ǵ�ַ��Ӧ����ֵ�ıȽ�
			if (tbItem.getId() == itemId.longValue()) {
				// 3��������ڣ���Ʒ������ӡ�
				tbItem.setNum(tbItem.getNum() + num);
				hasItem = true;
				break;
			}
		}
		if (!hasItem) {
			// 4�������ڣ�������Ʒid��ѯ��Ʒ��Ϣ��
			TbItem tbItem = itemService.getItemById(itemId);
			//���ù�����Ʒ����
			tbItem.setNum(num);
			// 5������Ʒ��ӵ������б�
			cartList.add(tbItem);
		}
		// 6���ѹ�����Ʒ�б�д��cookie��  List�б�ת��json��
		CookieUtils.setCookie(request, response, CART, JsonUtils.objectToJson(cartList), 3600, true);
		return "success";
	}
	
	
	
	/**
	 * չʾ���ﳵ�б�
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cart/cart")
	@ResponseBody
	public List<TbItem> showCartList(HttpServletRequest request,Model model) {
		//��cookie��ȡ���ﳵ��Ʒ�б�
		List<TbItem> cartList = cartService.getCartList(request);
		//����Ʒ�б��ݸ�ҳ��
//		model.addAttribute("cartList", cartList);
		return cartList;
	}

	/**
	 * �޸Ĺ��ﳵ��Ʒ����
	 * @param itemId  ��Ʒid
	 * @param num     ��Ʒ����
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/update/{itemId}/{num}")
	@ResponseBody
	public String updateNum(@PathVariable Long itemId, @PathVariable Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		// 1��������������
		// 2����cookie��ȡ��Ʒ�б�
		List<TbItem> cartList =cartService.getCartList(request);
		// 3��������Ʒ�б��ҵ���Ӧ��Ʒ
		for (TbItem tbItem : cartList) {
			if (tbItem.getId() == itemId.longValue()) {
				// 4��������Ʒ����
				tbItem.setNum(num);
			}
		}
		// 5������Ʒ�б�д��cookie��
		CookieUtils.setCookie(request, response, CART, JsonUtils.objectToJson(cartList), EXPIRE, true);
		// 6����Ӧe3Result��Json���ݡ�
		return "success";
	}

	/**
	 * ɾ�����ﳵ��Ӧ����Ʒ
	 * @param itemId  ��Ʒid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/delete/{itemId}")
	@ResponseBody
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request,
			HttpServletResponse response) {
		// 1����url��ȡ��Ʒid
		// 2����cookie��ȡ���ﳵ��Ʒ�б�
		List<TbItem> cartList = cartService.getCartList(request);
		// 3�������б��ҵ���Ӧ����Ʒ
		for (TbItem tbItem : cartList) {
			if (tbItem.getId() == itemId.longValue()) {
				// 4��ɾ����Ʒ��
				cartList.remove(tbItem);
				break;
			}
		}
		// 5������Ʒ�б�д��cookie��
		CookieUtils.setCookie(request, response, CART, JsonUtils.objectToJson(cartList), EXPIRE, true);
		// 6�������߼���ͼ�����߼���ͼ����redirect��ת��
		return "success";
	}
	
	/**
	 * ��չ��ﳵ
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
