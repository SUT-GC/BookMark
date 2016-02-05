package dao.bmdb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import org.hibernate.annotations.Parent;

@Embeddable
@Table(name = "bm_logininfor")
public class LoginInfor implements Serializable{

	@Column(name = "login_time")
	private Timestamp logintime;
	@Column(name="login_ip")
	private String ipAddress;
	@Column(name = "login_hostname")
	private String hostname;
	@Parent
	private UserInfor userInfor;
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}


	public UserInfor getUserInfor() {
		return userInfor;
	}

	public void setUserInfor(UserInfor userInfor) {
		this.userInfor = userInfor;
	}

	public LoginInfor() {
		super();
	}

	public LoginInfor(Timestamp logintime) {
		super();
		this.logintime = logintime;
	}


	public Timestamp getLogintime() {
		return logintime;
	}

	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}

	@Override
	public String toString() {
		return "LoginInfor [logintime=" + logintime + ", ipAddress="
				+ ipAddress + ", hostname=" + hostname + "]";
	}


	
	
	

}
