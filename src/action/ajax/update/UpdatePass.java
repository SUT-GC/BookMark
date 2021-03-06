package action.ajax.update;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.UserInforDao;
import dao.bmdb.operate.WebInforDao;
import dao.wbdb.operate.UserDao;
import encrypt.base64.Base64Util;
import encrypt.md5.MD5Util;

public class UpdatePass extends ActionSupport {
	private int userid;
	private String useremail;
	private String usernick;
	private String newpass1;
	private String newpass2;
	private String oldpass;

	private InputStream inputStream;

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

	public String getUsernick() {
		return usernick;
	}

	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}

	public String getNewpass1() {
		return newpass1;
	}

	public void setNewpass1(String newpass1) {
		this.newpass1 = newpass1;
	}

	public String getNewpass2() {
		return newpass2;
	}

	public void setNewpass2(String newpass2) {
		this.newpass2 = newpass2;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	

	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	@Override
	/**
	 * 0:服务器链接失败
	 * -2:session过期
	 * 1:更新成功
	 * -1:两次密码不一致
	 * -3:旧密码错误
	 */
	public String execute() throws Exception {
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		int userid = -1;
		
		
		String useremail =null; 
		String usernick = null; 

		
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
					usernick =  Base64Util.decodeToString(cookie.getValue());
					count++;
				}
			}
			if (count == 3) {
				if (userid == -1 || useremail == null || usernick == null) {
					result = "-2";
				} else {
					if(!MD5Util.makeSrcToMD5(oldpass).equals(UserDao.selectPassMd5(userid, useremail))){
						result = "-3";
					}else{
						if (!newpass1.equals(newpass2)) {
							result = "-1";
						} else {
							newpass2 = MD5Util.makeSrcToMD5(newpass2);
							UserDao.updatePassword(userid, newpass2);
							result = "1";
						}
					}
				}
			} else {
				result = "-2";
			}
		} else {
			result = "-2";
		}
		

		

		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}

}
