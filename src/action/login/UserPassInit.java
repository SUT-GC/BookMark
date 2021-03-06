package action.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import encrypt.base64.Base64Util;

public class UserPassInit extends ActionSupport {
	private int userid;
	private String useremail;
	private String usernike;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUsernike() {
		return usernike;
	}
	public void setUsernike(String usernike) {
		this.usernike = usernike;
	}
	@Override
	public String execute() throws Exception {
		userid = -1;
		useremail = null;
		usernike = null;
		
		if(userid == -1 || useremail == null || usernike == null){
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("userid")){
					userid = Integer.parseInt(cookie.getValue());
				}
				if(cookie.getName().equals("usernick")){
					usernike = Base64Util.decodeToString(cookie.getValue());
				}
				if(cookie.getName().equals("useremail")){
					useremail = cookie.getValue();
				}
			}
		}
		if(userid == -1 || useremail == null || usernike == null){
			return "session";
		}
		return SUCCESS;
	}
}
