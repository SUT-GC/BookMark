package encrypt.test;

import encrypt.base64.Base64Util;

public class TestBase64 {

	public static void main(String[] args) {
		String src = "123";
		src = Base64Util.encodeToString(src);
		src = Base64Util.decodeToString(src);
		System.out.println(src);
	}

}
