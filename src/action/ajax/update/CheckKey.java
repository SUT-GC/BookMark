package action.ajax.update;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.UserInforDao;
import dao.wbdb.operate.UserDao;
import encrypt.base64.Base64Util;
import encrypt.md5.MD5Util;

public class CheckKey extends ActionSupport {
	private String oldkey;
	private InputStream inputStream;
	public String getOldkey() {
		return oldkey;
	}
	public void setOldkey(String oldkey) {
		this.oldkey = oldkey;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	@Override
	/**
	 * 0:数据库链接错误
	 * 1:口令正确
	 * -1:口令错误
	 * -2:session过期
	 */
	public String execute() throws Exception {
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		int userid = -1;
		String useremail = null;
		String usernike = null;
		
		
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
					usernike =  Base64Util.decodeToString(cookie.getValue());
					count++;
				}
			}
			if (count == 3) {
				System.out.println("userid="+userid+"usernick="+usernike+"useremail="+useremail);
				if(userid != -1 && useremail != null && usernike != null){
					oldkey = MD5Util.makeSrcToMD5(oldkey);
					String userkey = UserInforDao.selectUserKeyMd5ByUseridUseremail(userid, useremail);
					if(oldkey.equals(userkey)){
						result = "1";
					}else{
						result = "-1";
					}
				}else{
					result = "-2";
				}
			} else {
				result = "-2";
			}
		} else {
			result = "-2";
		}
		
		System.out.println("result = "+result );
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
