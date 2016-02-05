package smalltools;

public class ReplaceSpecialCharacter {
	/*
	 * 1：将字符串中的"<"替换成“&lt"
	 */
	public static String replaceBracket(String src){
		return src.replaceAll("<", "&lt");
	}
	
	
	/*
	 * 主函数：测试函数
	 */
	public static void main(String[] args) {
		/*
		 * 测试函数1
		 */
		System.out.println(replaceBracket("<html>"));
	}
}
