package br.com.rpw.monitoramento.api.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeradorSenha {

	public static void main(String args[]) {
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[];
			messageDigest = algorithm.digest("123456".getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			
			System.out.println(hexString.toString());
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
