package encrypt.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5Util {
	public static String makeSrcToMD5(String src) {
		String result = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] resultByte = messageDigest.digest(src.getBytes());
			result = Hex.encodeHexString(resultByte);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("MD5加密失败");
			System.out.println("============下面是MD5的错误信息==============");
			e.printStackTrace();
			System.out.println("============上面是MD5的错误信息==============");
		}
		return result;
	}
}
