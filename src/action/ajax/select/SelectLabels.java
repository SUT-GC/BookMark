package action.ajax.select;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.LabelDao;
import encrypt.base64.Base64Util;

public class SelectLabels extends ActionSupport {

	private String labelname;
	private InputStream InputStream;

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public InputStream getInputStream() {
		return InputStream;
	}

	public void setInputStream(InputStream inputStream) {
		InputStream = inputStream;
	}

	@Override
	/**
	 * 0：数据库无响应
	 * -1:查询结果不存在
	 * -2：session过期
	 */
	public String execute() throws Exception {
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		if (httpSession.getAttribute("sessionoverdue") != null
				&& httpSession.getAttribute("sessionoverdue").equals("yes")) {
			// session过期之后
			result = "-2";
		} else {
			// session没有过期
			int userid = (int) httpSession.getAttribute("userid");
			List list = LabelDao.selectLabelsByUseridAndLabelname(
					Base64Util.encodeToString(labelname), userid);
			if (list == null || list.size() == 0) {
				result = "-1";
			} else {
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = null;

				for (Object object : list) {
					Object[] o = (Object[]) object;
					jsonObject = new JSONObject();
					jsonObject.put("labelid", o[0]);
					jsonObject.put("labelname", Base64Util.decodeToString(""+o[1]));
					jsonArray.put(jsonObject);
				}
				result = jsonArray.toString();
			}
		}

		InputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
