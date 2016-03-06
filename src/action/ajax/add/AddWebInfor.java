package action.ajax.add;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.entity.WebInfor;
import dao.bmdb.operate.UserInforDao;
import dao.bmdb.operate.WebInforDao;
import encrypt.base64.Base64Util;
import encrypt.md5.MD5Util;
import encrypt.pbe.PBEUtil;

public class AddWebInfor extends ActionSupport {
	private String input_webname;
	private String input_weblink;
	private String webdescribe;
	private String[] labels;
	private String input_webaccount;
	private String input_webpassword;
	private InputStream inputStream;
	private String input_password;

	public String getInput_password() {
		return input_password;
	}

	public void setInput_password(String input_password) {
		this.input_password = input_password;
	}

	public String getInput_webname() {
		return input_webname;
	}

	public void setInput_webname(String input_webname) {
		this.input_webname = input_webname;
	}

	public String getInput_weblink() {
		return input_weblink;
	}

	public void setInput_weblink(String input_weblink) {
		this.input_weblink = input_weblink;
	}

	public String getWebdescribe() {
		return webdescribe;
	}

	public void setWebdescribe(String webdescribe) {
		this.webdescribe = webdescribe;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public String getInput_webaccount() {
		return input_webaccount;
	}

	public void setInput_webaccount(String input_webaccount) {
		this.input_webaccount = input_webaccount;
	}

	public String getInput_webpassword() {
		return input_webpassword;
	}

	public void setInput_webpassword(String input_webpassword) {
		this.input_webpassword = input_webpassword;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute() 
	 * 0:数据库未链接 
	 * -1:口令错误
	 * 1:添加成功 
	 * -2:必填项目不能为空
	 * -3:session过期
	 */
	@Override
	public String execute() throws Exception {

		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		if(httpSession.getAttribute("sessionoverdue") != null && httpSession.getAttribute("sessionoverdue").equals("yes") ){
			result = "-3";
			httpSession.removeAttribute("sessionoverdue");
			System.out.println("AddLabel 走了1");
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
			String userkeymd5 = UserInforDao.selectUserKeyMd5ByUseridUseremail(
					userid, useremail);


			if (input_webname == null || input_weblink == null
					|| input_webname.equals("") || input_weblink.equals("")) {
				result = "-2";
			} else {
				WebInfor webInfor = new WebInfor();
				webInfor.setName(Base64Util.encodeToString(input_webname));
				if(!webdescribe.equals("")){
					webInfor.setDescribe(Base64Util.encodeToString(webdescribe));
				}
				//如果url不是以http://或者https://开头的，则添加http：//
				if(!input_weblink.startsWith("http://") && !input_weblink.startsWith("https://")){
					input_weblink = "http://"+input_weblink;
				}
				webInfor.setCreatetime(new Timestamp(new Date().getTime()));
				webInfor.setLink(Base64Util.encodeToString(input_weblink));
				webInfor.setUserid(userid);
				webInfor.setUseremail(useremail);
				/*
				 * 只要账号密码有一个不等于空并且口令不等于空即可成立
				 */
				System.out.println(((input_webaccount.length() != 0) + ", "+(input_webpassword.length() != 0)+","+  (input_password.length() != 0)));
				if (((input_webaccount.length() != 0)
						|| (input_webpassword.length() != 0)) && input_password.length() != 0) {
					System.out.println("这里已经判断成功了");
					if (!MD5Util.makeSrcToMD5(input_password).equals(userkeymd5)) {
						result = "-1";
					} else{
						/*
						 * 将账号密码重新拼接成 'account_BookMark_password'形式
						 */
						String newstring = input_webaccount+"_BookMark_"+input_webpassword;
						PBEUtil pbeUtiEncodel = new PBEUtil(input_password, newstring);
						byte[] encoderesult = pbeUtiEncodel.PBEEncode();
						
						webInfor.setSalt(Base64.encodeBase64String(pbeUtiEncodel.getSalt()));
						webInfor.setResult(Base64.encodeBase64String(encoderesult));
					}
				}
				/*
				 * 如果result ！= -1也就是口令正确，则添加，若口令不正确，则先不添加
				 */
				int webinforid;
				if(!result.equals("-1")){
					webinforid = WebInforDao.insertWebInfor(webInfor);
					if(labels != null && labels.length != 0){
						for(String labelid: labels){
							WebInforDao.addLabelToWebInforBywebInforId(Integer.parseInt(labelid), webinforid);
						}
					}
					result = ""+webinforid;
				}
			}

		}

	
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		
		return SUCCESS;
	}

}
