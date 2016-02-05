package encrypt.test;


import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.asn1.pkcs.PBEParameter;

public class TestPBEStatic {

	public static String src = "i am gc PBE";

	public static void main(String[] args) {
		jdkPBE();
		//运行结果
		/*
		 * 加密之后的结果:e9c7035f7c54def110404a3218ed351d
		 *	解密之后的结果:i am gc PBE
		 */
	}

	public static void jdkPBE() {
		try {
			// 初始化盐
			SecureRandom random = new SecureRandom();
			// 产生一个8位的盐,盐必须8位长度
			byte[] salt = random.generateSeed(8);
			// 创建口令
			String password = "gc";
			// 将口令转换成秘钥
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());

			// PBEWITHMD5andDES产生秘钥工厂
			SecretKeyFactory secretKeyFactory = SecretKeyFactory
					.getInstance("PBEWITHMD5andDES");
			// 产生Key
			Key key = secretKeyFactory.generateSecret(pbeKeySpec);

			// 加密
			// 产生PBE参数, 用盐和迭代次数初始化
			PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
			Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("加密之后的结果:" + Hex.encodeHexString(result));
			
			// 解密
			cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
			result = cipher.doFinal(result);
			System.out.println("解密之后的结果:" + new String(result));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}