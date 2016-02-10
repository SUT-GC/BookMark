package action.ajax.add;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.WebInforDao;

public class AddWebInforNum extends ActionSupport {
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
	public String execute() throws Exception {
		WebInforDao.updataWebinforNum(webinforid);
		inputStream = new ByteArrayInputStream("yes".getBytes("utf-8"));
		return SUCCESS;
	}
}
