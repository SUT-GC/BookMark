package dao.bmdb.test.dao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import dao.bmdb.entity.Label;
import dao.bmdb.entity.UserInfor;
import dao.bmdb.entity.WebInfor;
import dao.bmdb.operate.UserInforDao;
import dao.bmdb.operate.WebInforDao;
import encrypt.base64.Base64Util;
import encrypt.md5.MD5Util;

public class TestWebInforDao {
	public static void main(String[] args) {
		/*
		 * 测试根据webinforid与labelid向webinfor里插入一个label
		 */
//		WebInforDao.addLabelToWebInforBywebInforId(2,6);
		
		/*
		 * 测试根据webinforid查询出labels
		 */
//		Set<Label> set = WebInforDao.selectLabelsByWebInforId(6);
//		Iterator<Label>iterator = set.iterator();
//		
//		while(iterator.hasNext()){
//			System.out.println(iterator.next());
//		}
		
		/*
		 * 测试根据webinforid和labelid删除webinfor_label里的记录
		 */
		
//		WebInforDao.deleteWebinforlabelByLabelidAndWebinforid(6, 2);
		/*
		 * 根据userid，useremail查询webinfor并且按照num从大到小排列
		 */
//		List<WebInfor> list = WebInforDao.selectAllWebinforByUseridAndUseremail(1, "w");
//		for(WebInfor webInfor : list){
//			System.out.println(webInfor);
//		}
		/*
		 * 根据userid，useremail查询webinfor并且按照create从新到旧排列
		 */
//		List list = WebInforDao.selectAllWebinforByUseridAndUseremailOrderbyCreatetime(1, "gouchao2008@qq.com");
//		for(Object object : list){
//			Object[] o = (Object[]) object;
//			System.out.println(Arrays.toString(o));
//		}
		/*
		 * 根据webinforid删除webinfor
		 */
//		WebInforDao.deleteWebinforByWebinforid(56);
		
		/*
		 * 根据webinforid查询webinfor
		 */
//		WebInfor webInfor = WebInforDao.selectWebinforByWebinforid(68);
//		Set<Label> set = webInfor.getLabels();
//		System.out.println(set);
//		Iterator<Label> iterator = set.iterator();
//		System.out.println(webInfor);
//		System.out.println(Base64Util.decodeToString(webInfor.getDescribe()));
		
		/*
		 * 根据webinforid查询出result
		 */
//		List list = WebInforDao.selectPassAndAccByWebinforid(69);
//		Object object = list.get(0);
//		System.out.println(object);
//		Object[] o = (Object[]) object;
//		System.out.println(o[0]+","+o[1]);
		/*
		 * 更新webinfor
		 */
//		WebInfor webInfor = new WebInfor();
//		webInfor.setName("gc");
//		webInfor.setLink("gc");
//		webInfor.setDescribe("gc");
//		webInfor.setResult("ds");
//		webInfor.setSalt("ds");
//		
//		WebInforDao.updataWebinfor(webInfor, 79);
		
		/*
		 * 根据webinforname模糊查询webinfor
		 */
//		List list = WebInforDao.selectWebinforLikeWebinforname("Z",1);
//		Object object = list.get(0);
//		Object[] o = (Object[]) object;
//		System.out.println(o[0]);
		/*
		 * 根据userid，newkey，oldkey更新webinfor的加密内容
		 */
//		System.out.println(UserInforDao.selectUserKeyMd5ByUseridUseremail(1, "gouchao2008@qq.com"));
		UserInforDao.updateKey(1,MD5Util.makeSrcToMD5("135"));
//		System.out.println(MD5Util.makeSrcToMD5("456"));
//		System.out.println(WebInforDao.updataWebinforResult(1, "135", "246"));
	}
}
