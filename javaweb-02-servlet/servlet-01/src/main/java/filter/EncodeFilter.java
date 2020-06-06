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
@WebFilter(description = "���ı���Filter", urlPatterns = { "/*" })
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
	 * ���٣�web�������رյ�ʱ�򣬹��˻�����
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LogUtil.log("EncodeFilter ����...");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		LogUtil.log("EncodeFilter ��ʼ���ñ����ʽ");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		httpRequest.setCharacterEncoding(ReqEncodeType);
		
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.setContentType(ResContentType);
		LogUtil.log("EncodeFilter ���ñ����ʽ���");

		//���������ִ��
		chain.doFilter(request, response);
		
	}

	/**
	 * ��ʼ����web���������������Լ���ʼ���ˣ���ʱ�ȴ����˶�����֣�
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LogUtil.log("EncodeFilter ��ʼ��...");
	}

}
