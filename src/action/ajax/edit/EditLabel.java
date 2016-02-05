package action.ajax.edit;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.LabelDao;
import encrypt.base64.Base64Util;

public class EditLabel extends ActionSupport {
	private int labelid;
	private String newlabelname;
	private InputStream inputStream;

	public int getLabelid() {
		return labelid;
	}

	public void setLabelid(int labelid) {
		this.labelid = labelid;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getNewlabelname() {
		return newlabelname;
	}

	public void setNewlabelname(String newlabelname) {
		this.newlabelname = newlabelname;
	}

	@Override
	/**
	 * 1：更新成功
	 * 0：更新失败
	 * -1: 数据库没相应
	 * -2: session过期
	 */
	public String execute() throws Exception {
		String result = "0";
		
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		if(httpSession.getAttribute("sessionoverdue") != null && httpSession.getAttribute("sessionoverdue").equals("yes") ){
			result = "-2";
			httpSession.removeAttribute("sessionoverdue");
		}else{
			System.out.println("newlabelname="+newlabelname);
			try {
				LabelDao.updateLabelNameByLabelid(labelid, Base64Util.encodeToString(newlabelname));
				result = "1";
			} catch (Exception e) {
				result = "-1";
				e.printStackTrace();
			}
		}
		
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
