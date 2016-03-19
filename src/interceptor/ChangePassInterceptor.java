package interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import dao.bmdb.operate.UserInforDao;
import encrypt.base64.Base64Util;

public class ChangePassInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
int userid = -1;
		
		
		String useremail =null; 
		String usernick = null; 

		
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if (cookies != null && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userid")) {
					userid = Integer.parseInt(cookie.getValue());
				}
				if (cookie.getName().equals("useremail")) {
					useremail = cookie.getValue();
				}
				if (cookie.getName().equals("usernick")) {
					usernick = Base64Util.decodeToString(cookie.getValue());
				}
			}
		}
		
		if(userid == -1 || useremail == null || usernick == null){
			return "session";
		}
		
		return invocation.invoke();
	}

}
