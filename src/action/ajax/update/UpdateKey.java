package action.ajax.update;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.UserInforDao;
import dao.bmdb.operate.WebInforDao;
import encrypt.md5.MD5Util;

public class UpdateKey extends ActionSupport {
	private String newkey1;
	private String newkey2;
	private String oldkey;
	private InputStream inputStream;
	public String getNewkey1() {
		return newkey1;
	}
	public void setNewkey1(String newkey1) {
		this.newkey1 = newkey1;
	}
	public String getNewkey2() {
		return newkey2;
	}
	public void setNewkey2(String newkey2) {
		this.newkey2 = newkey2;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String getOldkey() {
		return oldkey;
	}
	public void setOldkey(String oldkey) {
		this.oldkey = oldkey;
	}
	/**
	 * -2:session过期
	 * -1:两次口令不正确
	 * 0:数据库链接异常
	 * 1:修改成功
	 * -3:口令错误
	 */
	@Override
	public String execute() throws Exception {
		String result = "0";
		
		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		int userid = -1;
		if (httpSession.getAttribute("userid") != null) {
			userid = (int) httpSession.getAttribute("userid");
		}

		String useremail = (String) httpSession.getAttribute("useremail");
		String usernick = (String) httpSession.getAttribute("usernick");

		if (userid == -1 || useremail == null || usernick == null) {
			result = "-2";
		} else {
			if(!newkey1.equals(newkey2)){
				result = "-1";
			}else{
				String userkeymd5 = UserInforDao.selectUserKeyMd5ByUseridUseremail(userid, useremail);
				if(MD5Util.makeSrcToMD5(oldkey).equals(userkeymd5)){
					if(WebInforDao.updataWebinforResult(userid, newkey2, oldkey) == 1){
						UserInforDao.updateKey(userid, MD5Util.makeSrcToMD5(newkey2));
						result = "1";
					}else{
						result = "-3";
					}
				}else{
					result = "-3";
				}
			}
		}
		
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
