package action.ajax.select;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import dao.bmdb.operate.WebInforDao;
import encrypt.base64.Base64Util;

public class SelectWebInforLikeName extends ActionSupport {
	private String webinforname;
	private int type;
	private InputStream inputStream;

	public String getWebinforname() {
		return webinforname;
	}

	public void setWebinforname(String webinforname) {
		this.webinforname = webinforname;
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
	 *0:数据库链接失败
	 *-2:session过期
	 *-1:没有查询到 
	 */
	public String execute() throws Exception {
		System.out.println("这里走了selectwebinforname");
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		if (httpSession.getAttribute("sessionoverdue") != null
				&& httpSession.getAttribute("sessionoverdue").equals("yes")) {
			// session过期之后
			result = "-2";
		}else{
			int userid = (int) httpSession.getAttribute("userid");
			webinforname = Base64Util.encodeToString(webinforname);
			List list = WebInforDao.selectWebinforLikeWebinforname(webinforname, userid, type);
			if(list.size() == 0){
				result = "-1";
			}else{
				//select webinfor.id, webinfor.name, webinfor.link, webinfor.num, webinfor.createtime
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = null;
				for(Object object : list){
					Object[] o = (Object[]) object;
					jsonObject = new JSONObject();
					jsonObject.put("webinforid", o[0]);
					jsonObject.put("webinforname", Base64Util.decodeToString((String)o[1]));
					jsonObject.put("webinforlink", Base64Util.decodeToString((String)o[2]));
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
