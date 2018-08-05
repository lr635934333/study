package com.liuran.web.uc.service;

import com.liuran.web.uc.domain.User;
import com.liuran.web.uc.repository.UserRepository;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Component
public class UserService {
    @Autowired
    private UserRepository repository;
    public User createUser(User user){
        user.setId(UUID.randomUUID().toString());

        //加密后的字符串
        user.setPassword(MD5(user.getPassword().trim()).toLowerCase());
        return repository.save(user);
    }

    public static void main(String[] args) {
        System.out.println(MD5("liuran"));
    }

    private static String MD5(String str) {
        MessageDigest md= null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] digest = new byte[0];
        try {
            digest = md.digest(str.getBytes(Charset.defaultCharset().name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Hex.encodeHexString(digest);
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

}
