package cn.edu.zhku.meituan.controller;

import java.io.UnsupportedEncodingException;
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
import cn.edu.zhku.meituan.pojo.TbUser;
import cn.edu.zhku.meituan.service.CommentService;
import cn.edu.zhku.meituan.service.OrderService;
import cn.edu.zhku.meituan.service.UserService;
import cn.edu.zhku.meituan.util.E3Result;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CommentService commentService;
	
	
	/**
	 * 注册功能
	 * @param user  user对象
	 * @return
	 */
	@RequestMapping("/user/register")
	public String register(TbUser user) {
		System.out.println(user);
		E3Result e3Result = userService.register(user);
		return "redirect:/login";
	}
	
	/**
	 * 登录功能
	 * @param username  用户名
	 * @param password  密码
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/user/login",produces="text/html;charset=utf-8")
	public String login(String username, String password, 
			HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) throws UnsupportedEncodingException {
		E3Result e3Result = userService.userLogin(username, password);
		//判断是否登录成功
		if(e3Result.getStatus() == 200) {
			TbUser user = (TbUser) e3Result.getData();
			//如果service校验通过，将用户身份记录到session
			session.setAttribute("usercode", user);
			model.addAttribute("user", user);
			//返回结果
			return "redirect:/index";
		}
		session.setAttribute("errormes", "error");
		return "redirect:/login";
	}
	
	/**
	 * 退出账号
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/logout")
	public String logout(HttpSession session)throws Exception{
		
		//session失效
		session.invalidate();
		//重定向到登录页面
		return "redirect:/login";
		
	}
	
	
	
	/**
	 * 根据用户id查询用户信息
	 * @param uid  用户的id
	 * @return
	 */
	@RequestMapping("/user/select/{uid}")
	@ResponseBody
	public TbUser findUserInfoByUserId(@PathVariable Long uid) {
		TbUser user = userService.selectUserInfoByUserId(uid);
		return user;
	}
	
	
	/**
	 * 从session中取用户信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/user/select")
	@ResponseBody
	public TbUser getUser(HttpSession session) {
		TbUser user = (TbUser) session.getAttribute("usercode");
		return user;
	}
	
	/**
	 * 用户确认订单（修改订单状态）
	 * @param id
	 * @param status
	 */
	@RequestMapping("/receive/{id}")
	@ResponseBody
	public String updateStatus(@PathVariable Long id ) {
		int status=4;
		orderService.updateOrder(id, status);
		return "success";
	}
	
	/**
	 * 用户评论
	 * @param comment
	 * @param session
	 */
	public void comment(TbComment comment,HttpSession session) {
		//从session中取用户id
		TbUser user = (TbUser) session.getAttribute("usercode");
		comment.setUserid(user.getId());
		//添加评论
		commentService.addComment(comment);
	}
	
	/**
	 * 根据用户id查看用户评论
	 */
	public void findComment(HttpSession session,Model model) {
		//从session中取用户id
		TbUser user = (TbUser) session.getAttribute("usercode");
		//根据用户id查看用户评论
		List<TbComment> list = commentService.geTbComment(user.getId());
		model.addAttribute(list);
	}
	
}
