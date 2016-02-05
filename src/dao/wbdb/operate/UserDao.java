package dao.wbdb.operate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.wbdb.entity.User;
import dao.wbdb.tools.DBConnectUtil;


public class UserDao {
	
	/*
	 * 函数目录
	 * 1：查询出所有的用户信息， 函数名：selectAllUser
	 * 2：根据用户邮箱查询用户信息，函数名：selectUserpassByUseremail
	 */
	
	
	/*
	 * 1
	 *查询出所有的用户信息
	 */
	public static List<User> selectAllUser(){
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
	public static List<User> selectUserByUseremail(String useremail){
		List<User> list = null;
		
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		
		list = session.createQuery("select u from User u where u.userEmail = :useremail").setString("useremail", useremail).list();
		
		transaction.commit();
		DBConnectUtil.closeSession(session);
		
		return list;
	}
}
