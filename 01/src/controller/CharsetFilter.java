package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class CharsetFilter implements Filter{
	private String charset = "utf-8";
	private FilterConfig fc;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		fc = filterConfig;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("전처리");
		request.setCharacterEncoding(charset);
		HttpServletRequest req = (HttpServletRequest) request;
		long start = System.currentTimeMillis();
		chain.doFilter(request, response);
		System.out.printf("%s의 소요시간 %sms입니다 %n", req.getRequestURI(),System.currentTimeMillis()-start);
		System.out.println("후처리");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
