package com.quizapp.quiz_versioning_system.security.jwt;

import java.text.ParseException;
import java.util.Date;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.nimbusds.jose.JOSEObjectType;

import com.quizapp.quiz_versioning_system.common.entity.User;



@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtConfig jwtConfig;

    public String generateToken(User user)
            throws JOSEException {

        JWSSigner signer = new MACSigner(jwtConfig.getSecret());

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()

                .subject(user.getEmail())

                .claim("role",
                        user.getRole().name())

                .issueTime(new Date())

                .expirationTime(
                        new Date(
                                System.currentTimeMillis()
                                        + jwtConfig.getExpiration()))

                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.HS256)
                        .type(JOSEObjectType.JWT)
                        .build(),
                claimsSet);

        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public JWTClaimsSet validateToken(String token)
            throws ParseException, JOSEException {

        SignedJWT signedJWT = SignedJWT.parse(token);

        boolean valid = signedJWT.verify(
                new MACVerifier(jwtConfig.getSecret()));

        if (!valid) {
            throw new RuntimeException("Invalid JWT token");
        }

        return signedJWT.getJWTClaimsSet();
    }
}