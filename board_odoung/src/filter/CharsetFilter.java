package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

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
		request.setCharacterEncoding(charset); //서블릿 수행 전 전처리
		chain.doFilter(request, response); //실제 서블릿이 수행 할 일 >> 이걸 주석하면 필터링만함
	}

	@Override
	public void destroy() {
	}
}
