package de.sp.superBnB_backend_18_9_2024.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeyProperties(
        RSAPublicKey publicKey,
        RSAPrivateKey privateKey
) {
}
