package dao.wbdb.operate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.wbdb.entity.User;
import dao.wbdb.tools.DBConnectUtil;

public class UserDao {

	/*
	 * 函数目录 1：查询出所有的用户信息， 函数名：selectAllUser
	 * 2：根据用户邮箱查询用户信息，函数名：selectUserpassByUseremail
	 */

	/*
	 * 1查询出所有的用户信息
	 */
	public static List<User> selectAllUser() {
		List<User> list = null;

		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		list = session.createQuery("from User").list();

		transaction.commit();
		DBConnectUtil.closeSession(session);

		return list;
	}

	/*
	 * 2:根据用户邮箱，查询用户信息
	 */
	public static List<User> selectUserByUseremail(String useremail) {
		List<User> list = null;

		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		list = session
				.createQuery(
						"select u from User u where u.userEmail = :useremail")
				.setString("useremail", useremail).list();

		transaction.commit();
		DBConnectUtil.closeSession(session);

		return list;
	}

	/*
	 * 3：根据userid，useremail，usernike查询passMD5
	 */
	public static String selectPassMd5(int userid, String useremail,
			String usernick) {
		String passMd5 = "";
		List list = null;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		list = session
				.createQuery(
						"select u.userPass from User u where u.userId = :userid and u.userEmail = :useremail and u.userNick = :usernick")
				.setInteger("userid", userid).setString("useremail", useremail)
				.setString("usernick", usernick).list();
		passMd5 = (String) list.get(0);
		
		transaction.commit();
		DBConnectUtil.closeSession(session);
		return passMd5;
	}
	/*
	 * 根据userid，和newpass更新密码
	 */
	public static void updatePassword(int userid, String newpass){
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		
		User user = (User) session.get(User.class, userid);
		user.setUserPass(newpass);
		session.update(user);
		
		transaction.commit();
		DBConnectUtil.closeSession(session);
	}
}
