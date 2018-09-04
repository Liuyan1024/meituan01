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
		// 执行handler方法之前执行此方法
		// 1、实现一个HandlerInterceptor接口。
		// 2、在执行handler方法之前做业务处理
		//得到请求的url
		String url = request.getRequestURI();
		System.out.println(url);
		//判断是否是公开 地址
		//实际开发中需要公开 地址配置在配置文件中
		//...
		if(url.equals("/")||url.indexOf("index")>=0||url.indexOf("login")>=0||url.indexOf("regist")>=0){
			//如果是公开 地址则放行
			return true;
		}
		//判断用户身份在session中是否存在
		HttpSession session = request.getSession();
		TbUser user =(TbUser) session.getAttribute("usercode");
		TbMerchants merchants =(TbMerchants) session.getAttribute("merchants");
		//如果用户身份在session中存在放行
		if(user!=null || merchants!=null){
			return true;
		}
		//执行到这里拦截，跳转到登陆页面，用户进行身份认证
//		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		response.sendRedirect("/");
		
		//如果返回false表示拦截不继续执行handler，如果返回true表示放行
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// 执行handler方法之后，并且是返回ModelAndView对象之前

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 返回ModelAndView之后。可以捕获异常。

	}

	

	
}
