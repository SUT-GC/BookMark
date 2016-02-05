package dao.bmdb.test.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import action.login.UserLogin;
import dao.bmdb.entity.LoginInfor;
import dao.bmdb.entity.UserInfor;
import dao.bmdb.operate.UserInforDao;

public class TestUserInforDao {

	public static void main(String[] args) {
		/*
		 * 测试插入新的UserInfor
		 */
//		UserInfor userInfor = new UserInfor(1, "gouchao2008@qq.com", "gc", "12334");
//		UserInforDao.insertUserInforWithoutLoginInfor(userInfor);
//		
		/*
		 * 测试根据userid， useremail，usernike更新UserLogin
		 */
//		Date now = new Date();
//		
//		LoginInfor loginInfor = new LoginInfor(new Timestamp(now.getTime()));
//		
//		UserInforDao.insertLoginInforByUseridUseremailUsernike(1, "gouchao2008@qq.com", "gc", loginInfor);
		
		/*
		 * 测试selectThisUserIsFirstToUseBookMark
		 */
//		System.out.println(UserInforDao.selectThisUserIsFirstToUseBookMark(2, "gc@qq.com", "bmtest1"));
		
		/*
		 * 测试根据userid与useremail查询keymd5
		 */
		System.out.println(UserInforDao.selectUserKeyMd5ByUseridUseremail(1, "gouchao2008@qq.com"));
	}

}
