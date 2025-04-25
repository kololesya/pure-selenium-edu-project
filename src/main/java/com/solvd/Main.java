package com.solvd;

import com.solvd.laba.utils.EncryptionUtils;

public class Main {
    public static void main(String[] args) throws Exception {
        String encryptedPassword = EncryptionUtils.encrypt("12344321olesya@gmail.com");
        System.out.println("Encrypted password: " + encryptedPassword);
    }
}