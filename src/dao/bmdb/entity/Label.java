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

@Entity
@Table(name = "bm_label")
public class Label implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "label_id")
	private int id;
	@Column(name = "label_userid")
	private int  userid;
	@Column(name = "label_name")
	private String name;
	@Column(name = "label_createtime")
	private Timestamp createtime;
	@ManyToMany(targetEntity = WebInfor.class, fetch=FetchType.EAGER)
	@JoinTable(name = "webinfor_label", joinColumns = @JoinColumn(name = "label_id", referencedColumnName = "label_id"), inverseJoinColumns = @JoinColumn(name = "webinfor_id", referencedColumnName = "webinfor_id"))
	private Set<WebInfor> webinfors = new HashSet<WebInfor>();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Set<WebInfor> getWebinfors() {
		return webinfors;
	}
	public void setWebinfors(Set<WebInfor> webinfors) {
		this.webinfors = webinfors;
	}
	public Label() {
		super();
	}
	@Override
	public String toString() {
		return "Label [id=" + id + ", userid=" + userid + ", name=" + name
				+ ", createtime=" + createtime 
				+ "]";
	}
	
}
