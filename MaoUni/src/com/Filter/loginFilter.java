package com.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;

//@WebFilter("/loginFilter")
public class loginFilter implements Filter {

	public loginFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//doFilter每一次請求被攔截都會執行
		//首先要做強制轉換，這樣才可以拿取session↓↓
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		
		String uri =req.getRequestURI(); //抓取當前網址後面兩條斜線的值
//		String contextPath = req.getContextPath();//抓取網址最後的值(就只有jsp)，其實不用用下面轉來轉去，單純用uri就可以去比對了
//		String path = uri.substring(contextPath.length());
	
		if(uri.contains("/member/memberpage.jsp")) { 
		//uri會抓到的當前位子（網址）關鍵字去對比，當前網址是否有「contains包含」，equals不知為何上次不成功

		HttpSession session = req.getSession();
		Object memberVO = session.getAttribute("MemberVO"); 
//		這邊要抓取上次登入的servlet存入的東西，不然沒有東西可驗

		if(memberVO != null) {
			
			chain.doFilter(req, res); //session不是空的話就放行
						
		}else{
			res.sendRedirect(req.getContextPath() + "/front-end/Home/login.jsp"); //空的話就去登入頁面
			}
		
		}else {
			
			chain.doFilter(req, res);
		}	
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
}		
