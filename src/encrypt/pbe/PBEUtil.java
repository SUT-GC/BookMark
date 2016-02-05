package encrypt.pbe;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Hex;

public class PBEUtil {

	private byte[] salt;
	private byte[] result;
	private byte[] byteSrc;
	private String password;
	private String stringSrc;
	private Key key;

	public PBEUtil(String password, String src) throws Exception {
		this.stringSrc = src;
		this.password = password;
		// 初始化盐
		SecureRandom random = new SecureRandom();
		// 产生一个8位的盐,盐必须8位长度
		salt = random.generateSeed(8);
		// 将口令转换成秘钥
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());

		// PBEWITHMD5andDES产生秘钥工厂
		SecretKeyFactory secretKeyFactory = SecretKeyFactory
				.getInstance("PBEWITHMD5andDES");
		// 产生Key
		key = secretKeyFactory.generateSecret(pbeKeySpec);
	

	}
	
	public PBEUtil(String password, byte[] src, byte[] salt) throws Exception {
		this.byteSrc = src;
		this.password = password;
		this.salt = salt;
		// 将口令转换成秘钥
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());

		// PBEWITHMD5andDES产生秘钥工厂
		SecretKeyFactory secretKeyFactory = SecretKeyFactory
				.getInstance("PBEWITHMD5andDES");
		// 产生Key
		key = secretKeyFactory.generateSecret(pbeKeySpec);
	

	}

	public byte[] PBEEncode() throws Exception{
		// 加密
		// 产生PBE参数, 用盐和迭代次数初始化
		PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
		Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
		cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
		result = cipher.doFinal(stringSrc.getBytes());
		return (result);
	}

	public String PBEDecode() throws Exception {
		// 产生PBE参数, 用盐和迭代次数初始化
		PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
		Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
		// 解密
		cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
		result = cipher.doFinal(byteSrc);
		return new String(result);
	}

	public byte[] getSalt(){
		return salt;
	}
}
