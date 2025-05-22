package util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class LicenseEncryptor {

    private static final String KEY = "1234567890abcdef"; // 16位密钥

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("license.json");
        String json = new String(Files.readAllBytes(Paths.get("license.json")));
        String encrypted = encrypt(json, KEY);
        Files.write(Paths.get("license.dat"), encrypted.getBytes());
    }

    public static String encrypt(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secret = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
}
