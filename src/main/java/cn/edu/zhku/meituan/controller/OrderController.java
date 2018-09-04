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
	 * չʾ����ҳ�棨��ѯ�ջ�����Ϣ��
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/order/order-cart")
	public String showOrderCart(HttpServletRequest request, HttpSession session) {
		// ȡ�û�id
		// TbUser user= (TbUser) session.getAttribute("usercode");
		// �����û�idȡ�ջ���ַ�б�
		// ʹ�þ�̬���ݡ�����
		// ȡ֧����ʽ�б�
		// ��̬����
		// ȡ���ﳵ�б�

		List<TbItem> cartList = cartService.getCartList(request);
		// �ѹ��ﳵ�б��ݸ�jsp
		request.setAttribute("cartList", cartList);
		// ����ҳ��
		return "order-cart";
	}

	/**
	 * ��������
	 * 
	 * @param orderInfo
	 *            ����ʵ����
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/create")
	public String createOrder(OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
		// ȡ�û���Ϣ
		TbUser user = (TbUser) session.getAttribute("usercode");
		// ���û���Ϣ��ӵ�orderInfo�С�
		orderInfo.setUserId(user.getId());
		orderInfo.setBuyerNick(user.getUsername());
		// ���÷������ɶ���
		E3Result e3Result = orderService.createOrder(orderInfo);
		// ����������ɳɹ�����Ҫɾ�����ﳵ
		if (e3Result.getStatus() == 200) {
			// ��չ��ﳵ
			cartService.clearCartItem(request, response);
		}
		// �Ѷ����Ŵ��ݸ�ҳ��
		session.setAttribute("orderId", e3Result.getData());
		session.setAttribute("payment", orderInfo.getPayment());
		// �����߼���ͼ
		return "redirect:/success";
	}
	
	/**
	 * ��ת����ѯ������ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping("/order")
	public String toOrder(Model model,HttpSession session) {
		//�����û�id��ѯ���ж���
		TbUser user = (TbUser) session.getAttribute("usercode");
		List<TbOrder> tbOrderList = orderService.findTbOrderByUid(user.getId());
		model.addAttribute("tbOrderList", tbOrderList);
		return "order";
	}
	
	/**
	 * ���ݶ���id��ѯ��������
	 * @param oid
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/{oid}")
	public String findOrderItemByOid(@PathVariable Long oid,Model model,HttpSession session) {
		//���ݶ���id��ѯ��������
		List<TbOrderItem> orderItemList = orderItemService.findTbOrderItem(oid);
		model.addAttribute("orderItemList", orderItemList);
		model.addAttribute("oid", oid);
		
		//���ݶ���id��ѯ�ջ���Ϣ
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
