package pl.pawc.security.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public class Security implements ISecurityService{
	
	public String generateSalt(){
		try{
	        SecureRandom secureRandom = SecureRandom.getInstance("NativePRNG");
    	    byte[] salt = new byte[32];
        	secureRandom.nextBytes(salt);
	        return Base64.encodeBase64String(salt);
    	}
		catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			return "error";
		}
	}

	public String hashWithSalt(String password, String salt){
		try{
		    String saltedPassString = password+salt;
		    byte[] saltedPassBytes = saltedPassString.getBytes("UTF-8");
		    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		    byte[] digestedSaltedPassBytes = messageDigest.digest(saltedPassBytes);
	    	return Base64.encodeBase64String(digestedSaltedPassBytes);
		}
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return "error";			
		}	
		catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			return "error";
		}
	}

}
