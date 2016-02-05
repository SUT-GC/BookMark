package action.ajax.select;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.entity.Label;
import dao.bmdb.entity.WebInfor;
import dao.bmdb.operate.WebInforDao;
import encrypt.base64.Base64Util;

public class SelectWebInforById extends ActionSupport {
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
		String result = "0";
		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		if (httpSession.getAttribute("sessionoverdue") != null
				&& httpSession.getAttribute("sessionoverdue").equals("yes")) {
			// session过期之后
			result = "-2";
		}else{
			System.out.println("webinforid = "+webinforid);
			WebInfor webInfor = WebInforDao.selectWebinforByWebinforid(webinforid);
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("webname", Base64Util.decodeToString(webInfor.getName()));
			jsonObject.put("weblink", Base64Util.decodeToString(webInfor.getLink()));
			if(webInfor.getDescribe() == null || webInfor.getDescribe().length() == 0){
				jsonObject.put("webdescribe", "您没有填写相关描述");
			}else{
				jsonObject.put("webdescribe", Base64Util.decodeToString(webInfor.getDescribe()));
			}
			jsonArray.put(jsonObject);

			if(webInfor.getLabels() != null){
				Set<Label> labels = webInfor.getLabels();
				Iterator<Label> iterator = labels.iterator();
				JSONObject label = null;
				JSONArray labeljsonarray = new JSONArray();
				while(iterator.hasNext()){
					label = new JSONObject();
					Label l = iterator.next();
					label.put("labelid", l.getId());
					label.put("labelname", Base64Util.decodeToString(l.getName()));
					labeljsonarray.put(label);
				}
				jsonArray.put(labeljsonarray);

			}
			result = jsonArray.toString();
		}
		
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}
}
