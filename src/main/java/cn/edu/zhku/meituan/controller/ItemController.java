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
	 * 根据店家id查询所有店家商品
	 * @param cid  店家id
	 * @return
	 */
	@RequestMapping("/item/{id}")
	public String getItemListByCid(@PathVariable Long id,Model model) {
		//根据店家id查询店家信息
		TbMerchants merchant = merchantsService.getMerchantsById(id);
		model.addAttribute("merchant", merchant);
		//调用服务根据店家id查询商品列表
		List<TbItem> itemList = itemService.getItemListByCid(id);
		model.addAttribute("itemList", itemList);
		//System.out.println(itemList==null);
		
		List<TbComment> comments = commentService.geTbCommentByMid(id);
		model.addAttribute("comments", comments);
		
//		System.out.println(comments.get(0));
		
		return "shop";
	}
	
	/**
	 * 添加商品
	 * @param item
	 * @param pictureFile
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/mer/add")
	public String addItem(TbItem item , /*MultipartFile pictureFile,*/HttpSession session) throws IllegalStateException, IOException {
		//胸session中取店家信息
		TbMerchants merchants = (TbMerchants) session.getAttribute("merchants");
		item.setCid(merchants.getCid());
		
		Long itemId = itemService.addItem(item);
		//进行图片上传
		/*if(pictureFile!=null && pictureFile.getOriginalFilename()!=null && pictureFile.getOriginalFilename().length()>0){
			//图片上传成功后，将图片的地址写到数据库
			String filePath = "/image/items";
			String newFileName = itemId.toString();
			//新文件
			File file = new java.io.File(filePath+newFileName);
			//将内存中的文件写入磁盘
			pictureFile.transferTo(file);
			
			//图片上传成功，将新图片地址写入数据库
			item.setImage(filePath+newFileName);
			itemService.updateItem(item);
		}*/
		List<TbItem> itemList = itemService.getItemListByCid(merchants.getCid());
		session.setAttribute("itemList", itemList);
		return "redirect:/shop_manager";
	}
	
	
	/**
	 * 店家删除商品
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
