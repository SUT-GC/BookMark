package action.ajax.delete;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.LabelDao;

public class DeleteLabel extends ActionSupport {
	private int labelid;
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

	@Override
	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 * 1:删除成功
	 * 0:数据库链接失败
	 * -1:删除失败
	 * -2:session过期
	 */
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		if(httpSession.getAttribute("sessionoverdue") != null && httpSession.getAttribute("sessionoverdue").equals("yes") ){
			result = "-2";
			httpSession.removeAttribute("sessionoverdue");
		}else{
			try {
				LabelDao.deleteLabelAndWebinforlabelByLabelId(labelid);
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
