package cn.edu.zhku.meituan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.zhku.meituan.pojo.OrderInfo;
import cn.edu.zhku.meituan.pojo.TbComment;
import cn.edu.zhku.meituan.pojo.TbItem;
import cn.edu.zhku.meituan.pojo.TbOrder;
import cn.edu.zhku.meituan.pojo.TbOrderItem;
import cn.edu.zhku.meituan.pojo.TbOrderShipping;
import cn.edu.zhku.meituan.pojo.TbUser;
import cn.edu.zhku.meituan.service.CartService;
import cn.edu.zhku.meituan.service.CommentService;
import cn.edu.zhku.meituan.service.OrderItemService;
import cn.edu.zhku.meituan.service.OrderService;
import cn.edu.zhku.meituan.service.ShippingService;
import cn.edu.zhku.meituan.util.E3Result;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ShippingService shippingService;
	
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private CommentService commentService; 

	
	
	/**
	 * 展示订单页面（查询收货人信息）
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/order/order-cart")
	public String showOrderCart(HttpServletRequest request, HttpSession session) {
		// 取用户id
		// TbUser user= (TbUser) session.getAttribute("usercode");
		// 根据用户id取收货地址列表
		// 使用静态数据。。。
		// 取支付方式列表
		// 静态数据
		// 取购物车列表

		List<TbItem> cartList = cartService.getCartList(request);
		// 把购物车列表传递给jsp
		request.setAttribute("cartList", cartList);
		// 返回页面
		return "order-cart";
	}

	/**
	 * 创建订单
	 * 
	 * @param orderInfo
	 *            订单实体类
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/create")
	public String createOrder(OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
		// 取用户信息
		TbUser user = (TbUser) session.getAttribute("usercode");
		// 把用户信息添加到orderInfo中。
		orderInfo.setUserId(user.getId());
		orderInfo.setBuyerNick(user.getUsername());
		// 调用服务生成订单
		E3Result e3Result = orderService.createOrder(orderInfo);
		// 如果订单生成成功，需要删除购物车
		if (e3Result.getStatus() == 200) {
			// 清空购物车
			cartService.clearCartItem(request, response);
		}
		// 把订单号传递给页面
		session.setAttribute("orderId", e3Result.getData());
		session.setAttribute("payment", orderInfo.getPayment());
		// 返回逻辑视图
		return "redirect:/success";
	}
	
	/**
	 * 跳转到查询订单的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/order")
	public String toOrder(Model model,HttpSession session) {
		//根据用户id查询所有订单
		TbUser user = (TbUser) session.getAttribute("usercode");
		List<TbOrder> tbOrderList = orderService.findTbOrderByUid(user.getId());
		model.addAttribute("tbOrderList", tbOrderList);
		return "order";
	}
	
	/**
	 * 根据订单id查询订单详情
	 * @param oid
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/{oid}")
	public String findOrderItemByOid(@PathVariable Long oid,Model model,HttpSession session) {
		//根据订单id查询订单详情
		List<TbOrderItem> orderItemList = orderItemService.findTbOrderItem(oid);
		model.addAttribute("orderItemList", orderItemList);
		model.addAttribute("oid", oid);
		
		//根据订单id查询收货信息
		TbOrderShipping shipping = shippingService.findTbOrderShippingByOid(oid);
		model.addAttribute("shipping", shipping);
		
		TbUser user   =   (TbUser)session.getAttribute("usercode");
		List<TbComment> list = commentService.geTbCommentByOidAndUid(oid, user.getId());
		if(list!=null && list.size()>0) {
			model.addAttribute("comment", list.get(0));
		}
		
		return "order_detail";
	}

}
