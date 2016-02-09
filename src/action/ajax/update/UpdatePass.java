package action.ajax.update;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.wbdb.operate.UserDao;
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
		userid = -1;
		if (httpSession.getAttribute("userid") != null) {
			userid = (int) httpSession.getAttribute("userid");
		}

		useremail = (String) httpSession.getAttribute("useremail");
		usernick = (String) httpSession.getAttribute("usernick");

		if (userid == -1 || useremail == null || usernick == null) {
			result = "-2";
		} else {
			if(!MD5Util.makeSrcToMD5(oldpass).equals(UserDao.selectPassMd5(userid, useremail, usernick))){
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

		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}

}
