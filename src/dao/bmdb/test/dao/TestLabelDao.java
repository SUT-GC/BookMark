package dao.bmdb.test.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import dao.bmdb.entity.Label;
import dao.bmdb.entity.WebInfor;
import dao.bmdb.operate.LabelDao;

public class TestLabelDao {

	public static void main(String[] args) {
		/*
		 * 测试根据label查询出所有相关的webinfor
		 */
//		Set<WebInfor> set = LabelDao.selectWebInforsByLableid(1);
//		Iterator<WebInfor>iterator = set.iterator();
//		
//		while(iterator.hasNext()){
//			System.out.println(iterator.next());
//		}
		
		/*
		 * 测试插入一个新的label
		 */
//		Label label = new Label();
//		label.setName("sss");
//		label.setUserid(1);
//		label.setCreatetime(new Timestamp(new Date().getTime()));
//		
//		LabelDao.insertLabel(label);
		
		/*
		 * 测试删除label根据labelid
		 */
//		LabelDao.deleteLabelAndWebinforlabelByLabelId(1);
		
		/*
		 * 测试根据userid查询出所有的label
		 */
//		List<Label> list = null;
//		list = LabelDao.selectAllLabelByUserid(1);
//		System.out.println(list.size());
		
		/*
		 * 测试根据labelid与newlabelname更新Label
		 */
//		LabelDao.updateLabelNameByLabelid(56, "new");
		
		/*
		 * 测试根据userid与labelname查询labels
		 */
		List list = LabelDao.selectWebInforsByLableid(85,0);
		for(Object object: list){
			Object[] o  = (Object[]) object;
			System.out.println(o[0]+","+o[1]+","+o[2]);
		}
	}

}
