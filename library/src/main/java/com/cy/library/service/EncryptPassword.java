package com.cy.library.service;

/**
 * Created by serious on 2014/9/6.
 */
public class EncryptPassword {
    public static final String encrypt(String password) {
        int randomCode = 100 + (int)(Math.random() * 100);

        return randomCode + MD5.getMD5(MD5.getMD5(password) + randomCode);
    }
}
