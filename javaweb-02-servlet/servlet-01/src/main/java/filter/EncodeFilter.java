package filter;

import util.LogUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet Filter implementation class EncodeFilter
 */
@WebFilter(description = "报文编码Filter", urlPatterns = { "/*" })
public class EncodeFilter implements Filter {

	private final String ReqEncodeType = "utf-8";
	private final String ResContentType = "text/json;charset=utf-8";
	
    /**
     * Default constructor. 
     */
    public EncodeFilter() {
    	LogUtil.log("EncodeFilter contruct...");
    }

	/**
	 * 销毁：web服务器关闭的时候，过滤会销毁
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LogUtil.log("EncodeFilter 销毁...");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		LogUtil.log("EncodeFilter 开始设置编码格式");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		httpRequest.setCharacterEncoding(ReqEncodeType);
		
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.setContentType(ResContentType);
		LogUtil.log("EncodeFilter 设置编码格式完成");

		//让请求继续执行
		chain.doFilter(request, response);
		
	}

	/**
	 * 初始化：web服务器启动，就以及初始化了，随时等待过滤对象出现！
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LogUtil.log("EncodeFilter 初始化...");
	}

}
