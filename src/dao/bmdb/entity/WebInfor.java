package dao.bmdb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name = "bm_webinfor")
@Entity
public class WebInfor implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "webinfor_id")
	private int id;
	@Column(name = "webinfor_userid")
	private int userid;
	@Column(name = "webinfor_useremail")
	private String useremail;
	@Column(name = "webinfor_name")
	private String name;
	@Column(name = "webinfor_link")
	private String link;
	@Column(name = "webinfor_num")
	private int num;
	@Column(name = "webinfor_describe")
	private String describe;
	@Column(name = "webinfor_createtime")
	private Timestamp createtime;
	@Column(name = "webinfor_salt")
	private String salt;
	@Column(name = "webinfor_result")
	private String result;
	@ManyToMany(targetEntity = Label.class, fetch = FetchType.EAGER)
	@JoinTable(name = "webinfor_label", joinColumns = @JoinColumn(name = "webinfor_id", referencedColumnName = "webinfor_id"), inverseJoinColumns = @JoinColumn(name = "label_id", referencedColumnName = "label_id"))
	private Set<Label> labels = new HashSet<Label>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Set<Label> getLabels() {
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public WebInfor() {
		super();
	}

	@Override
	public String toString() {
		return "WebInfor [id=" + id + ", userid=" + userid + ", useremail="
				+ useremail + ", name=" + name + ", link=" + link + ", num="
				+ num + ", createtime=" + createtime + ", describe=" + describe
				+ ", salt=" + salt + ", result=" + result + "]";
	}

}
