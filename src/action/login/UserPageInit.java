package action.login;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.entity.Label;
import dao.bmdb.entity.WebInfor;
import dao.bmdb.operate.LabelDao;
import encrypt.base64.Base64Util;

public class UserPageInit extends ActionSupport {
	private List labels = new ArrayList();
	private List webInforsByBits = new ArrayList();
	private List webInforsByTime = new ArrayList();

	public List getLabels() {
		return labels;
	}

	public void setLabels(List labels) {
		this.labels = labels;
	}

	public List getWebInforsByBits() {
		return webInforsByBits;
	}

	public void setWebInforsByBits(List webInforsByBits) {
		this.webInforsByBits = webInforsByBits;
	}

	public List getWebInforsByTime() {
		return webInforsByTime;
	}

	public void setWebInforsByTime(List webInforsByTime) {
		this.webInforsByTime = webInforsByTime;
	}

	@Override
	public String execute() throws Exception {
		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		
		/*
		 * 查询其cookie中是否保留了登陆信息
		 */
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("userid")){
				httpSession.setAttribute("userid", Integer.parseInt(cookie.getValue()));
				System.out.println("userid cookie = "+Integer.parseInt(cookie.getValue()));
			}
			if(cookie.getName().equals("usernick")){
				httpSession.setAttribute("usernick", cookie.getValue());
				System.out.println("usernick cookie = "+cookie.getValue());
			}
			if(cookie.getName().equals("useremail")){
				httpSession.setAttribute("useremail", cookie.getValue());
				System.out.println("useremail cookie = "+cookie.getValue());
			}
		}
		
		if (httpSession.getAttribute("userid") != null
				|| httpSession.getAttribute("usernick") != null
				|| httpSession.getAttribute("useremail") != null) {
			List list = LabelDao.selectAllLabelByUserid((Integer) httpSession
					.getAttribute("userid"));
			/*
			 * 将labelname解码
			 */
			for (int i = 0; i < list.size(); i++) {
				Object[] o = (Object[]) list.get(i);
				o[1] = Base64Util.decodeToString((String) o[1]);
				labels.add(o);
			}
		}
		return SUCCESS;
	}
}
