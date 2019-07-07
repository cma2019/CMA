package com.example.demo.framework;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;


//CSDN博客https://www.cnblogs.com/memphis-f/p/10109027.html
public class Aes {
    /**
     *
     * @author lyt
     * AES128 算法
     *
     * CBC 模式
     *
     * PKCS7Padding 填充模式
     *
     * CBC模式需要添加偏移量参数iv，必须16位
     * 密钥 sessionKey，必须16位
     *
     * 介于java 不支持PKCS7Padding，只支持PKCS5Padding 但是PKCS7Padding 和 PKCS5Padding 没有什么区别
     * 要实现在java端用PKCS7Padding填充，需要用到bouncycastle组件来实现
     */
    private String sessionKey = "123456789abcdef1";
    // 偏移量 16位
    private final String iv = "1fedcba987654321";

    // 算法名称
    final String KEY_ALGORITHM = "AES";
    // 加解密算法/模式/填充方式
    final String algorithmStr = "AES/CBC/PKCS7Padding";
    // 加解密 密钥 16位

    byte[] ivByte;
    byte[] keybytes;
    private Key key;
    private Cipher cipher;
    boolean isInited = false;

    public void init() {//←传userKey进来(?)
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        System.out.println(sessionKey);
        keybytes = sessionKey.getBytes();
        ivByte = iv.getBytes();
//        int base = 16;
//        if (keybytes.length % base != 0) {
//            int groups = keybytes.length / base + (keybytes.length % base != 0 ? 1 : 0);
//            byte[] temp = new byte[groups * base];
//            Arrays.fill(temp, (byte) 0);
//            System.arraycopy(keybytes, 0, temp, 0, keybytes.length);
//            keybytes = temp;
//        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keybytes, KEY_ALGORITHM);
        System.out.println(key);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr,"BC");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String encrypt(String content) {
        byte[] encryptedText = null;
        byte[] contentByte = content.getBytes();
        init();
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivByte));
            encryptedText = cipher.doFinal(contentByte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(Hex.encode(encryptedText));
    }

    public String decrypt(String encryptedData) {
        byte[] encryptedText = null;
        byte[] encryptedDataByte = Hex.decode(encryptedData);
        init();
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivByte));
            encryptedText = cipher.doFinal(encryptedDataByte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(encryptedText);
    }

    public void changeKey(String newKey){
        System.out.println("current Key:"+sessionKey);
        /*if(newKey.length()>16)
            newKey=KeyCut(newKey);*/
        sessionKey=newKey;
        keybytes = sessionKey.getBytes();
        key = new SecretKeySpec(keybytes, KEY_ALGORITHM);
        System.out.println("changed");
        System.out.println("new Key:"+sessionKey);

    }

    public String KeyCut(String key){
        key=key.substring(0,16);
        return key;
    }
    /*public static void main(String[] args) {
        Aes aes = new Aes();

        //加密字符串
        String content = "2323";
        System.out.println("加密前的：" + content);
//        System.out.println("加密密钥：" + new String(keybytes));
        // 加密方法
        String enc = aes.encrypt(content);
        System.out.println("加密后的内容：" + enc);

        // 解密方法
        String dec = aes.decrypt(enc);
        System.out.println("解密后的内容：" + dec);
    }*/
}

