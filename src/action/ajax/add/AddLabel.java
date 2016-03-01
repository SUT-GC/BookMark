package action.ajax.add;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import smalltools.ReplaceSpecialCharacter;

import com.opensymphony.xwork2.ActionSupport;

import dao.bmdb.entity.Label;
import dao.bmdb.operate.LabelDao;
import encrypt.base64.Base64Util;

public class AddLabel extends ActionSupport {
	private String newlabelname;
	private int userid;
	private Timestamp createtime;
	private InputStream inputStream;

	public String getNewlabelname() {
		return newlabelname;
	}

	public void setNewlabelname(String newlabelname) {
		this.newlabelname = newlabelname;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
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
	 * inputStream 值
	 * -1:newlabelname为空
	 * 1:成功
	 * -2:session过期
	 */
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Label newLabel = new Label();
		String result = "";

		HttpSession httpSession = ServletActionContext.getRequest()
				.getSession();
		System.out.println("AddLabel 里的sessionoverdue = "+httpSession.getAttribute("sessionoverdue")+", userid = "+httpSession.getAttribute("userid"));
		if(httpSession.getAttribute("sessionoverdue") != null && httpSession.getAttribute("sessionoverdue").equals("yes") ){
			result = "-2";
			httpSession.removeAttribute("sessionoverdue");
			System.out.println("AddLabel 走了1");
		}else if (newlabelname.length() == 0) {
			System.out.println("AddLabel 走了2");
			result = "-1";
		}else{
			
			/*
			 * 将newlabelname里的"<"替换掉之后base64编码
			 */
			newLabel.setName(Base64Util.encodeToString((newlabelname)));
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			int userid = -1;
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userid")) {
					userid = Integer.parseInt(cookie.getValue());
				}
			}
			System.out.println(userid);
			newLabel.setUserid(userid);
			System.out.println("这里走了3");
			newLabel.setCreatetime(new Timestamp(new Date().getTime()));
			System.out.println(newLabel);
			result = ""+LabelDao.insertLabel(newLabel);
			System.out.println("AddLabel里插入新的label之后的result = "+result);
		}

		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));

		return SUCCESS;
	}
}
