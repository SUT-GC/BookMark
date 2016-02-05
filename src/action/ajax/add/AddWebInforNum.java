package action.ajax.add;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.WebInforDao;

public class AddWebInforNum extends ActionSupport {
	private int webinforid;

	public int getWebinforid() {
		return webinforid;
	}

	public void setWebinforid(int webinforid) {
		this.webinforid = webinforid;
	}

	@Override
	public String execute() throws Exception {
		WebInforDao.updataWebinforNum(webinforid);
		return SUCCESS;
	}
}
