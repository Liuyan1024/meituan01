package cn.edu.zhku.meituan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zhku.meituan.pojo.TbMerchants;
import cn.edu.zhku.meituan.pojo.TbUser;
import cn.edu.zhku.meituan.service.UserService;

public class LoginInterceptor implements HandlerInterceptor{
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// ִ��handler����֮ǰִ�д˷���
		// 1��ʵ��һ��HandlerInterceptor�ӿڡ�
		// 2����ִ��handler����֮ǰ��ҵ����
		//�õ������url
		String url = request.getRequestURI();
		System.out.println(url);
		//�ж��Ƿ��ǹ��� ��ַ
		//ʵ�ʿ�������Ҫ���� ��ַ�����������ļ���
		//...
		if(url.equals("/")||url.indexOf("index")>=0||url.indexOf("login")>=0||url.indexOf("regist")>=0){
			//����ǹ��� ��ַ�����
			return true;
		}
		//�ж��û������session���Ƿ����
		HttpSession session = request.getSession();
		TbUser user =(TbUser) session.getAttribute("usercode");
		TbMerchants merchants =(TbMerchants) session.getAttribute("merchants");
		//����û������session�д��ڷ���
		if(user!=null || merchants!=null){
			return true;
		}
		//ִ�е��������أ���ת����½ҳ�棬�û����������֤
//		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		response.sendRedirect("/");
		
		//�������false��ʾ���ز�����ִ��handler���������true��ʾ����
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// ִ��handler����֮�󣬲����Ƿ���ModelAndView����֮ǰ

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// ����ModelAndView֮�󡣿��Բ����쳣��

	}

	

	
}
