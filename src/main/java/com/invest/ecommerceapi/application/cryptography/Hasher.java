package com.invest.ecommerceapi.application.cryptography;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class Hasher {
    public String hash(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

    public boolean compare(String plainText, String hashedText) {
        return BCrypt.checkpw(plainText, hashedText);
    }
}
