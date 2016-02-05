package action.ajax.delete;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.LabelDao;
import dao.bmdb.operate.WebInforDao;

public class DelOrAddLabelAndWebinfor extends ActionSupport {
	private int webinforid;
	private int labelid;
	private int type;
	private InputStream inputStream;
	public int getWebinforid() {
		return webinforid;
	}
	public void setWebinforid(int webinforid) {
		this.webinforid = webinforid;
	}
	public int getLabelid() {
		return labelid;
	}
	public void setLabelid(int labelid) {
		this.labelid = labelid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	 * 0:链接数据库失败
	 * 1:操作成功
	 * -2:session过期
	 */
	public String execute() throws Exception {
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		if (httpSession.getAttribute("sessionoverdue") != null
				&& httpSession.getAttribute("sessionoverdue").equals("yes")) {
			// session过期之后
			result = "-2";
		} else{
			if(type == 1){
				WebInforDao.addLabelToWebInforBywebInforId(labelid, webinforid);
				result = "1";
			}else{
				WebInforDao.deleteWebinforlabelByLabelidAndWebinforid(webinforid, labelid);
				result = "1";
			}
		}
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
