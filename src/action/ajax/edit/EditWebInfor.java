package action.ajax.edit;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import dao.bmdb.entity.WebInfor;
import dao.bmdb.operate.UserInforDao;
import dao.bmdb.operate.WebInforDao;
import dao.wbdb.operate.UserDao;
import encrypt.base64.Base64Util;
import encrypt.md5.MD5Util;
import encrypt.pbe.PBEUtil;

public class EditWebInfor extends ActionSupport {
	private int webinforid;
	private String webinforname;
	private String webinforlink;
	private String webinfordes;
	private String webinforacc;
	private String webinforpass;
	private String webinforkey;
	private InputStream inputStream;
	public int getWebinforid() {
		return webinforid;
	}
	public void setWebinforid(int webinforid) {
		this.webinforid = webinforid;
	}
	public String getWebinforname() {
		return webinforname;
	}
	public void setWebinforname(String webinforname) {
		this.webinforname = webinforname;
	}
	public String getWebinforlink() {
		return webinforlink;
	}
	public void setWebinforlink(String webinforlink) {
		this.webinforlink = webinforlink;
	}
	public String getWebinfordes() {
		return webinfordes;
	}
	public void setWebinfordes(String webinfordes) {
		this.webinfordes = webinfordes;
	}
	public String getWebinforacc() {
		return webinforacc;
	}
	public void setWebinforacc(String webinforacc) {
		this.webinforacc = webinforacc;
	}
	public String getWebinforpass() {
		return webinforpass;
	}
	public void setWebinforpass(String webinforpass) {
		this.webinforpass = webinforpass;
	}
	public String getWebinforkey() {
		return webinforkey;
	}
	public void setWebinforkey(String webinforkey) {
		this.webinforkey = webinforkey;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 * 0:数据库链接失败
	 * -1:口令错误
	 * -2:session过期
	 * 1:更新成功
	 */
	public String execute() throws Exception {
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		if(httpSession.getAttribute("sessionoverdue") != null && httpSession.getAttribute("sessionoverdue").equals("yes") ){
			result = "-2";
			httpSession.removeAttribute("sessionoverdue");
		}else{
			WebInfor webInfor = new WebInfor();
			webInfor.setName(Base64Util.encodeToString(webinforname));
			webInfor.setLink(Base64Util.encodeToString(webinforlink));
			if(webinfordes.length() != 0 ){
				webInfor.setDescribe(Base64Util.encodeToString(webinfordes));
			}
			int  userid = (int) httpSession.getAttribute("userid");
			String useremail = (String) httpSession.getAttribute("useremail");
			
			String keymd5 = UserInforDao.selectUserKeyMd5ByUseridUseremail(userid, useremail);
			
			String inputmd5 = MD5Util.makeSrcToMD5(webinforkey);
			System.out.println("webinfor.length ="+webinforkey.length());
			if(webinforkey.length() != 0){
				if(!keymd5.equals(inputmd5)){
					result = "-1";
				}else{
					String passandacc = webinforacc+"_BookMark_"+webinforpass;
					if(!passandacc.equals("")){
						PBEUtil pbeUtil = new PBEUtil(webinforkey, passandacc);
						webInfor.setSalt(Base64.encode(pbeUtil.getSalt()));
						webInfor.setResult(Base64.encode(pbeUtil.PBEEncode()));
					}
					WebInforDao.updataWebinfor(webInfor, webinforid);
					result = "1";
				}
			}else{
				WebInforDao.updataWebinfor(webInfor, webinforid);
				result = "1";
			}

		}
		
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
