package action.show;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.entity.LoginInfor;
import dao.bmdb.operate.UserInforDao;

public class ShowLoginInfor extends ActionSupport {
	private List<LoginInfor> list = null;
	private int userid;
	private int size;
	private String useremail;
	private String usernike;

	public List<LoginInfor> getList() {
		return list;
	}

	public void setList(List<LoginInfor> list) {
		this.list = list;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getUsernike() {
		return usernike;
	}

	public void setUsernike(String usernike) {
		this.usernike = usernike;
	}

	@Override
	public String execute() throws Exception {
		userid = -1;
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		for(Cookie cookie: cookies){
			if(cookie.getName().equals("userid")){
				userid = Integer.parseInt(cookie.getValue());
			}
			if(cookie.getName().equals("useremail")){
				useremail = cookie.getValue();
			}
			if(cookie.getName().equals("usernick")){
				usernike = cookie.getValue();
			}
		}
		
		if(userid == -1 || useremail == null || usernike == null){
			return "session";
		}else{
			list = UserInforDao.selectLoginInfor(userid);
			size = list.size();
		}
		
		return SUCCESS;
	}
}
