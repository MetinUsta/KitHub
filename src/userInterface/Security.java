package userInterface;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {

	public static void main(String[] args) {
		String password = "123456789";

		System.out.println(getPasswordHash(password));
	}

	/**
	 * Generates a hex SHA-256 hash string from the given password
	 */
	public static String getPasswordHash(String password) {
		MessageDigest digest;
		byte[] encodedhash;
		StringBuilder hexString;

		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		hexString = new StringBuilder(2 * encodedhash.length);
		for (int i = 0; i < encodedhash.length; i++) {
			String hex = Integer.toHexString(0xff & encodedhash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return new String(hexString);
	}

}
