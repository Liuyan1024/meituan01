package cn.edu.zhku.meituan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.zhku.meituan.pojo.TbMerchants;
import cn.edu.zhku.meituan.pojo.TbUser;
import cn.edu.zhku.meituan.service.MerchantsService;
import cn.edu.zhku.meituan.service.OrderService;
import cn.edu.zhku.meituan.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MerchantsService merchantsService;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 用户
	 * @param model
	 * @return
	 */
	@RequestMapping("/adm/user/sel")
	public String selectUser(Model model) {
		List<TbUser> list = userService.findTbUser();
		model.addAttribute("ulist", list);
		
		return "admin_user";
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/adm/user/update/{id}")
	public String delUser(@PathVariable Long id ,Model model) {
		TbUser user = userService.selectUserInfoByUserId(id);
		model.addAttribute("user", user);
		return "admin_user_update";
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/adm/user/update")
	public String updatetUser(TbUser user,Model model) {
		userService.updateTbUser(user);
		
		List<TbUser> list = userService.findTbUser();
		model.addAttribute("ulist", list);
		
		return "admin_user";
	}
	
	
	/**
	 * 店家
	 */
	@RequestMapping("/adm/mer/sel")
	public String selectMer(Model model) {
		List<TbMerchants> mlist = merchantsService.getMerchantsList();
		model.addAttribute("mlist", mlist);
		return "admin_shop";
	}
	@RequestMapping("/adm/mer/del/{id}")
	public String delMer(@PathVariable Long id,Model model) {
		merchantsService.delMerchants(id);
		List<TbMerchants> mlist = merchantsService.getMerchantsList();
		model.addAttribute("mlist", mlist);
		return "admin_shop";
	}
	
	/**
	 * 到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/adm/mer/update/{id}")
	public String updateMer(@PathVariable Long id,Model model) {
		
		TbMerchants merchants = merchantsService.getMerchantsById(id);
		model.addAttribute("merchants", merchants);
		
		return "admin_mer_update";
	}
	
	/**修改
	 * 
	 */
	@RequestMapping("/adm/mer/update")
	public String updateMer(TbMerchants merchants, Model model) {
		merchantsService.updateMerchant(merchants);
		
		List<TbMerchants> mlist = merchantsService.getMerchantsList();
		model.addAttribute("mlist", mlist);
		
		return "admin_shop";
	}
	
	
	@RequestMapping("/admim")
	public String adm() {
		return "admlogin";
	}
	
	@RequestMapping("/adm/login")
	public String admlogin() {
		return "admin_index";
	}
	
	
}
