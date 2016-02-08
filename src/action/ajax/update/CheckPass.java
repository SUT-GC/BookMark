package action.ajax.update;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.UserInforDao;
import dao.wbdb.operate.UserDao;
import encrypt.md5.MD5Util;

public class CheckPass extends ActionSupport {
	private String oldpass;
	private int userid;
	private String usernick;
	private String useremail;
	private InputStream inputStream;
	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsernick() {
		return usernick;
	}

	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * -2:session过期
	 * -1:密码错误
	 * 1:认真成功
	 * 0:服务器异常
	 */
	@Override
	public String execute() throws Exception {
		oldpass = MD5Util.makeSrcToMD5(oldpass);
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		userid = -1;
		if( httpSession.getAttribute("userid") != null){
			userid = (int) httpSession.getAttribute("userid"); 
		}
		
		useremail =(String) httpSession.getAttribute("useremail"); 
		usernick = (String) httpSession.getAttribute("usernick"); 

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
			result = "-2";
		}else{
			String userpass = UserDao.selectPassMd5(userid, useremail, usernick);
			System.out.println("userpass="+userpass);
			System.out.println("oldpass"+oldpass);
			if(userpass.equals(oldpass)){
				result = "1";
			}else{
				result = "-1";
			}
		}
		
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
