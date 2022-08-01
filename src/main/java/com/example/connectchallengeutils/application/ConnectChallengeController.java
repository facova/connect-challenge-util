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
    public ResponseEntity<PubKeyResponse> keys() {

        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwQR6g64OC7gWTc9WtNTgLIWhfts6eVk4mcO6nNx65JpcmRbyh4pEWgg6DNiyKP3+5U5uSu8OmvoPMCS681KbZKvh6QgVE6XXpJxfysWKWFAeFwmV3wYmzPzLCdWuuMVT5uPz1nQfAY2yXEzqwCrAEH328rjk3LClt2JreiaUAx9vw0JBqpAxaJjg6aRENddes21Ep2ak3f8Eqk9l5j7+Kpux/FMDI55exz+2BkB13Ons47H89UqALsmqRnEGPGaRgvoexwL4mPsT8s47NwaDoaEJADA85YzrbbnljJc3hKIu9yiXVCkQg6+13xEEmLDaNPPx94SNXZmcGK/TcLMgOQIDAQAB";

        return ResponseEntity.ok(PubKeyResponse.builder()
                .publicKey(pubKey)
                .createdAt(ZonedDateTime.now())
                .build());
    }

    private String base64(byte[] code) {
        return Base64.getEncoder().encodeToString(code);
    }

}
