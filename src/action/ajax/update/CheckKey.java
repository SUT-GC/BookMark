package action.ajax.update;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.UserInforDao;
import dao.wbdb.operate.UserDao;
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
		
		if(httpSession.getAttribute("userid") != null){
			userid = (int) httpSession.getAttribute("userid");
		}
		if(httpSession.getAttribute("useremail") != null){
			useremail =(String) httpSession.getAttribute("useremail");
		}
		if(httpSession.getAttribute("usernick") != null){
			usernike =(String) httpSession.getAttribute("usernick");
		}
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
		
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
