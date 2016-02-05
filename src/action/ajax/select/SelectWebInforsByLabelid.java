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

public class SelectWebInforsByLabelid extends ActionSupport {
	private int type;
	private int labelid;
	private List list;
	private InputStream inputStream;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLabelid() {
		return labelid;
	}

	public void setLabelid(int labelid) {
		this.labelid = labelid;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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
	 * -2:session过期
	 * -1:没查询出结果
	 */
	public String execute() throws Exception {
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		if (httpSession.getAttribute("sessionoverdue") != null
				&& httpSession.getAttribute("sessionoverdue").equals("yes")) {
			// session过期之后
			result = "-2";
		}else{
			list = LabelDao.selectWebInforsByLableid(labelid, type);
			if(list.size() == 0){
				result = "-1";
			}else{
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = null;
				for(Object object :list){
					jsonObject = new JSONObject();
					Object[] o = (Object[]) object;
					jsonObject.put("webinforid", o[0]);
					jsonObject.put("webinforname", Base64Util.decodeToString((String) o[1]));
					jsonObject.put("webinforlink", Base64Util.decodeToString((String) o[2]));
					jsonObject.put("webinfornum", o[3]);
					jsonObject.put("webinforcreatetime", o[4]);
					jsonArray.put(jsonObject);
				}
				result = jsonArray.toString();
			}
		}
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
