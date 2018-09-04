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
	 * ��ҳ��ѯ���е���б�
	 * @param page  ��ǰҳ��
	 * @param rows  ÿҳ��ʾ������
	 * @return
	 */
	@RequestMapping("/merchants/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		//���÷����ѯ��Ʒ�б�
		EasyUIDataGridResult result = merchantsService.getMerchantsList(page, rows);
		return result;
	}
	
	/**
	 * ���ݵ��id��ѯ����б�
	 * @param id  ���id
	 * @return
	 */
	@RequestMapping("/merchants/list/{id}")
	@ResponseBody
	public List<TbMerchants> geTbMerchantsByid(@PathVariable Long id){
		List<TbMerchants> list = merchantsService.getMerchantsByCid(id);
		return list;
	}
	
	/**
	 * ��ӵ��(����û�ע��)
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
		//����ͼƬ�ϴ�
		/*System.out.println("2222");
		if(true){
			//ͼƬ�ϴ��ɹ��󣬽�ͼƬ�ĵ�ַд�����ݿ�
			System.out.println("333");
			String filePath = "/image/merchants";
			String newFileName = id.toString();
			//���ļ�
			File file = new java.io.File(filePath+newFileName);
			//���ڴ��е��ļ�д�����
			pictureFile.transferTo(file);
			System.out.println("444");
			//ͼƬ�ϴ��ɹ�������ͼƬ��ַд�����ݿ�
			merchants.setImage(filePath+newFileName);
			merchantsService.updateMerchants(merchants);
		}*/
		return "shop_login";
	}
	
	/**
	 * ����û���¼
	 */
	@RequestMapping("/mer/login")
	public String login(String username, String password, 
			HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) {
		System.out.println(username);
		E3Result e3Result = merchantsService.MerchantsLogin(username, password);
		//�ж��Ƿ��¼�ɹ�
		if(e3Result.getStatus() == 200) {
			TbMerchants merchants = (TbMerchants) e3Result.getData();
			//���serviceУ��ͨ����������û���ݼ�¼��session
			session.setAttribute("merchants", merchants);
			//model.addAttribute("merchants", merchants);
			//���ؽ��
			//���÷�����ݵ��id��ѯ��Ʒ�б�
			List<TbItem> itemList = itemService.getItemListByCid(merchants.getCid());
			model.addAttribute("itemList", itemList);
			
			return "/shop_manager";
		}
		session.setAttribute("errormes", "error");
		return "redirect:/shop_login";
	}
	
	/**
	 * ���ݵ���û�id��ѯ��Ҷ���
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
	 * ���� ���id��ѯ��Ʒ
	 * @param id
	 * @param model
	 */
	public void getItemBymid(@PathVariable Long id,Model model) {
		List<TbItem> itemList = itemService.getItemListByCid(id);
		model.addAttribute("itemList", itemList);
	}
	
	/**
	 *��ҽӵ����޸Ķ���״̬��
	 */
	@RequestMapping("/mer/update/{oid}")
	@ResponseBody
	public String updateStatus(@PathVariable Long oid) {
		int status=3;
		orderService.updateOrder(oid, status);
		return "success";
	}
	
	/**
	 * ȡ��ҵ�ȫ������
	 * @param session
	 * @param model
	 */
	public void findComment(HttpSession session,Model model) {
		//ȡ��ҵ�id
		 TbMerchants merchants = (TbMerchants) session.getAttribute("merchants");
		 //ȡ�����б�
		 List<TbComment> list = commentService.geTbCommentByMid(merchants.getCid());
		 
		 List<TbComment> commentList = new ArrayList<TbComment>();
		 for (TbComment comment : list) {
			 //�õ�����id
			 Long oid = comment.getOrderid();
			 //���ݶ���id��ѯ������
			 TbOrder order = orderService.findOrderById(oid);
			 
			 comment.setOrderId(order.getOrderId());
			 comment.setPayment(order.getPayment());
			 comment.setPaymentTime(order.getCreateTime());
			 
			 commentList.add(comment);
		}
		 model.addAttribute("tbComment", commentList);
		 
	}
	
}
