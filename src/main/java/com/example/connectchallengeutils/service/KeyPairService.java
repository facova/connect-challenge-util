package com.example.connectchallengeutils.service;

import com.example.connectchallengeutils.domain.Keys;
import com.example.connectchallengeutils.repository.KeysRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import java.time.ZonedDateTime;
import java.util.Base64;

@Service
@AllArgsConstructor
public class KeyPairService {

    private KeysRepository repository;

    public Keys generate() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        String pubKey = base64(keyPair
                .getPublic()
                .getEncoded());
        String privKey = base64(keyPair
                .getPrivate()
                .getEncoded());

        Keys pair = new Keys(
                ZonedDateTime.now().toEpochSecond(),
                pubKey,
                privKey
        );
        return repository.save(pair);
    }

    public Keys findById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "key pair not found"));
    }

    private String base64(byte[] code) {
        return Base64.getEncoder().encodeToString(code);
    }

}
