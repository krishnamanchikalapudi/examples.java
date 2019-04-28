package com.krishna.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author: Krishna Manchikalapudi
 */
public final class EncryptDecrypt {
	// update the SALT encryption key & length must be 16 chars
	private static final String SALT_KEY = "SaltKey123456789";
	/**
	 * If IV_SIZE = 16, encrypted string size is 64. If IV_SIZE = 32, encrypted
	 * string size is 96. If IV_SIZE = 48, encrypted string size is 128. If
	 * IV_SIZE = 64, encrypted string size is 160. If IV_SIZE = 80, encrypted
	 * string size is 192. If IV_SIZE = 96, encrypted string size is 224. If
	 * IV_SIZE = 112, encrypted string size is 256. If IV_SIZE = 128, encrypted
	 * string size is 288.
	 * 
	 * NOTE: Tested with JDK 1.8.x 64bit
	 */
	private static int IV_SIZE = 112;

	// DO NOT Modify below properties
	private static final String ALGORITHM = "SHA1PRNG";
	private static final String DEFAULT_CIPHER = "AES/CBC/PKCS5Padding";
	private static final String DEFAULT_KEYTYPE = "AES";
	private static final char hexchars[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * @param plainText
	 * @return default encrypted string length is 256
	 * @throws IllegalArgumentException
	 *             , NoSuchAlgorithmException, NoSuchPaddingException,
	 *             InvalidAlgorithmParameterException, InvalidKeyException,
	 *             BadPaddingException, IllegalBlockSizeException,
	 *             ShortBufferException
	 */
	public static synchronized String encrypt(String plainText)
			throws Exception {
		String encryptedTxt = null;
		if (plainText != null && plainText.trim().length() != 0) {
			SecretKey secretKey = new SecretKeySpec(SALT_KEY.getBytes(),
					DEFAULT_KEYTYPE);

			byte[] iv = new byte[IV_SIZE];
			SecureRandom randomSeed = SecureRandom.getInstance(ALGORITHM);
			// Force seed generation. This is very slow
			byte junk[] = new byte[1];
			randomSeed.nextBytes(junk);
			SecureRandom secureRandom = randomSeed;
			secureRandom.nextBytes(iv);

			// Copy the iv into the first 16 position in encryptedText
			IvParameterSpec ivspec = new IvParameterSpec(iv, 0, 16);
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

			byte[] encryptedText = new byte[iv.length
					+ cipher.getOutputSize(plainText.getBytes().length)];
			System.arraycopy(iv, 0, encryptedText, 0, iv.length);

			// Put the contents after iv in encryptedText
			cipher.doFinal(plainText.getBytes(), 0,
					plainText.getBytes().length, encryptedText, iv.length);

			char[] result = new char[encryptedText.length * 2];
			for (int i = 0, j = 0; i < encryptedText.length; i++, j += 2) {
				byte b = encryptedText[i];
				result[j] = hexchars[(b >>> 4) & 0x0F];
				result[j + 1] = hexchars[b & 0x0F];
			} // for (int i = 0, j = 0; i < encryptedText.length; i++, j += 2)

			encryptedTxt = new String(result);
		} // if (plainText!= && plainText.trim().length()!=0)
		return encryptedTxt;
	}

	/**
	 * @param encryptedText
	 *            , string length must be 256
	 * @return plain text
	 * @throws IllegalArgumentException
	 *             , IllegalBlockSizeException, NoSuchPaddingException,
	 *             NoSuchAlgorithmException, InvalidAlgorithmParameterException,
	 *             BadPaddingException, InvalidKeyException
	 */
	public static synchronized String decrypt(String encryptedText)
			throws Exception {
		// public static synchronized String decrypt(String encryptedText)
		// throws {
		String plainText = null;
		if (encryptedText != null && encryptedText.trim().length() != 0) {
			SecretKey secretKey = new SecretKeySpec(SALT_KEY.getBytes(),
					DEFAULT_KEYTYPE);

			int len = encryptedText.length();
			byte[] encryptedData = new byte[len / 2];
			for (int i = 0; i < len; i += 2) {
				encryptedData[i / 2] = (byte) ((Character.digit(
						encryptedText.charAt(i), 16) << 4) + Character.digit(
						encryptedText.charAt(i + 1), 16));
			} // for (int i = 0; i < len; i += 2)

			byte[] iv = new byte[IV_SIZE];
			System.arraycopy(encryptedData, 0, iv, 0, iv.length);

			// Cipher cipher = getCipher(Cipher.DECRYPT_MODE, iv);
			IvParameterSpec ivspec = new IvParameterSpec(iv, 0, 16);
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

			byte[] plainTxt = cipher.doFinal(encryptedData, iv.length,
					encryptedData.length - iv.length);
			plainText = new String(plainTxt);
		} // if (encryptedText!= null && encryptedText.trim().length()!=0)
		return plainText;
	}

	/**
	 * @param plainText
	 * @param ivSize
	 *            , must be 16, 32, 48, 64, 80, 196, 112, 128. NOTE: 16 multiple
	 * @return default encrypted string length is 256
	 * @throws IllegalArgumentException
	 *             , NoSuchAlgorithmException, NoSuchPaddingException,
	 *             InvalidAlgorithmParameterException, InvalidKeyException,
	 *             BadPaddingException, IllegalBlockSizeException,
	 *             ShortBufferException
	 */
	public static synchronized String encrypt(String plainText, int ivSize)
			throws Exception {
		IV_SIZE = ivSize;
		return encrypt(plainText);
	}

	/**
	 * @param encryptedText
	 * @param ivSize
	 *            , must be 16, 32, 48, 64, 80, 196, 112, 128. NOTE: 16 multiple
	 * @return plain text
	 * @throws IllegalArgumentException
	 *             , IllegalBlockSizeException, NoSuchPaddingException,
	 *             NoSuchAlgorithmException, InvalidAlgorithmParameterException,
	 *             BadPaddingException, InvalidKeyException
	 */
	public static synchronized String decrypt(String encryptedText, int ivSize)
			throws Exception {
		IV_SIZE = ivSize;
		return decrypt(encryptedText);
	}
}