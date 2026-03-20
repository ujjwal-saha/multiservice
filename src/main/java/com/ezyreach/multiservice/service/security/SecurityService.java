package com.ezyreach.multiservice.service.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.ezyreach.multiservice.config.AEPSProps;
import com.ezyreach.multiservice.exception.MultiserviceException;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Service
public class SecurityService {
	// creating a logger
	Logger logger = LoggerFactory.getLogger(SecurityService.class);
    private static final String JCE_PROVIDER = "BC";
	private static final int SYMMETRIC_KEY_SIZE = 128;
    @Autowired
    private ResourceLoader resourceLoader;
	private final RestClient restClient;
    private final AEPSProps aepsProps;


	public SecurityService(RestClient.Builder restClientBuilder,AEPSProps aepsProps) {
		this.aepsProps=aepsProps;
		this.restClient = restClientBuilder.baseUrl(this.aepsProps.getEndpoint().getBaseUrl()).build();
	}

	public byte[] generateSha256Hash(byte[] message) {
		String algorithm = "SHA-256";
		String SECURITY_PROVIDER = "BC";

		byte[] hash = null;
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm, SECURITY_PROVIDER);
			digest.reset();
			hash = digest.digest(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hash;
	}

	public  byte[] generateSessionKey() throws NoSuchAlgorithmException, NoSuchProviderException {

		KeyGenerator kgen = KeyGenerator.getInstance("AES", JCE_PROVIDER);

		kgen.init(SYMMETRIC_KEY_SIZE);

		return kgen.generateKey().getEncoded();
	}
/*
	public static String encryptUsingSessionKey(byte[] skey, byte[] data) throws InvalidCipherTextException {
		PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new AESEngine(), new PKCS7Padding());
		cipher.init(true, new KeyParameter(skey));
		int outputSize = cipher.getOutputSize(data.length);
		byte[] tempOP = new byte[outputSize];
		int processLen = cipher.processBytes(data, 0, data.length, tempOP, 0);
		int outputLen = cipher.doFinal(tempOP, processLen);
		byte[] result = new byte[processLen + outputLen];
		System.arraycopy(tempOP, 0, result, 0, result.length);
		return  new String(Base64.encode(result));
	}*/

	public  String encryptUsingSessionKey(byte[] skey, byte[] data) throws InvalidCipherTextException {
	    if (skey == null || skey.length == 0)
	        throw new IllegalArgumentException("Session key must not be null or empty.");

		if (data == null)
	        throw new IllegalArgumentException("Data must not be null.");

	    // Initialize AES Cipher with PKCS7 Padding
	    PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new AESEngine(), new PKCS7Padding());
	    cipher.init(true, new KeyParameter(skey));

	    // Prepare output buffer
	    int outBufSize = cipher.getOutputSize(data.length);
	    byte[] cipherText = new byte[outBufSize];

	    int processedLen = cipher.processBytes(data, 0, data.length, cipherText, 0);
	    int finalLen = cipher.doFinal(cipherText, processedLen);

	    int totalLen = processedLen + finalLen;

	    // Truncate to actual ciphertext length (if required)
	    byte[] output = new byte[totalLen];
	    System.arraycopy(cipherText, 0, output, 0, totalLen);

	    // Use BouncyCastle Base64 encoder directly (for compatibility)
	    return Base64.getEncoder().encodeToString(output);
	}
	

	
	public  String encryptUsingPublicKey(byte[] message) {
		byte[] ciphertextBytes = null;
		try {
			// The source of randomness
			SecureRandom secureRandom = new SecureRandom();
			//Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			// Obtain a RSA Cipher Object
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
			// Loading certificate file
			ClassPathResource classPathResource = new ClassPathResource("/public_key.txt");
			//String certFile = "/resources/public_key.txt";
			InputStream inStream = classPathResource.getInputStream();
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf.generateCertificate(inStream);
			inStream.close();
			// Read the public key from certificate file
			RSAPublicKey pubkey = (RSAPublicKey) cert.getPublicKey();
			// Initialize the cipher for encryption
			cipher.init(Cipher.ENCRYPT_MODE, pubkey, secureRandom);
			// Encrypt the message
			ciphertextBytes = cipher.doFinal(message);
			Base64.getEncoder().encodeToString(ciphertextBytes);
		} catch (IOException e) {
			System.out.println("IOException:" + e);
			e.printStackTrace();
		} catch (CertificateException e) {
			System.out.println("CertificateException:" + e);
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException:" + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			e.printStackTrace();
		}
		return null;
	}

	public String restCall(String strRequest, String uri,WebClient webClient) throws InvalidCipherTextException, NoSuchAlgorithmException, NoSuchProviderException {
        String resp=null;
		try{
			String encryptedRequest= encryptUsingSessionKey(generateSessionKey(),strRequest.getBytes());
			Mono<String> response = webClient.post()
			.uri(uri)
			.headers(header->{ header.addAll(getHeaders(strRequest));})
			.bodyValue(encryptedRequest)
			.retrieve()
			.bodyToMono(String.class);
			//.timeout(Duration.ofSeconds(5))
			//.retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)));
			resp=response.block();
	
		}catch(Exception ex){
           logger.info(ex.getMessage());
		   throw new MultiserviceException(ex.getMessage());
		}

		logger.info("URI = "+uri+" and Request ==== "+strRequest+" and Response = "+resp);

		return resp;
	}

	public String restGetCallWithoutBody(String uri,WebClient webClient) throws InvalidCipherTextException, NoSuchAlgorithmException, NoSuchProviderException {
        String resp=null;
		try{			
			Mono<String> response = webClient.get()
			.uri(uri)			
			.retrieve()		
			.bodyToMono(String.class);
			//.timeout(Duration.ofSeconds(5))
			//.retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)));
			resp=response.toString();
			getValue(response);
	
		}catch(Exception ex){
            logger.info(ex.getMessage());
			throw new MultiserviceException(ex.getMessage());
		}

		logger.info("URI = "+uri+" and Response = "+resp);

		return resp;
	}

	public void getValue(Mono<String> monoString){
	  monoString.subscribe(System.out::println);
	}

	private MultiValueMap<String, String> getHeaders(String request){
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		
		Date currentDate = new Date();       
        String pattern = "dd/mm/yyyy HH:mm:ss";       
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);      
        String trnTimestamp = simpleDateFormat.format(currentDate);
		byte[] hash=generateSha256Hash(request.getBytes());
	
		try {
			String eskey = encryptUsingPublicKey(generateSessionKey());
			headers.add("trnTimestamp",trnTimestamp);
		    headers.add("hash",Base64.getEncoder().encodeToString(hash));
		    headers.add("eskey",eskey);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

		return headers;
	}

}
