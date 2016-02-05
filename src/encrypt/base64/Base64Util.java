package encrypt.base64;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Util {
	/*
	 * 将字符串Base64转码
	 */
	public static String encodeToString(String src){
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(src.getBytes());
	}
	
	/*
	 * 将字符串base64解码
	 */
	public static String decodeToString(String src){
		Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(src));
	}
	
	/*
	 * 将byte[]转码
	 */
	public static String encodeToString(byte[] src){
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(src);
	}
	
	/*
	 * 将byte[] base64解码
	 */
	public static byte[] decodeToByte(String src){
		Decoder decoder = Base64.getDecoder();
		return decoder.decode(src);
	}
}
