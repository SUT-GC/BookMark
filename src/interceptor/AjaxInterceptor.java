package interceptor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AjaxInterceptor extends AbstractInterceptor {


	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("这里走了ajax监听器");
		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();

		System.out.println("userid = "+httpSession.getAttribute("userid")+", useremail = "+httpSession.getAttribute("useremail")+", usernick = "+httpSession.getAttribute("usernick"));
		String result = "1";
		int userid = -1;
		String useremail = null;
		String usernick = null;
		if (userid == -1 || useremail == null || usernick == null) {

			/*
			 * 在cookies里查询cookie
			 */
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			if (cookies != null && cookies.length != 0) {
				int count = 0;
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("userid")) {
						userid = Integer.parseInt(cookie.getValue());
						count++;
					}
					if (cookie.getName().equals("useremail")) {
						useremail = cookie.getValue();
						count++;
					}
					if (cookie.getName().equals("usernick")) {
						usernick =  cookie.getValue();
						count++;
					}
				}
				if (count == 3) {
					result = "1";
				} else {
					result = "0";
				}
			} else {
				result = "0";
			}
		} else {
			result = "1";
		}

		System.out.println("ajax拦截器的result= " + result);
		
		if (result.equals("0")) {
			ServletActionContext.getRequest().getSession().setAttribute("sessionoverdue", "yes");
		}
		return arg0.invoke();
	}

}
