package cn.edu.zhku.meituan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zhku.meituan.pojo.TbComment;
import cn.edu.zhku.meituan.pojo.TbItem;
import cn.edu.zhku.meituan.pojo.TbMerchants;
import cn.edu.zhku.meituan.pojo.TbOrder;
import cn.edu.zhku.meituan.service.CommentService;
import cn.edu.zhku.meituan.service.ItemService;
import cn.edu.zhku.meituan.service.MerchantsService;
import cn.edu.zhku.meituan.service.OrderItemService;
import cn.edu.zhku.meituan.service.OrderService;
import cn.edu.zhku.meituan.util.E3Result;
import cn.edu.zhku.meituan.util.EasyUIDataGridResult;

@Controller
public class MerchantsController {
	
	@Autowired
	private MerchantsService merchantsService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	/**
	 * 分页查询所有店家列表
	 * @param page  当前页数
	 * @param rows  每页显示的条数
	 * @return
	 */
	@RequestMapping("/merchants/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		//调用服务查询商品列表
		EasyUIDataGridResult result = merchantsService.getMerchantsList(page, rows);
		return result;
	}
	
	/**
	 * 根据店家id查询店家列表
	 * @param id  店家id
	 * @return
	 */
	@RequestMapping("/merchants/list/{id}")
	@ResponseBody
	public List<TbMerchants> geTbMerchantsByid(@PathVariable Long id){
		List<TbMerchants> list = merchantsService.getMerchantsByCid(id);
		return list;
	}
	
	/**
	 * 添加店家(店家用户注册)
	 * @param item
	 * @param pictureFile
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value="/mer/regist")
	public String  addMerchant(TbMerchants merchants /*, MultipartFile pictureFile*/) throws Exception{
		System.out.println("1111");
		merchants.setCategoryId(1l);
		Long id = merchantsService.addMerchants(merchants);
		System.out.println(merchants);
		//进行图片上传
		/*System.out.println("2222");
		if(true){
			//图片上传成功后，将图片的地址写到数据库
			System.out.println("333");
			String filePath = "/image/merchants";
			String newFileName = id.toString();
			//新文件
			File file = new java.io.File(filePath+newFileName);
			//将内存中的文件写入磁盘
			pictureFile.transferTo(file);
			System.out.println("444");
			//图片上传成功，将新图片地址写入数据库
			merchants.setImage(filePath+newFileName);
			merchantsService.updateMerchants(merchants);
		}*/
		return "shop_login";
	}
	
	/**
	 * 店家用户登录
	 */
	@RequestMapping("/mer/login")
	public String login(String username, String password, 
			HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) {
		System.out.println(username);
		E3Result e3Result = merchantsService.MerchantsLogin(username, password);
		//判断是否登录成功
		if(e3Result.getStatus() == 200) {
			TbMerchants merchants = (TbMerchants) e3Result.getData();
			//如果service校验通过，将店家用户身份记录到session
			session.setAttribute("merchants", merchants);
			//model.addAttribute("merchants", merchants);
			//返回结果
			//调用服务根据店家id查询商品列表
			List<TbItem> itemList = itemService.getItemListByCid(merchants.getCid());
			model.addAttribute("itemList", itemList);
			
			return "/shop_manager";
		}
		session.setAttribute("errormes", "error");
		return "redirect:/shop_login";
	}
	
	/**
	 * 根据店家用户id查询店家订单
	 * @param id
	 * @return
	 */
	@RequestMapping("/mer/order")
	@ResponseBody
	public List<TbOrder> getOrder( Model model,HttpSession session){
		TbMerchants merchants = (TbMerchants) session.getAttribute("merchants");
		List<TbOrder> orderList = orderService.findTbOrderBymid(merchants.getCid());
		model.addAttribute("orderList", orderList);
		return orderList;
		
	}
	
	/**
	 * 根据 店家id查询商品
	 * @param id
	 * @param model
	 */
	public void getItemBymid(@PathVariable Long id,Model model) {
		List<TbItem> itemList = itemService.getItemListByCid(id);
		model.addAttribute("itemList", itemList);
	}
	
	/**
	 *店家接单（修改订单状态）
	 */
	@RequestMapping("/mer/update/{oid}")
	@ResponseBody
	public String updateStatus(@PathVariable Long oid) {
		int status=3;
		orderService.updateOrder(oid, status);
		return "success";
	}
	
	/**
	 * 取店家的全部评论
	 * @param session
	 * @param model
	 */
	public void findComment(HttpSession session,Model model) {
		//取店家的id
		 TbMerchants merchants = (TbMerchants) session.getAttribute("merchants");
		 //取评论列表
		 List<TbComment> list = commentService.geTbCommentByMid(merchants.getCid());
		 
		 List<TbComment> commentList = new ArrayList<TbComment>();
		 for (TbComment comment : list) {
			 //得到订单id
			 Long oid = comment.getOrderid();
			 //根据订单id查询订单项
			 TbOrder order = orderService.findOrderById(oid);
			 
			 comment.setOrderId(order.getOrderId());
			 comment.setPayment(order.getPayment());
			 comment.setPaymentTime(order.getCreateTime());
			 
			 commentList.add(comment);
		}
		 model.addAttribute("tbComment", commentList);
		 
	}
	
}
