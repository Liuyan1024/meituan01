package cn.edu.zhku.meituan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zhku.meituan.pojo.TbComment;
import cn.edu.zhku.meituan.pojo.TbUser;
import cn.edu.zhku.meituan.service.CommentService;
import cn.edu.zhku.meituan.service.OrderService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private OrderService orderService;

	/**
	 * 添加评论
	 * 
	 * @param comment
	 */
	@RequestMapping("/comment/add")
	@ResponseBody
	public String addComment(TbComment comment, HttpSession session) {
//		System.out.println(comment);
		TbUser tbUser = (TbUser) session.getAttribute("usercode");
		comment.setUserid(tbUser.getId());
		
//		System.out.println(comment.getOrderid());
		
		orderService.updateOrder(comment.getOrderid(), 5);
		commentService.addComment(comment);
		
		return "success";
	}

	/**
	 * 根据店家id查看用户评论
	 */
	@RequestMapping("/comment/mer/{mid}")
	public String findComment(@PathVariable Long mid, Model model) {
		List<TbComment> list = commentService.geTbCommentByMid(mid);
		model.addAttribute("commentList", list);
		return "";
	}
}
