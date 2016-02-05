package interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserInitPageInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("这里走了UserInitPageInterceptor");
		Map<String,Object> map = invocation.getInvocationContext().getSession();
		
		/*
		 * 检查userid是否为空
		 */
		int userid = 0;
		if(map.get("userid") != null){
			userid = (Integer) map.get("userid");
		}
		String useremail = (String) map.get("useremail");
		String usernike = (String) map.get("usernick");
		String isFirstToUse = (String)map.get("isFirstToUse");
		System.out.println("userid = "+userid+", useremail = "+useremail +", usernike = "+usernike +",isFirseToUser = "+isFirstToUse);
		
		/*
		 * 检查相关条件，如果有一个不符合，都会返回登陆界面
		 * userid， useremail， usernike 最高，优先检查
		 * isFirstToUse次之，后检查
		 */
		if(userid == 0 || useremail == null || usernike == null){
			return "welcome";
		}else{
			if(isFirstToUse == null || isFirstToUse.equals("no")){
				return "user";
			}else{
				if(isFirstToUse.equals("yes")){
					String result = invocation.invoke();
					System.out.println("这里在UserInitPageInterceptor即将允许, result = "+result);
					return result;
				}else{
					return "user";
				}
			}
		}
		
	}

}
