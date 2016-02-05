package action.ajax.delete;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.WebInforDao;

public class DeleteWebInfor extends ActionSupport {
	private int webinforid;
	private InputStream inputStream;
	public int getWebinforid() {
		return webinforid;
	}
	public void setWebinforid(int webinforid) {
		this.webinforid = webinforid;
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
	 * 1:删除成功
	 * -2:session过期
	 */
	public String execute() throws Exception {
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		if(httpSession.getAttribute("sessionoverdue") != null && httpSession.getAttribute("sessionoverdue").equals("yes") ){
			result = "-2";
			httpSession.removeAttribute("sessionoverdue");
		}else{
			WebInforDao.deleteWebinforByWebinforid(webinforid);
			result = "1";
		}
		
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
