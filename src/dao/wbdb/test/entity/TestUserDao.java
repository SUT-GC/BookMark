package dao.wbdb.test.entity;

import java.util.List;

import dao.wbdb.entity.User;
import dao.wbdb.operate.UserDao;

public class TestUserDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 测试selectAllUser
		 */
//		List<User> list = UserDao.selectAllUser();
//		for(User u:list){
//			System.out.println(u);
//		}
		
		/*
		 * 测试：selectUserpassByUseremail
		 */
		List<User> list = UserDao.selectUserByUseremail("gouchaso2008@qq.com");
		System.out.println("list.size = "+list.size());
		for(User u : list){
		System.out.println(u);
		}
	}

}
