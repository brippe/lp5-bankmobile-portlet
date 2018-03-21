package edu.nocccd.portlets.lp.services.sso;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Advanced Encryption Standard utility
 * Handles the encrypted token for BankMobile
 *
 * @author Brad Rippe
 * @version 1.0
 * @see http://bankmobiledisbursements.com/ for more information on BankMobile.
 */
public class AESUtil {
    private static final String CIPHER_TYPE_AES = "AES/ECB/PKCS5Padding";
    private static final String ENCODING_TYPE_AES = "AES";

    /**
     * Encrypt the token with the shared key
     * @param sharedKey
     * @param token
     * @return
     */
    public static String aesEncrypt(String sharedKey, String token) {
        String encryptedString = null;
        try {
            SecretKey key = new SecretKeySpec(asBinary(sharedKey), ENCODING_TYPE_AES);
            Cipher cipher = Cipher.getInstance(CIPHER_TYPE_AES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(token.getBytes());
            encryptedString = asHex(encrypted).toUpperCase();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    /**
     * Decrypt the token with the shared key
     * @param sharedKey
     * @param token
     * @return
     */
    public static String aesDecrypt(String sharedKey, String token) {
        String decryptedString = null;
        try {
            SecretKey key = new SecretKeySpec(asBinary(sharedKey), ENCODING_TYPE_AES);
            Cipher cipher = Cipher.getInstance(CIPHER_TYPE_AES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(asBinary(token));
            decryptedString = new String(decrypted);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return decryptedString;
    }

    /**
     * Build the token with the clientcode, student id and the timestamp
     * @param clientCode
     * @param studentID
     * @param timestamp
     * @return
     */
    public static String buildTokenString(String clientCode, String studentID, String timestamp) {
        StringBuffer tokenString = new StringBuffer();
        tokenString.append(clientCode);
        tokenString.append("&");
        tokenString.append(studentID);
        tokenString.append("&");
        tokenString.append(timestamp);
        return tokenString.toString();
    }

    private static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        for(int i = 0; i < buf.length; i++) {
            if(((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

    private static byte[] asBinary(String hexChars) {
        int numBytes = hexChars.length() / 2;
        byte[] binary = new byte[numBytes];
        for(int x = 0; x < numBytes; x++) {
            String temp = "0x" + hexChars.charAt(x*2) + hexChars.charAt(x*2+1);
            binary[x] = Integer.decode(temp).byteValue();
        }
        return binary;
    }
}
