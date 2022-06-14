package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebFilter(urlPatterns = { "/users/*", "/prd/*", "/cate/*", "/tk/doipass", "/tk/edit", "/cart/*" })
public class AuthenticationFilter implements Filter {

	public AuthenticationFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		HttpSession session = httpReq.getSession();
		User user = (User) session.getAttribute("userdn");

		if (user == null) {
			session.setAttribute("error", "Bạn cần đăng nhập mới có thể vào trang");
			httpRes.sendRedirect("/ASSM_SOF3011/tk/formlogin");

			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}