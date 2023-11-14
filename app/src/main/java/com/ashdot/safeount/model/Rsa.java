//package com.ashdot.safeount.model;
//
//import android.util.Base64;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.security.KeyFactory;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.NoSuchAlgorithmException;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//
//import javax.crypto.Cipher;
//
///**
// * Created by Fengyufei on 2019/6/20.
// */
//
//public class Rsa {
//    /**
//     * RSA算法
//     */
//    public static final String RSA = "RSA";
//    /**加密方式，android的*/
////  public static final String TRANSFORMATION = "RSA/None/NoPadding";
//    /**
//     * 加密方式，标准jdk的
//     */
//    public static final String TRANSFORMATION = "RSA/None/PKCS1Padding";
//    /**
//     * 秘钥默认长度
//     */
//    public static final int DEFAULT_KEY_SIZE = 2048;
//    /**
//     * 加密的数据最大的字节数，即117个字节
//     */
//    public static final int MAX_ENCRYPT_BLOCK = (DEFAULT_KEY_SIZE / 8) - 11;
//    /**
//     * 解密的数据最大的字节数，即117个字节
//     */
//    private static final int MAX_DECRYPT_BLOCK = 512;
//
//
//    /**
//     * 生成密钥对，即公钥和私钥。key长度是512-2048，一般为1024
//     */
//    public static KeyPair generateRSAKeyPair(int keyLength) throws NoSuchAlgorithmException {
//        KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
//        kpg.initialize(keyLength);
//        return kpg.genKeyPair();
//    }
//
//    /**
//     * 获取公钥，打印为48-12613448136942-12272-122-913111503-126115048-12...等等一长串用-拼接的数字
//     */
//    public static PublicKey getPublicKey(KeyPair keyPair) {
//        return keyPair.getPublic();
//    }
//
//    /**
//     * 获取私钥，同上
//     */
//    public static PrivateKey getPrivateKey(KeyPair keyPair) {
//        return keyPair.getPrivate();
//    }
//
//    /**
//     * 用公钥加密
//     * 每次加密的字节数，不能超过密钥的长度值减去11
//     *
//     * @param data      需加密数据的byte数据
//     * @param publicKey 公钥
//     * @return 加密后的byte型数据
//     */
//    public static byte[] encryptByPublicKey(byte[] data, RSAPublicKey publicKey) throws Exception {
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//        // 编码前设定编码方式及密钥
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        // 传入编码数据并返回编码结果
//        return cipher.doFinal(data);
//    }
//
//    /**
//     * 用私钥解密
//     *
//     * @param encryptedData 经过encryptedData()加密返回的byte数据
//     * @param privateKey    私钥
//     * @return
//     */
//    public static byte[] decryptByPrivateData(byte[] encryptedData, PrivateKey privateKey) throws Exception {
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        return cipher.doFinal(encryptedData);
//    }
//
//    /**
//     * 私钥加密
//     *
//     * @param content
//     * @param privateKey
//     * @return
//     * @throws Exception
//     */
//    public static byte[] encryptByPrivateKey(byte[] content, PrivateKey privateKey) throws Exception {
//        Cipher cipher = Cipher.getInstance(RSA);
//        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
//        return cipher.doFinal(content);
//    }
//
//    /**
//     * 公钥解密
//     *
//     * @param content
//     * @param publicKey
//     * @return
//     * @throws Exception
//     */
//    public static byte[] decrypByPublicKey(byte[] content, PublicKey publicKey) throws Exception {
//        Cipher cipher = Cipher.getInstance(RSA);
//        cipher.init(Cipher.DECRYPT_MODE, publicKey);
//        return cipher.doFinal(content);
//    }
//
//    /**
//     * 使用公钥分段加密
//     */
//    public static byte[] encryptByPublicKeyForSpilt(byte[] data, PublicKey publicKey) throws Exception {
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        int inputLen = data.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        // 对数据分段加密
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > (((RSAPublicKey) publicKey).getModulus().bitLength() / 8 - 11)) {
//                cache = cipher.doFinal(data, offSet, (((RSAPublicKey) publicKey).getModulus().bitLength() / 8 - 11));
//            } else {
//                cache = cipher.doFinal(data, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * (((RSAPublicKey) publicKey).getModulus().bitLength() / 8 - 11);
//        }
//        byte[] encryptedData = out.toByteArray();
//        out.close();
//        return encryptedData;
//
//    }
//
//    /**
//     * 使用私钥分段解密
//     */
//    public static byte[] decryptByPrivateKeyForSpilt(byte[] encrypted, PrivateKey privateK) throws Exception {
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//        cipher.init(Cipher.DECRYPT_MODE, privateK);
//        int inputLen = encrypted.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        // 对数据分段解密
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
//                cache = cipher.doFinal(encrypted, offSet, MAX_DECRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(encrypted, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * MAX_DECRYPT_BLOCK;
//        }
//        byte[] decryptedData = out.toByteArray();
//        out.close();
//        return decryptedData;
//    }
//
//    //使用私钥分段加密
//    public static byte[] encryptByPublicKeyForSpilt(byte[] data, PrivateKey privateKey) {
//        return new byte[0];
//    }
//
//    /**
//     * 使用公钥分段解密
//     */
//    public static byte[] decryptByPublicKeyForSpilt(byte[] encrypted, PublicKey publicKey) throws Exception {
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//        cipher.init(Cipher.DECRYPT_MODE, publicKey);
//        int inputLen = encrypted.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        // 对数据分段解密
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > (((RSAPublicKey) publicKey).getModulus().bitLength() / 8)) {
//                cache = cipher.doFinal(encrypted, offSet, (((RSAPublicKey) publicKey).getModulus().bitLength() / 8));
//            } else {
//                cache = cipher.doFinal(encrypted, offSet, inputLen - offSet);
//            }
//            out.write(cache);
//            i++;
//            offSet = i * (((RSAPublicKey) publicKey).getModulus().bitLength() / 8);
//        }
//        byte[] decryptedData = out.toByteArray();
//        out.close();
//        return decryptedData;
//    }
//
//
//    /**
//     * 从字符串中加载公钥
//     *
//     * @param publicKeyStr 公钥数据字符串
//     * @throws Exception 加载公钥时产生的异常
//     */
//    public static RSAPublicKey loadPublicKey(String publicKeyStr) throws Exception {
//        byte[] buffer = Base64.decode(publicKeyStr, Base64.DEFAULT);
//        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
//        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
//    }
//
//    /**
//     * 从字符串中加载私钥
//     *
//     * @param priKeyStr 私钥数据字符串
//     * @throws Exception 加载公钥时产生的异常
//     */
//    public static RSAPrivateKey loadPrivateKey(String priKeyStr) throws Exception {
//        byte[] privateKeyBytes = Base64.decode(priKeyStr, Base64.DEFAULT);
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
//        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
//    }
//
//    /**
//     * 从文件中输入流中加载公钥
//     *
//     * @param in 公钥输入流
//     * @throws Exception 加载公钥时产生的异常
//     */
//    public static RSAPublicKey loadPublicKey(InputStream in) throws Exception {
//        try {
//            return loadPublicKey(readKey(in));
//        } catch (IOException e) {
//            throw new Exception("公钥数据流读取错误");
//        } catch (NullPointerException e) {
//            throw new Exception("公钥输入流为空");
//        }
//    }
//
//    /**
//     * 读取密钥信息
//     *
//     * @param in
//     * @return
//     * @throws IOException
//     */
//    private static String readKey(InputStream in) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(in));
//        String readLine = null;
//        StringBuilder sb = new StringBuilder();
//        while ((readLine = br.readLine()) != null) {
//            if (readLine.charAt(0) == '-') {
//                continue;
//            } else {
//                sb.append(readLine);
//                sb.append('\r');
//            }
//        }
//
//        return sb.toString();
//    }
//}
