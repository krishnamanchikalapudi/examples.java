package com.krishna.security.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.krishna.security.EncryptDecrypt;

/**
 * @author Krishna Manchikalapudi
 */
public class EncryptDecryptTests {

	private String plainText = "Hello world!.";

	@Test()
	public void testEncrypt() {
		try {
			String encryptText = EncryptDecrypt.encrypt(plainText);
			System.out.println("encrypt length: " + encryptText.length()
					+ " text:: " + encryptText);
			Assert.assertNotNull(encryptText);
		} catch (Exception ex) {
			ex.printStackTrace();
		} // TRY - CATCH
	}

	@Test()
	public void testDecrypt() {
		try {
			String eText = EncryptDecrypt.encrypt(plainText);
			System.out.println("\nencrypt length: " + eText.length()
					+ " text:: " + eText);

			String actual = EncryptDecrypt.decrypt(eText);
			System.out.println("decrypt length: " + actual.length()
					+ "  text:: " + actual);

			Assert.assertEquals(plainText, actual);
		} catch (Exception ex) {
			ex.printStackTrace();
		} // TRY - CATCH
	}

	@Test()
	public void testEncryptDecryptWithIvSize16() {
		testEncryptDecryptWithIvSize(16);
	}

	@Test()
	public void testEncryptDecryptWithIvSize32() {
		testEncryptDecryptWithIvSize(32);
	}

	@Test()
	public void testEncryptDecryptWithIvSize48() {
		testEncryptDecryptWithIvSize(48);
	}

	@Test()
	public void testEncryptDecryptWithIvSize64() {
		testEncryptDecryptWithIvSize(64);
	}

	@Test()
	public void testEncryptDecryptWithIvSize80() {
		testEncryptDecryptWithIvSize(80);
	}

	@Test()
	public void testEncryptDecryptWithIvSize96() {
		testEncryptDecryptWithIvSize(96);
	}

	@Test()
	public void testEncryptDecryptWithIvSize112() {
		testEncryptDecryptWithIvSize(112);
	}

	@Test()
	public void testEncryptDecryptWithIvSize128() {
		testEncryptDecryptWithIvSize(128);
	}

	private void testEncryptDecryptWithIvSize(int size) {
		try {
			System.out.println("\nIV_SIZE: " + size);
			String eText = EncryptDecrypt.encrypt(plainText, size);
			System.out.println("encrypt length: " + eText.length() + " text:: "
					+ eText);

			String actual = EncryptDecrypt.decrypt(eText, size);
			System.out.println("decrypt length: " + actual.length()
					+ "  text:: " + actual);

			Assert.assertEquals(plainText, actual);
		} catch (Exception ex) {
			ex.printStackTrace();
		} // TRY - CATCH
	}

}
