package com.example.connectchallengeutils.application;


import com.example.connectchallengeutils.domain.Keys;
import com.example.connectchallengeutils.response.KeyResponse;
import com.example.connectchallengeutils.response.PubKeyResponse;
import com.example.connectchallengeutils.service.KeyPairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/keypair")
public class ConnectChallengeController {

    private final KeyPairService keyPairService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyResponse> keyPair() throws NoSuchAlgorithmException {

        Keys keys = keyPairService.generate();

        return ResponseEntity.ok(KeyResponse.builder()
                .privateKey(keys.getPrivateKey())
                .publicKey(keys.getPublicKey())
                .createdAt(keys.getCreatedAt())
                .build());
    }

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyResponse> findById(@PathVariable String id) {

        Keys keys = keyPairService.findById(Integer.parseInt(id));

        return ResponseEntity.ok(KeyResponse.builder()
                .privateKey(keys.getPrivateKey())
                .publicKey(keys.getPublicKey())
                .createdAt(keys.getCreatedAt())
                .build());
    }

    @GetMapping(value = "keys", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PubKeyResponse> keys() {

        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm8kO3I1bdjnto/7tcjte4cv225gDkGKRn712LIfTJ//e0ioRdv5zZsuw5fhyAL7TmW1NPiPUHTalrZjBHB/bGtekFoCY0/ydtdvXFCPincXYn8BpX1QKdjmCu/D3IRb5Pil5HoD/nrX+YnRui6qTOFeeiEKtALZAtJElK1YGd1gw/wH3YgWZvA/sNFkGe5VgfT3yb7jHrGejMHF6A/rO/dFgY+B/AElg6hAvim4/rj0oqfInAh40dZv9XN/kQledIlHAkqPcLOOIMued2WeI763/LYuQmpPJ8HtHb+ELCrtRtTRt51R6hk+OTeBO8+ZAPNijoJyOcLFhQ6RKCzbCAwIDAQAB";

        return ResponseEntity.ok(PubKeyResponse.builder()
                .publicKey(pubKey)
                .createdAt(123456789L)
                .build());
    }
}
