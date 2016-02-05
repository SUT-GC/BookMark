package dao.bmdb.operate;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.bmdb.entity.Label;
import dao.bmdb.entity.WebInfor;
import dao.bmdb.tools.DBConnectUtil;

public class LabelDao {
	/*
	 * 1：插入新的label
	 * 2 : 根据labelid查询label
	 * 3：根据id查询出所关联的WebInfor
	 * 4：根据labelid删除label与其相关的webinfor_label记录
	 * 5 : 根据userid查询出所有的label
	 * 6：根据labelid更新labelname7：根据userid与labelname查询list
	 * 7：根据userid与labelname模糊查询list
	 */

	/*
	 * 1:插入新的label
	 */
	public static int insertLabel(Label label) {
		int labelkey = -1;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		labelkey = (int) session.save(label);

		transaction.commit();
		DBConnectUtil.closeSession(session);
		return labelkey;
	}

	/*
	 * 2:根据labelid查询Label
	 */
	public static Label selectLabelById(int labelid) {
		Label result = null;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		result = (Label) session.get(Label.class, labelid);

		transaction.commit();
		DBConnectUtil.closeSession(session);
		return result;
	}

	/*
	 * 3：根据id查询出所关联的WebInfor
	 * orderby == 1:按照时间排序
	 * orderby == 0:按照浏览次数排序
	 */
	public static List selectWebInforsByLableid(int lableid, int orderby) {
		List list = null;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		if(orderby == 1){
			list = session.createQuery("select wbs.id, wbs.name, wbs.link, wbs.num, wbs.createtime from Label label inner join label.webinfors wbs where label.id = :labelid order by wbs.createtime desc").setInteger("labelid", lableid).list();
		}else{
			list = session.createQuery("select wbs.id, wbs.name, wbs.link, wbs.num, wbs.createtime from Label label inner join label.webinfors wbs where label.id = :labelid order by wbs.num desc ,wbs.createtime desc").setInteger("labelid", lableid).list();
		}
		
		
		transaction.commit();
		DBConnectUtil.closeSession(session);

		return list;
	}

	/*
	 * 4：根据labelid删除label与其相关的webinfor_label记录
	 */
	public static void deleteLabelAndWebinforlabelByLabelId(int labelid) {
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		Label label = (Label) session.get(Label.class, labelid);
		session.delete(label);

		transaction.commit();
		DBConnectUtil.closeSession(session);
	}

	/*
	 * 5 : 根据userid查询出所有的label
	 */
	public static List selectAllLabelByUserid(int userid) {
		List list = null;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		list = session
				.createQuery(
						"select label.id, label.name from Label label where label.userid = :userid")
				.setInteger("userid", userid).list();

		transaction.commit();
		DBConnectUtil.closeSession(session);

		return list;
	}

	/*
	 * 6：根据labelid来更新labelname
	 */
	public static void updateLabelNameByLabelid(int labelid, String newlabelname) {
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		Label label = (Label) session.get(Label.class, labelid);
		label.setName(newlabelname);
		session.flush();

		transaction.commit();
		DBConnectUtil.closeSession(session);
	}

	/*
	 * 7：根据userid与labelname模糊查询list
	 */
	public static List selectLabelsByUseridAndLabelname(String labelname,
			int userid) {
		List list = null;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		list = session.createQuery(
				"select label.id, label.name from Label label where label.userid ='"
						+ userid + "' and label.name like '%" + labelname
						+ "%' ").list();
		
		transaction.commit();
		DBConnectUtil.closeSession(session);

		return list;
	}
}
