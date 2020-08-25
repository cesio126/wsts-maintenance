package br.com.rio.maintenance.usefull;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component("criptoFactory")
public class CriptographyFactory {

	private static final String UNICODE_FORMAT = "UTF-8";
	private String myEncryptionKey;

	public CriptographyFactory(@Value("${app.set.encriptyKey}") String myEncryptionKey) throws Exception {
		super();
		this.myEncryptionKey = myEncryptionKey;
	}

	public String encrypt(String unencryptedString) {
		String encryptedString = null;
		try {
			byte[] KeyData = this.myEncryptionKey.getBytes();
			SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, KS);
			String ret = new String(cipher.doFinal(unencryptedString.getBytes(UNICODE_FORMAT)));
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	public String decrypt(String encryptedString) {
		String decryptedText = null;
		try {
		    byte[] KeyData = this.myEncryptionKey.getBytes();
		    SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
		    Cipher cipher = Cipher.getInstance("Blowfish");
		    cipher.init(Cipher.DECRYPT_MODE, KS);
		    String ret = new String(cipher.doFinal(encryptedString.getBytes()));
		    return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}
}
