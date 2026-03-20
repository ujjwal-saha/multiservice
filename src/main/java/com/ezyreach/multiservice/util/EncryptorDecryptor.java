package com.ezyreach.multiservice.util;

import org.jasypt.util.text.AES256TextEncryptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class EncryptorDecryptor {
	
	private static final String SALT="EZY";
	
	private EncryptorDecryptor() {
		
	}
	
	public static String encrypt(String plainText) {
		AES256TextEncryptor encryptor=new AES256TextEncryptor();
		encryptor.setPassword(SALT);
		return encryptor.encrypt(plainText);
	}

	public static String decrypt(String encryptedText) {
		AES256TextEncryptor decryptor=new AES256TextEncryptor();
		decryptor.setPassword(SALT);
		return decryptor.decrypt(encryptedText);
	}
	
	public static void main(String[] args) {
		String target="MzZMKNh61yYvIRRxiNtV/WTmC/2Wb0+RXzCm4HBfSOmiAqNQMA5uvknNnguHehtl";
		String decryptedText=EncryptorDecryptor.decrypt(target);
		String encryptedText=EncryptorDecryptor.encrypt(decryptedText);
		
		log.info(decryptedText);
	}
}
