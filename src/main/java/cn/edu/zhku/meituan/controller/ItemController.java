package cn.edu.zhku.meituan.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.zhku.meituan.pojo.TbComment;
import cn.edu.zhku.meituan.pojo.TbItem;
import cn.edu.zhku.meituan.pojo.TbMerchants;
import cn.edu.zhku.meituan.service.CommentService;
import cn.edu.zhku.meituan.service.ItemService;
import cn.edu.zhku.meituan.service.MerchantsService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private MerchantsService merchantsService;
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * ���ݵ��id��ѯ���е����Ʒ
	 * @param cid  ���id
	 * @return
	 */
	@RequestMapping("/item/{id}")
	public String getItemListByCid(@PathVariable Long id,Model model) {
		//���ݵ��id��ѯ�����Ϣ
		TbMerchants merchant = merchantsService.getMerchantsById(id);
		model.addAttribute("merchant", merchant);
		//���÷�����ݵ��id��ѯ��Ʒ�б�
		List<TbItem> itemList = itemService.getItemListByCid(id);
		model.addAttribute("itemList", itemList);
		//System.out.println(itemList==null);
		
		List<TbComment> comments = commentService.geTbCommentByMid(id);
		model.addAttribute("comments", comments);
		
//		System.out.println(comments.get(0));
		
		return "shop";
	}
	
	/**
	 * �����Ʒ
	 * @param item
	 * @param pictureFile
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/mer/add")
	public String addItem(TbItem item , /*MultipartFile pictureFile,*/HttpSession session) throws IllegalStateException, IOException {
		//��session��ȡ�����Ϣ
		TbMerchants merchants = (TbMerchants) session.getAttribute("merchants");
		item.setCid(merchants.getCid());
		
		Long itemId = itemService.addItem(item);
		//����ͼƬ�ϴ�
		/*if(pictureFile!=null && pictureFile.getOriginalFilename()!=null && pictureFile.getOriginalFilename().length()>0){
			//ͼƬ�ϴ��ɹ��󣬽�ͼƬ�ĵ�ַд�����ݿ�
			String filePath = "/image/items";
			String newFileName = itemId.toString();
			//���ļ�
			File file = new java.io.File(filePath+newFileName);
			//���ڴ��е��ļ�д�����
			pictureFile.transferTo(file);
			
			//ͼƬ�ϴ��ɹ�������ͼƬ��ַд�����ݿ�
			item.setImage(filePath+newFileName);
			itemService.updateItem(item);
		}*/
		List<TbItem> itemList = itemService.getItemListByCid(merchants.getCid());
		session.setAttribute("itemList", itemList);
		return "redirect:/shop_manager";
	}
	
	
	/**
	 * ���ɾ����Ʒ
	 * @param id
	 */
	@RequestMapping("/mer/delete/{id}")
	@ResponseBody
	public String deleteItem(@PathVariable Long id,HttpSession session) {
		TbMerchants merchants = (TbMerchants) session.getAttribute("merchants");
		itemService.deleteItem(id);
		List<TbItem> itemList = itemService.getItemListByCid(merchants.getCid());
		session.setAttribute("itemList", itemList);
		return "success";
	}
	
}
