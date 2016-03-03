package dao.bmdb.operate;

import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.bmdb.entity.Label;
import dao.bmdb.entity.WebInfor;
import dao.bmdb.tools.DBConnectUtil;
import encrypt.pbe.PBEUtil;

public class WebInforDao {

	/*
	 * 1:插入一个新的webInfor 
	 * 2:根据labelid与webinforid向webinfor的labels里添加label
	 * 3:根据webinforid查询出所有的label 
	 * 4:根据labelid删除webinfor_label里的记录
	 * 5:根据userid与useremail查询出所有相关的webinfor,并且按照点击数从大到小排列
	 * 6:根据userid与useremail查询出所有相关的webinfor,并且按照点创建时间从新到旧排列
	 * 7:根据webinforid删除webinfor 
	 * 8:根据webinforid查询出webinfor的信息
	 * 9:根据webinforid查询出账号密码 
	 * 10:更新webinfor 
	 * 11:更新webinfornum 自加1
	 * 12:根据webinforname模糊查询webinfor 13:根据userid与newkey，oldkey更新所有的口令
	 */

	/*
	 * 1：插入一个新的WenInfor
	 */
	public static int insertWebInfor(WebInfor webInfor) {
		int id = -1;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		id = (int) session.save(webInfor);

		transaction.commit();
		DBConnectUtil.closeSession(session);
		return id;
	}

	/*
	 * 2：根据webinforid与Label更新一个Webinfor
	 */
	public static void addLabelToWebInforBywebInforId(int labelid,
			int webInforId) {
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		Label label = (Label) session.get(Label.class, labelid);
		WebInfor webInfor = (WebInfor) session.get(WebInfor.class, webInforId);
		webInfor.getLabels().add(label);
		session.update(webInfor);

		transaction.commit();
		DBConnectUtil.closeSession(session);
	}

	/*
	 * 3:根据webinforid查询出所有的label
	 */
	public static Set<Label> selectLabelsByWebInforId(int webinforid) {
		Set<Label> set = null;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		WebInfor webInfor = (WebInfor) session.get(WebInfor.class, webinforid);
		set = webInfor.getLabels();

		transaction.commit();
		DBConnectUtil.closeSession(session);

		return set;
	}

	/*
	 * 4:根据webinforid和labelid删除webinfor_label里的记录
	 */
	public static void deleteWebinforlabelByLabelidAndWebinforid(
			int webinforid, int labelid) {
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		WebInfor webInfor = (WebInfor) session.get(WebInfor.class, webinforid);
		Label label = (Label) session.get(Label.class, labelid);
		webInfor.getLabels().remove(label);

		transaction.commit();
		DBConnectUtil.closeSession(session);
	}

	/*
	 * 5:根据userid与useremail查询出所有相关的webinfor,并且按照点击数从大到小排列
	 */
	public static List selectAllWebinforByUseridAndUseremailOrderbyNum(
			int userid, String useremail) {
		List list = null;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		list = session
				.createQuery(
						"select webinfor.id, webinfor.name, webinfor.link, webinfor.num, webinfor.createtime from WebInfor webinfor where webinfor.userid = :userid and webinfor.useremail = :useremail order by webinfor.num desc").setInteger("userid", userid).setString("useremail", useremail)
				.list();

		transaction.commit();
		DBConnectUtil.closeSession(session);
		return list;
	}

	/*
	 * 6:根据userid与useremail查询出所有相关的webinfor,并且按照点创建时间从新到旧排列
	 */
	public static List selectAllWebinforByUseridAndUseremailOrderbyCreatetime(
			int userid, String useremail) {
		List list = null;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		list = session
				.createQuery(
						"select webinfor.id, webinfor.name, webinfor.link, webinfor.num, webinfor.createtime from WebInfor webinfor where webinfor.userid = :userid and webinfor.useremail = :useremail order by webinfor.createtime desc").setInteger("userid", userid).setString("useremail", useremail)
				.list();

		transaction.commit();
		DBConnectUtil.closeSession(session);
		return list;
	}

	/*
	 * 7:根据webinforid删除webinfor
	 */
	public static void deleteWebinforByWebinforid(int webinforid) {
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		WebInfor infor = (WebInfor) session.get(WebInfor.class, webinforid);
		session.delete(infor);

		transaction.commit();
		DBConnectUtil.closeSession(session);
	}

