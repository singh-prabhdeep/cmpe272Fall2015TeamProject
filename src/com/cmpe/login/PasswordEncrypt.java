package com.cmpe.login;

import java.security.MessageDigest;

public class PasswordEncrypt {

	public PasswordEncrypt(){
	}
	
	/*
	 * Crediting user1452273
	 * Obtained from 
	 * http://stackoverflow.com/questions/5531455/how-to-encode-some-string-with-sha256-in-java/11009612#11009612
	 */
	public static String sha256(String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
	
}
