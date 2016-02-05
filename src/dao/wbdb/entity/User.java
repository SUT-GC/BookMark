package dao.wbdb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wb_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	@Column(name = "user_email")
	private String userEmail;
	@Column(name = "user_pass")
	private String userPass;
	@Column(name = "user_nick")
	private String userNick;
	@Column(name = "user_imagepath")
	private String userImagepath;
	@Column(name = "user_isadmin")
	private String userIsAdmin;

	public User(String userEmail, String userPass, String userNick,
			String userImagepath, String userIsAdmin) {
		super();
		this.userEmail = userEmail;
		this.userPass = userPass;
		this.userNick = userNick;
		this.userImagepath = userImagepath;
		this.userIsAdmin = userIsAdmin;
	}

	public User() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserImagepath() {
		return userImagepath;
	}

	public void setUserImagepath(String userImagepath) {
		this.userImagepath = userImagepath;
	}

	public String getUserIsAdmin() {
		return userIsAdmin;
	}

	public void setUserIsAdmin(String userIsAdmin) {
		this.userIsAdmin = userIsAdmin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userEmail=" + userEmail
				+ ", userPass=" + userPass + ", userNick=" + userNick
				+ ", userImagepath=" + userImagepath + ", userIsAdmin="
				+ userIsAdmin + "]";
	}

}
