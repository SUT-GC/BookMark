package dao.bmdb.operate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import action.login.UserLogin;
import dao.bmdb.entity.LoginInfor;
import dao.bmdb.entity.UserInfor;
import dao.bmdb.tools.DBConnectUtil;

public class UserInforDao {

	/*
	 * 1:插入一个新的userinfor
	 * 2:根据userid，useremail，usernike来查询出一个userinfo人并且向里添加logininfor
	 * 3:根据userid，useremail，usernike来判断该用户是否是第一次使用BookMark 
	 * 4:根据userid，useremail.查询口令的MD5
	 */

	/*
	 * 1:插入一个新的userinfor
	 */
	public static void insertUserInforWithoutLoginInfor(UserInfor userinfor) {

		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		session.save(userinfor);

		transaction.commit();
		DBConnectUtil.closeSession(session);

	}

	/*
	 * 2:根据userid，useremail，usernike来查询出一个userinfo人并且向里添加logininfor
	 */
	public static void insertLoginInforByUseridUseremailUsernike(int userid,
			String useremail, String usernike, LoginInfor loginInfor) {
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		List<UserInfor> list = session
				.createQuery(
						"select userinfor from UserInfor userinfor where userinfor.userid = :userid and userinfor.useremail =:useremail and userinfor.usernike = :usernike")
				.setInteger("userid", userid).setString("useremail", useremail)
				.setString("usernike", usernike).list();

		UserInfor userinfor = list.get(0);
		userinfor.getLogininfors().add(loginInfor);

		session.update(userinfor);

		transaction.commit();
		DBConnectUtil.closeSession(session);

	}

	/*
	 * 3:根据userid，useremail，usernike来判断该用户是否是第一次使用BookMark true:是第一次访问
	 * false:不是第一次访问
	 */
	public static boolean selectThisUserIsFirstToUseBookMark(int userid,
			String useremail, String usernike) {
		boolean result = false;

		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		List<UserInfor> list = session
				.createQuery(
						"select userinfor from UserInfor userinfor where userid = :userid and useremail = :useremail and usernike = :usernike")
				.setInteger("userid", userid).setString("useremail", useremail)
				.setString("usernike", usernike).list();

		transaction.commit();
		DBConnectUtil.closeSession(session);

		if (list.size() == 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/*
	 * 4:根据userid，useremail. 查询口令的MD5
	 */
	public static String selectUserKeyMd5ByUseridUseremail(int userid,
			String useremail) {
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();

		List list = session
				.createQuery(
						"select userinfor.keymd5 from UserInfor userinfor where userinfor.userid = :userid and userinfor.useremail = :useremail")
				.setInteger("userid", userid).setString("useremail", useremail)
				.list();
		

		transaction.commit();
		DBConnectUtil.closeSession(session);
		
		return (String) list.get(0);
	}
}
