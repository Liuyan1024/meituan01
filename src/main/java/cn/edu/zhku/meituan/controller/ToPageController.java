package cn.edu.zhku.meituan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.zhku.meituan.pojo.TbMerchants;
import cn.edu.zhku.meituan.pojo.TbOrder;
import cn.edu.zhku.meituan.pojo.TbUser;
import cn.edu.zhku.meituan.service.MerchantsService;
import cn.edu.zhku.meituan.service.OrderService;

@Controller
public class ToPageController {

	@Autowired
	private MerchantsService merchantsService;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * Ìø×ªµ½Ê×Ò³
	 * @return
	 */
	@RequestMapping("/index")
	public String toIndex() {
		return "index";
	}
	@RequestMapping("/success")
	public String toSuccess() {
		return "success";
	}
	@RequestMapping("/regist")
	public String toRegister() {
		return "user_register";
	}
	@RequestMapping("/login")
	public String toLogin() {
		return "login";
	}
	@RequestMapping("/waimai_index")
	public String toWaimai_index(Model model,HttpSession session) {
		TbUser  user =  (TbUser) session.getAttribute("usercode");
		model.addAttribute("user", user);
		List<TbMerchants> list = merchantsService.getMerchantsList();
		model.addAttribute("list",list);
		return "waimai_index";
	}
	@RequestMapping("/shop")
	public String toShop() {
		return "shop";
	}
	@RequestMapping("/mer/re")
	public String tomerregist(TbMerchants merchants) {
		System.out.println(merchants);
		return "shop_register";
	}
	@RequestMapping("/shop_login")
	public String toMerLogin() {
		return "shop_login";
	}
	@RequestMapping("/shop_regist")
	public String toMerRegist() {
		return "shop_register";
	}
	@RequestMapping("/shop_manager")
	public String toShoMmanager() {
		return "shop_manager";
	}
	@RequestMapping("/comment/{orderId}")
	public String toComment(@PathVariable Long orderId,Model model) {
//		System.out.println(orderId);
		TbOrder tbOrder = orderService.findOrderById(orderId);
		Long id = tbOrder.getMerid();
		TbMerchants merchants = merchantsService.getMerchantsById(id);
		model.addAttribute("order",tbOrder);
		model.addAttribute("merchants",merchants);
		return "comment";
	}
	
	
	
	
}
