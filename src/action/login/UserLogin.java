package action.login;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.entity.LoginInfor;
import dao.bmdb.operate.UserInforDao;
import dao.wbdb.entity.User;
import dao.wbdb.operate.UserDao;
import encrypt.md5.MD5Util;

public class UserLogin extends ActionSupport {
	private String useremail;
	private String userpass;
	/*
	 * 错误码： 
	 * 2:登陆成功，第一次访问
	 * 1:登陆成功 
	 * 0:用户邮箱不存在 
	 * -1:用户密码不正确 
	 * -2:查询结果不为一，系统错误
	 */
	private InputStream inputStream;

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public String execute() throws Exception {
		List<User> user = UserDao.selectUserByUseremail(useremail);
		String result = "";
		if (user.size() < 1) {
			result = "0";
		} else if (user.size() > 1) {
			result = "-2";
		} else {
			System.out.println("用户输入的md5: "+MD5Util.makeSrcToMD5(userpass));
			System.out.println("保存的md5: "+user.get(0).getUserPass());
			if (user.get(0).getUserPass()
					.equals(MD5Util.makeSrcToMD5(userpass))) {

				User userinfo = user.get(0);

				HttpSession httpSession = ServletActionContext.getRequest()
						.getSession();
				
				httpSession.setAttribute("userid", userinfo.getUserId());
				httpSession.setAttribute("usernick",  userinfo.getUserNick());
				httpSession.setAttribute("useremail", userinfo.getUserEmail());
				
				Cookie useridcookie = new Cookie("userid", ""+userinfo.getUserId());
				Cookie usernickcookie = new Cookie("usernick", userinfo.getUserNick());
				Cookie useremailcookie = new Cookie("useremail", userinfo.getUserEmail());
				
				useremailcookie.setMaxAge(60*60*24*30);
				usernickcookie.setMaxAge(60*60*24*30);
				useridcookie.setMaxAge(60*60*24*30);
				
				ServletActionContext.getResponse().addCookie(useremailcookie);
				ServletActionContext.getResponse().addCookie(useridcookie);
				ServletActionContext.getResponse().addCookie(usernickcookie);
				
				result = "1";
				
				if(UserInforDao.selectThisUserIsFirstToUseBookMark(userinfo.getUserId(), userinfo.getUserEmail(), userinfo.getUserNick())){
					httpSession.setAttribute("isFirstToUse", "yes");
					System.out.println("这里走了UserLogin的检查为第一次访问");
					result = "2";
				}else{
					httpSession.setAttribute("isFirstToUse", "no");
				}
				
				if(result.equals("1")){
					LoginInfor loginInfor = new LoginInfor(new Timestamp(new Date().getTime()));
					loginInfor.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());
					loginInfor.setHostname(ServletActionContext.getRequest().getRemoteHost());
					UserInforDao.insertLoginInforByUseridUseremailUsernike(userinfo.getUserId(), userinfo.getUserEmail(), userinfo.getUserNick(), loginInfor);
				}
				
			} else {
				result = "-1";
			}
		}

	
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));

		return SUCCESS;
	}
}
