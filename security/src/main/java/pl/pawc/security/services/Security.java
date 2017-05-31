package pl.pawc.security.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public class Security implements ISecurityService{

    public String generateSalt() throws NoSuchAlgorithmException{
        SecureRandom secureRandom = SecureRandom.getInstance("NativePNRG");
        byte[] salt = new byte[256];
        secureRandom.nextBytes(salt);
        return Base64.encodeBase64String(salt);
    }

	public String hashWithSalt(String password, String salt) throws UnsupportedEncodingException, NoSuchAlgorithmException{
	    String saltedPassString = password+salt;
	    byte[] saltedPassBytes = saltedPassString.getBytes("UTF-8");
	    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	    byte[] digestedSaltedPassBytes = messageDigest.digest(saltedPassBytes);
	    return Base64.encodeBase64String(digestedSaltedPassBytes);
	}
}