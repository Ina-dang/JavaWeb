package sec02.ex01;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*") //모든요청에 대해 이 필터를 적용시키겠다
public class CharsetFilter implements Filter {
	
	private String charset = "utf-8";
	private FilterConfig fc;
	
	
	
//alt + s + v 하면 기본으로 세 개 체크 되어있음
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		fc= filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//전 처리
		System.out.println("전처리");
		request.setCharacterEncoding(charset); //서블릿 수행 전 전처리
		HttpServletRequest req = (HttpServletRequest) request;
		long start = System.currentTimeMillis();
		chain.doFilter(request, response); //실제 서블릿이 수행 할 일 >> 이걸 주석하면 필터링만함
		System.out.println(req.getRequestURI() + "의 소요시간 " + (System.currentTimeMillis() - start) + "ms");
		//후 처리
		System.out.println("후처리");
	}

	@Override
	public void destroy() {
	}
}
