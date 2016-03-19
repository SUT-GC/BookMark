package interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import encrypt.base64.Base64Util;

public class UserPageInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("这里走了UserPageInterceptor");
		
		/*
		 * 在cookies里查询cookie
		 */
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		int count = 0;
		if(cookies != null && cookies.length != 0){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("userid")){
					httpSession.setAttribute("userid", cookie.getValue());
					count ++;
				}
				if(cookie.getName().equals("useremail")){
					httpSession.setAttribute("useremail", cookie.getValue());
					count ++;
				}
				if(cookie.getName().equals("usernick")){
					httpSession.setAttribute("usernick", Base64Util.decodeToString(cookie.getValue()));
					count ++;
				}
			}
		}
		Map<String, Object> map = invocation.getInvocationContext()
				.getSession();
		System.out.println("userpageinterceptor 的 userid = "+map.get("userid")+", usernick ="+ map.get("usernick") +", useremail = "+map.get("useremail"));
		if (map.get("userid") != null && map.get("usernick") != null
				&& map.get("useremail") != null){
			/*
			 * 预防：第一次登陆的人进入初始化界面之后再人为访问user.action
			 * 即：没有初始化的用户强制拉入初始化界面
			 */
			if(map.get("isFirstToUse")!=null && map.get("isFirstToUse").equals("yes")){
				return "userinitpage";
			}
			String result = invocation.invoke();
			System.out.println("userpageInterceptor 的result = "+result );
			return result;
		}else{
			return "welcome";
		}
	}

}
