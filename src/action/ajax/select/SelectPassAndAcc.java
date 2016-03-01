package action.ajax.select;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.UserInforDao;
import dao.bmdb.operate.WebInforDao;
import encrypt.md5.MD5Util;
import encrypt.pbe.PBEUtil;

public class SelectPassAndAcc extends ActionSupport {
	private String key;
	private InputStream inputStream;
	private int webinforid;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public int getWebinforid() {
		return webinforid;
	}
	public void setWebinforid(int webinforid) {
		this.webinforid = webinforid;
	}
	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 * 0:数据库链接失败
	 * -1:口令不正确
	 * -2:session过期
	 * 2:没有保存账号密码
	 */
	@Override
	public String execute() throws Exception {
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		if (httpSession.getAttribute("sessionoverdue") != null
				&& httpSession.getAttribute("sessionoverdue").equals("yes")) {
			// session过期之后
			result = "-2";
		}else{
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			int userid = -1;
			String useremail = null;
			String usernick = null;
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userid")) {
					userid = Integer.parseInt(cookie.getValue());
				}
				if (cookie.getName().equals("useremail")) {
					useremail = cookie.getValue();
				}
				if (cookie.getName().equals("usernick")) {
					usernick =  cookie.getValue();
				}
			}
			
			String keymd5 = UserInforDao.selectUserKeyMd5ByUseridUseremail(userid, useremail);
			
			if(!MD5Util.makeSrcToMD5(key).equals(keymd5)){
				result = "-1";
			}else{
				List list = WebInforDao.selectPassAndAccByWebinforid(webinforid);
				Object object = list.get(0);
				Object[] o = (Object[]) object;
				if(o[0] == null || o[1] == null){
					result = "2";
				}else{
					String pberesult = (String) o[0];
					String salt = (String) o[1];
					System.out.println("pberesult = "+pberesult+", salt = "+salt);
					PBEUtil pbeUtil = new PBEUtil(key, Base64.decodeBase64(pberesult),  Base64.decodeBase64(salt));
					result = pbeUtil.PBEDecode();
				}
			}
		}
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
