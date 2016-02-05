package action.outlogin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UserOutLogin extends ActionSupport {
	@Override
	public String execute() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("useremail");
		session.removeAttribute("usernick");
		session.removeAttribute("userid");
		session.removeAttribute("isFirstToUse");
		
		Cookie useridcookie = new Cookie("userid", null);
		Cookie useremailcookie = new Cookie("useremail", null);
		Cookie usernickcookie = new Cookie("usernick", null);
		
		useremailcookie.setMaxAge(0);
		useridcookie.setMaxAge(0);
		usernickcookie.setMaxAge(0);
		
		ServletActionContext.getResponse().addCookie(usernickcookie);
		ServletActionContext.getResponse().addCookie(useridcookie);
		ServletActionContext.getResponse().addCookie(useremailcookie);
		
		return SUCCESS;
		
		
	}
}
