package com.example.task_management_api.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class KeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Or HS256, HS384, etc.
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Generated Key: " + base64Key);
    }
}

