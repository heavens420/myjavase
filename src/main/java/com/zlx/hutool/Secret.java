package com.zlx.hutool;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

public class Secret {

    public static void main(String[] args) throws Exception {
//        System.out.println("base64:"+ Arrays.toString(Base64.getDecoder().decode("abc")));
//        System.out.println("bytesUtil:"+ Arrays.toString(ByteUtils.fromHexString("abc")));
//        System.out.println("hutool:" + Arrays.toString(cn.hutool.core.codec.Base64.decode("abc")));
//        System.out.println("str:" + Arrays.toString("abc".getBytes()));
        String encryptKey = "hj7x89H$yuBI0456";
        String plainText = "abc123哈哈";

        Cipher c = Cipher.getInstance("SM4/ECB/PKCS5Padding", BouncyCastleProvider.PROVIDER_NAME);
        c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(StandardCharsets.UTF_8), "SM4"));
        Cipher c2 = Cipher.getInstance("SM4/ECB/PKCS5Padding", BouncyCastleProvider.PROVIDER_NAME);
        c2.init(Cipher.DECRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(StandardCharsets.UTF_8), "SM4"));

        byte[] ss =  c.doFinal(plainText.getBytes());
        System.out.println("ss：" + Arrays.toString(ss));
        System.out.println("plain:"+Arrays.toString(plainText.getBytes()));
        String mingwen = ByteUtils.toHexString(ss);
        System.out.println(mingwen);


        byte[] bytes1 = ByteUtils.fromHexString(plainText);
        System.out.println("bytes1:"+Arrays.toString(bytes1));
//        byte[] bytes1 = Base64.getDecoder().decode(mingwen);
        byte[] bytes = c2.doFinal(ss);
//        System.out.println(new String(bytes,StandardCharsets.ISO_8859_1));
//        System.out.println(new String(bytes,StandardCharsets.UTF_8));
//        System.out.println(ByteUtils.toHexString(bytes));
        String result = new String(bytes);
        System.out.println(result);

    }

    static {
        // 添加Bouncy Castle提供者
        Security.addProvider(new BouncyCastleProvider());
    }

//    public static void main(String[] args) throws Exception {
//        String key = "hj7x89H$yuBI0456";
//        String plainText = "这是一段需要加密的信息。123";
//        byte[] keyBytes = key.getBytes(); // 生成随机密钥
//        byte[] ivBytes = keyBytes; // 生成随机初始向量，用于CBC模式
//
//        byte[] encrypted = sm4Encrypt(keyBytes, ivBytes, plainText.getBytes("UTF-8"));
//        System.out.println(Arrays.toString(encrypted));
//        System.out.println(Arrays.toString(plainText.getBytes("UTF-8")));
//        System.out.println(Arrays.toString(ByteUtils.fromHexString(plainText)));
//        System.out.println();
//        System.out.println("加密后的密文：" + ByteUtils.toHexString(encrypted));
//
//        byte[] decrypted = sm4Decrypt(keyBytes, ivBytes, encrypted);
//        System.out.println("解密后的明文：" + new String(decrypted, "UTF-8"));
//    }

    public static byte[] sm4Encrypt(byte[] key, byte[] iv, byte[] data) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "SM4");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("SM4/CBC/PKCS5Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        return cipher.doFinal(data);
    }

    public static byte[] sm4Decrypt(byte[] key, byte[] iv, byte[] encrypted) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "SM4");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("SM4/CBC/PKCS5Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        return cipher.doFinal(encrypted);
    }
}
