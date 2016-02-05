package encrypt.test;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.asn1.pkcs.PBEParameter;

import encrypt.base64.Base64Util;
import encrypt.pbe.PBEUtil;

public class TestPBE {

	public static void main(String[] args) {
		try {
			PBEUtil pbeUtil = new PBEUtil("gc", "231231233");
			byte[] byteresult = pbeUtil.PBEEncode();
			byte[] salt = pbeUtil.getSalt();
			String stringsalt = Base64.encodeBase64String(salt);
			
			PBEUtil pbeUtil2 = new PBEUtil("gc", byteresult, Base64.decodeBase64(stringsalt));
			System.out.println(pbeUtil2.PBEDecode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