	/*
	 * 8:根据webinforid查询出webinfor的信息
	 */
	public static WebInfor selectWebinforByWebinforid(int Webinforid) {
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		WebInfor webInfor = (WebInfor) session.get(WebInfor.class, Webinforid);

		transaction.commit();
		DBConnectUtil.closeSession(session);

		return webInfor;
	}

	/*
	 * 9:根据webinforid查询出账号密码
	 */
	public static List selectPassAndAccByWebinforid(int webinforid) {
		List list = null;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		list = session
				.createQuery(
						"select webinfor.result, webinfor.salt from WebInfor webinfor where webinfor.id = :webinforid")
				.setInteger("webinforid", webinforid).list();

		transaction.commit();
		DBConnectUtil.closeSession(session);

		return list;
	}

	/*
	 * 10:更新webinfor根据WebInfor
	 */
	public static void updataWebinfor(WebInfor webInfor, int webinforid) {
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		WebInfor infor = (WebInfor) session.get(WebInfor.class, webinforid);
		infor.setName(webInfor.getName());
		infor.setLink(webInfor.getLink());
		infor.setDescribe(webInfor.getDescribe());
		if (webInfor.getResult() != null && webInfor.getSalt() != null) {
			infor.setResult(webInfor.getResult());
			infor.setSalt(webInfor.getSalt());
		}
		session.update(infor);

		transaction.commit();
		DBConnectUtil.closeSession(session);
	}

	/*
	 * 11:更新webinfornum 自加1
	 */
	public static void updataWebinforNum(int webinforid) {
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		WebInfor webInfor = (WebInfor) session.get(WebInfor.class, webinforid);
		webInfor.setNum(webInfor.getNum() + 1);
		session.update(webInfor);

		transaction.commit();
		DBConnectUtil.closeSession(session);
	}

	/*
	 * 12:根据webinforname模糊查询webinfor
	 */
	public static List selectWebinforLikeWebinforname(String webinforname,
			int userid, int type) {
		List list = null;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		if (type == 1) {
			list = session
					.createQuery(
							"select webinfor.id, webinfor.name, webinfor.link, webinfor.num, webinfor.createtime from WebInfor webinfor where webinfor.name like '%"
									+ webinforname
									+ "%' and webinfor.userid = "
									+ userid
									+ " order by webinfor.createtime desc ")
					.list();
		} else {
			list = session
					.createQuery(
							"select webinfor.id, webinfor.name, webinfor.link, webinfor.num, webinfor.createtime from WebInfor webinfor where webinfor.name like '%"
									+ webinforname
									+ "%' and webinfor.userid = "
									+ userid
									+ " order by webinfor.num desc ").list();
		}

		transaction.commit();
		DBConnectUtil.closeSession(session);
		return list;
	}

	/*
	 * 13:根据userid与newkey，oldkey更新所有的口令
	 * 0:webinforDao出错
	 * 1:修改成功
	 * -1:修改修改记录为0
	 */
	public static int  updataWebinforResult(int userid, String newkey,
			String oldkey) {
		int resultint = 0;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		List<WebInfor> list = session
				.createQuery(
						"from WebInfor webinfor where webinfor.userid = :userid")
				.setInteger("userid", userid).list();

		System.out.println("list判断是否为null:" + list);
		
		int count = 1;
		for(WebInfor webInfor : list){
	
			try {
				System.out.println("webinfor:"+webInfor);
				if(webInfor.getResult() == null || webInfor.getSalt() == null){
					continue;
				}else{
					PBEUtil pbeUtil = new PBEUtil(oldkey, Base64.decodeBase64(webInfor.getResult()), Base64.decodeBase64(webInfor.getSalt()));
					String result = pbeUtil.PBEDecode();
					PBEUtil newpbeUtil = new PBEUtil(newkey,result);
					webInfor.setResult(Base64.encodeBase64String(newpbeUtil.PBEEncode()));
					webInfor.setSalt(Base64.encodeBase64String(newpbeUtil.getSalt()));
					count ++;
					if(count % 20 == 0){
						session.flush();
					}
					resultint = 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultint = -1;
			}
		}
		if(count == 1){
			resultint = -1;
		}
		transaction.commit();
		DBConnectUtil.closeSession(session);
		return resultint;
	}
}
