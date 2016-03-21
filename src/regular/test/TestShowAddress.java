package regular.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.bmdb.entity.LoginInfor;
import dao.bmdb.tools.DBConnectUtil;
import select.ip.ShowAddress;

public class TestShowAddress {

	public static void main(String[] args) {
//		ShowAddress showAddress = new ShowAddress("bcf69b3f618d1ca364def3583f6abaaf");
		List<LoginInfor>list = null;
		Session session = DBConnectUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		

		list = session.createQuery("from UserInfor").list();
		
		transaction.commit();
		session.close();
		
		System.out.println(list.size());
		
	}

}
