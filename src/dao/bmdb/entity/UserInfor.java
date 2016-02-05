package dao.bmdb.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "bm_userinfor")
public class UserInfor implements Serializable {

	@Id
	@Column(name = "user_id")
	private int userid;
	@Column(name = "user_email")
	private String useremail;
	@Column(name = "user_nike")
	private String usernike;
	@Column(name = "user_keymd5")
	private String keymd5;

	@ElementCollection(targetClass = LoginInfor.class, fetch=FetchType.EAGER)
	@CollectionTable(name = "bm_logininfor", joinColumns = @JoinColumn(name = "userid", nullable = false))
	@OrderColumn(name = "logininfor_order")
	private List<LoginInfor> logininfors = new ArrayList<LoginInfor>();

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

	public String getUsernike() {
		return usernike;
	}

	public void setUsernike(String usernike) {
		this.usernike = usernike;
	}

	public String getKeymd5() {
		return keymd5;
	}

	public void setKeymd5(String keymd5) {
		this.keymd5 = keymd5;
	}

	public List<LoginInfor> getLogininfors() {
		return logininfors;
	}

	public void setLogininfors(List<LoginInfor> logininfors) {
		this.logininfors = logininfors;
	}

	public UserInfor() {
		super();
	}

	public UserInfor(int userid, String useremail, String usernike,
			String keymd5) {
		super();
		this.userid = userid;
		this.useremail = useremail;
		this.usernike = usernike;
		this.keymd5 = keymd5;
	}

	@Override
	public String toString() {
		return "UserInfor [userid=" + userid + ", useremail=" + useremail
				+ ", usernike=" + usernike + ", keymd5=" + keymd5 + "]";
	}

	
}
