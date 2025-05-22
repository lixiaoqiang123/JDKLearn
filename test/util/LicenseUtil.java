package util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class LicenseUtil {

    // 生成密钥对（只执行一次，生成后保存）
    public static void generateKeyPair(String pubKeyPath, String priKeyPath) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // 公钥
        byte[] pub = keyPair.getPublic().getEncoded();
        Files.write(Paths.get(pubKeyPath), pub);

        // 私钥
        byte[] pri = keyPair.getPrivate().getEncoded();
        Files.write(Paths.get(priKeyPath), pri);
    }

    // 使用私钥对授权内容进行签名
    public static String sign(String data, String priKeyPath) throws Exception {
        byte[] priKeyBytes = Files.readAllBytes(Paths.get(priKeyPath));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(priKeyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = factory.generatePrivate(spec);

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());

        return Base64.getEncoder().encodeToString(signature.sign());
    }

    // 使用公钥验证签名
    public static boolean verify(String data, String signatureStr, String pubKeyPath) throws Exception {
        byte[] pubKeyBytes = Files.readAllBytes(Paths.get(pubKeyPath));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(pubKeyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = factory.generatePublic(spec);

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes());

        byte[] sigBytes = Base64.getDecoder().decode(signatureStr);
        return signature.verify(sigBytes);
    }

    // 示例主函数（生成/签名/验证）
    public static void main(String[] args) throws Exception {
        String pubKeyPath = "public.key";
        String priKeyPath = "private.key";

        // 1. 生成密钥对（只需运行一次）
        //generateKeyPair(pubKeyPath, priKeyPath);

        // 2. 待授权内容（授权信息）
        String licenseContent = "{\"licensee\":\"ACME Corp\",\"expire\":\"2025-11-31\"}";

        // 3. 私钥签名
//        String signed = sign(licenseContent, priKeyPath);
//        System.out.println("签名结果：" + signed);
        String signed = "lXyYT38gY51S9+wHHIDwGZMzzqADE79zKcsITwZq9ZfGnKPLsKujg8UbNxbFh3ML9PiYKBF9jovgl5mhuDeiAkpfo3uLZXdcKCen+1lz5pCEmyD0Lghc4dlo43lxuuAT4zoEUmuRmm1L8h7PeoZTTMVGRrQDVwUGyr3XxaVm7TOQSpI0PwSOT6VdL2SfZfUFtgwgbFBXxQuWasO3it4+KfPk6PYZtIP9qPqWcxGBbABPefvdg2jStTAWrz4+BKcwh09kEax0BrYeAVP/Xgce9whF/iKcIZukBsxNVM0LgfwlJno/FYEVhk9AGGIws9MrVTfJZWAOSJY9Lt33RuB1nA==";
        // 4. 公钥验证
        boolean ok = verify(licenseContent, signed, pubKeyPath);
        System.out.println("验证通过：" + ok);
    }
}

