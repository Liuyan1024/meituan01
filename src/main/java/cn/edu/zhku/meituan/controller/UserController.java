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
	 * ע�Ṧ��
	 * @param user  user����
	 * @return
	 */
	@RequestMapping("/user/register")
	public String register(TbUser user) {
		System.out.println(user);
		E3Result e3Result = userService.register(user);
		return "redirect:/login";
	}
	
	/**
	 * ��¼����
	 * @param username  �û���
	 * @param password  ����
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/user/login",produces="text/html;charset=utf-8")
	public String login(String username, String password, 
			HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) throws UnsupportedEncodingException {
		E3Result e3Result = userService.userLogin(username, password);
		//�ж��Ƿ��¼�ɹ�
		if(e3Result.getStatus() == 200) {
			TbUser user = (TbUser) e3Result.getData();
			//���serviceУ��ͨ�������û���ݼ�¼��session
			session.setAttribute("usercode", user);
			model.addAttribute("user", user);
			//���ؽ��
			return "redirect:/index";
		}
		session.setAttribute("errormes", "error");
		return "redirect:/login";
	}
	
	/**
	 * �˳��˺�
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/logout")
	public String logout(HttpSession session)throws Exception{
		
		//sessionʧЧ
		session.invalidate();
		//�ض��򵽵�¼ҳ��
		return "redirect:/login";
		
	}
	
	
	
	/**
	 * �����û�id��ѯ�û���Ϣ
	 * @param uid  �û���id
	 * @return
	 */
	@RequestMapping("/user/select/{uid}")
	@ResponseBody
	public TbUser findUserInfoByUserId(@PathVariable Long uid) {
		TbUser user = userService.selectUserInfoByUserId(uid);
		return user;
	}
	
	
	/**
	 * ��session��ȡ�û���Ϣ
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
	 * �û�ȷ�϶������޸Ķ���״̬��
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
	 * �û�����
	 * @param comment
	 * @param session
	 */
	public void comment(TbComment comment,HttpSession session) {
		//��session��ȡ�û�id
		TbUser user = (TbUser) session.getAttribute("usercode");
		comment.setUserid(user.getId());
		//�������
		commentService.addComment(comment);
	}
	
	/**
	 * �����û�id�鿴�û�����
	 */
	public void findComment(HttpSession session,Model model) {
		//��session��ȡ�û�id
		TbUser user = (TbUser) session.getAttribute("usercode");
		//�����û�id�鿴�û�����
		List<TbComment> list = commentService.geTbComment(user.getId());
		model.addAttribute(list);
	}
	
}
