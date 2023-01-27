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
                .createdAt(123456789L)
                .build());
    }

    @GetMapping(value = "keys", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PubKeyResponse> keys() {

        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnYRuNEpyvQba7095CewtRpqIN+TKyZ31TjyW8qUDt4nPp7Y9ovS4xv/p094ihBrmFGHE9S/Gw61VGZuuT8nApl8VPoE6lI/UV26ADmQwiyKyII0gnldiOuPCylj+bdXLfE6RCXFe/o1WPve5P1K44hgkwGfyNhAVVhbc8mEgFegE4itO1rduKueF4TRkhO35Hdi4LUIX041B/AZgyYfwLxpX8EudMUKG0FYI8QI+rUWkZBY9AXdxCKzExcmsA9mzFhMPiwZ6y2MucdeytPElAyDmUAFg6cEUs3ShJgsFy4K76cHp/3ArRJTchLSYBppi6hYRTDO+u6gJnvI4w1pN3wIDAQAB";

        return ResponseEntity.ok(PubKeyResponse.builder()
                .publicKey(pubKey)
                .createdAt(123456789L)
                .build());
    }

    private String base64(byte[] code) {
        return Base64.getEncoder().encodeToString(code);
    }

}
