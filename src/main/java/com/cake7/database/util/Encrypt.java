package com.cake7.database.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Encrypt {
    private final MessageDigest digest;
    private final String salt;  // 한 번 생성한 후 유지할 Salt

    public Encrypt() throws NoSuchAlgorithmException {
        this.digest = MessageDigest.getInstance("SHA-256");
        this.salt = generateSalt(); // 생성 시 Salt 저장
    }

    // SHA-256 적용 + 고정된 Salt 사용
    public String getEncrypt(String pwd) {
        digest.update((pwd + salt).getBytes());
        byte[] pwdSalt = digest.digest();

        // 바이트 배열을 16진수 문자열로 변환
        StringBuilder sb = new StringBuilder();
        for (byte b : pwdSalt) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // 랜덤한 Salt 생성 (고정된 Salt를 제공)
    private String generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] saltBytes = new byte[16];
        sr.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    // Salt를 외부에서 가져올 수 있도록 Getter 제공
    public String getSalt() {
        return this.salt;
    }
}
