package com.example.connectchallengeutils.application;


import com.example.connectchallengeutils.response.KeyResponse;
import com.example.connectchallengeutils.response.PubKeyResponse;
import com.example.connectchallengeutils.service.KeyPairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Base64;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/create")
public class ConnectChallengeController {

    private final KeyPairService keyPairService;

    @GetMapping(value = "keypair", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyResponse> keyPair() throws NoSuchAlgorithmException {

        KeyPair keyPair = keyPairService.generate();
        String pubKey = base64(keyPair
                .getPublic()
                .getEncoded());
        String privKey = base64(keyPair
                .getPrivate()
                .getEncoded());

        return ResponseEntity.ok(KeyResponse.builder()
                .privateKey(privKey)
                .publicKey(pubKey)
                .createdAt(ZonedDateTime.now())
                .build());
    }

    @GetMapping(value = "key", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PubKeyResponse> key() throws NoSuchAlgorithmException {

        KeyPair keyPair = keyPairService.generate();
        String pubKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

        return ResponseEntity.ok(PubKeyResponse.builder()
                .publicKey(pubKey)
                .createdAt(ZonedDateTime.now())
                .build());
    }

    @GetMapping(value = "keys", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PubKeyResponse> key() {

        var pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjmlIEpHV9ZIhFO2ZKHUqqnoTaaYypj8KxQZ50LSWQAx0ouYZm+ToLWId3oEEkapZsPEUeL0JZQbR/jRZZCVJFKHh6ChunFQBndxUDPSebx/296JA0Dj+8OyxWUbPBHCgoq3WepBqfbWrJ+3ae3mQ5qNMDuMQ8FjzoEp46eFFDI3yDyhNQq1bIJEvPoUHfxu+8MN18mdIPnSRbeDv1/pcb6bkigGeqSrSOzboQBUxY2GulxlkI4HN0cZScbNX3TGU6/z3Gw4BjLk1d6GpDAnwUu9c/i9/H8lkMmIi9Re3NpeK31DKxbzKPgtoaT5oXkJ3kXYBy4jAClwXtvwx90zh3QIDAQAB"

        return ResponseEntity.ok(PubKeyResponse.builder()
                .publicKey(pubKey)
                .createdAt(ZonedDateTime.now())
                .build());
    }

    private String base64(byte[] code) {
        return Base64.getEncoder().encodeToString(code);
    }

}
