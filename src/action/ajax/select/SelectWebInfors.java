package action.ajax.select;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.operate.WebInforDao;
import encrypt.base64.Base64Util;

public class SelectWebInfors extends ActionSupport {
	private List list;
	private InputStream inputStream;
	/*
	 * type = 0:按浏览次数
	 * type = 1:按创建时间
	 */
	private int type;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport
	 * #execute() 
	 * 0:数据库链接失败
	 * -1:没有查询到结果
	 * -2:session过期
	 */

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
			int userid  = (int) httpSession.getAttribute("userid");
			String useremail = (String) httpSession.getAttribute("useremail");
			System.out.println("type = "+type);
			if(type == 0){
				list = WebInforDao.selectAllWebinforByUseridAndUseremailOrderbyNum(userid, useremail);
			}else{
				list = WebInforDao.selectAllWebinforByUseridAndUseremailOrderbyCreatetime(userid, useremail);
			}
			
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
