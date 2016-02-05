package action.login;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.entity.UserInfor;
import dao.bmdb.operate.UserInforDao;
import encrypt.md5.MD5Util;

public class UserInit extends ActionSupport {
	private String keymd5;
	/*
	 * 1:初始化成功
	 * 0:初始化失败，请联系管理员
	 */
	private InputStream inputStream;
	
	public String getKeymd5() {
		return keymd5;
	}

	public void setKeymd5(String keymd5) {
		this.keymd5 = keymd5;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public String execute() throws Exception {
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		int userid = (Integer) httpSession.getAttribute("userid");
		String useremail = (String) httpSession.getAttribute("useremail");
		String usernike = (String) httpSession.getAttribute("usernick");
		String isFirstToUse = (String) httpSession.getAttribute("isFirstToUse");
		String result = "0";
//		System.out.println(userid+", "+useremail+","+usernike+","+isFirstToUse);

		if(keymd5.length() == 0){
			result = "-1";
		}else{
			UserInfor userInfor = new UserInfor(userid, useremail, usernike, MD5Util.makeSrcToMD5(keymd5));
			UserInforDao.insertUserInforWithoutLoginInfor(userInfor);
			
			httpSession.removeAttribute("isFirstToUse");
			result = "1";
		}
	

//		System.out.println("UserInitLogin的result = "+result );
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		
		return SUCCESS;
				
	}
}
