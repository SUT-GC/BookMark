package interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import dao.bmdb.operate.UserInforDao;

public class ChangePassInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		int userid = -1;
		if(httpSession.getAttribute("userid") != null){
			userid = (int) httpSession.getAttribute("userid"); 
		}
		String useremail = (String) httpSession.getAttribute("useremail"); 
		String usernick = (String) httpSession.getAttribute("usernick"); 
		
		if(userid == -1 || useremail == null || usernick == null){
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("userid")){
					userid = Integer.parseInt(cookie.getValue());
				}
				if(cookie.getName().equals("usernick")){
					usernick = cookie.getValue();
				}
				if(cookie.getName().equals("useremail")){
					useremail = cookie.getValue();
				}
			}
		}
		
		if(userid == -1 || useremail == null || usernick == null){
			return "session";
		}
		
		return invocation.invoke();
	}

}
