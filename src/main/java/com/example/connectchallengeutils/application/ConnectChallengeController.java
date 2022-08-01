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

        String pubKey = "MIIEowIBAAKCAQEAsidsU7b0QU0RhoaIvdv2bAfEu0SxZUZ5U42jbhC5D7+cTiy9E30EucMWjsgyXWb8QpK20XSDSSSFXTXrBQhZGvw2pGaC7cIW/VE2cf4Dbzd6KXxhOPHAl57hh4LdFbvzDq4kZnrUmHpvqsk1eG2angMKNJLY4gxIev1cABwIt2PowV0/HebHE+3t2bwx8bSqwem01c6me40s9mPWdDiMxEm8rJeZ7Isbwxhjwdfu3jjrGBl4LSbnWUan5e2vRzEhK/6Q4leTuuHD6DzhnYrzVZ9aPMO6wR5aUQsGErVSW7VWmK0pWwNW+oSyWlA/+6k85Y4gObKINGNoXWM4xqTEEQIDAQABAoIBABI3P7dLlKv8olcb+G1ZSVgAb1gqjOzrCMrHR7egVBvNHsFLh8WvWcVYsjzFMYklV5PDBphiDm5zIoPGWJJ6KQo+UaP7q1V+K/WY0TVUoKtFg+MsCZDV/zhBkZPWiJ6JSi/tFGG6G3S/yB0xV5tJsa6IZwm7Hhv2345by4VjWGzPybSt6+xwsU52isr8YvXs0bCVVtpJBxeK8G+iDyJafZ2G17gYOI8j58UsckVbddXtZyFWt2o0bBcna9ftal3rFyZVi0UMac97XJGXUy5d2cC99/2vNbT+A08E/sDS1jxP1kihp4mJxtUZmQX0+v55qTuGv5rqOvR5PqYyjipmcyUCgYEA0LdumN15EsFEG/9IHJQ3J+qlOHij6lIwVrOOiFifj2lvuWlM5xlKPkxNUjw4VHfJeI4ZqPbPs4op3o3h4S+nIiq2GRuNzwrgatBfPFJ3YPQ8hZoItYHmlfvuvUwHWWeVt7sKIeIvIUZ/+YCEvTJNIJG8sHQImC/xrvEQWrRjJNMCgYEA2oOCW73ThpZxFABOVzc4EoII8s7w17j3rPEFaXIQ32citELMJPZ1gzmVVnK5HZNG35S9f3wushZD+664KrjamjVnbXoqH09Enzx39RLNRfOGLcbwhz6xleoNqT8JFcDQU3kMzruYowHmAH+2KDo5s2A8Ih4mGmVlfpyHRLmjtQsCgYBZ9WXm3zZq/f/0LUCQiNWJD9qsj1rfCROqmvOpEsGbF2+/M4Kg7MaSOWLuOeRxi7u7iUvIBrEZCkjnkrGz1E0uLciU48poXaDCiRMzNbwcfsiDMRPl35paNN7+mgFk6H+gVC6W0h1MM02/ZURRI5gYnJf+WdTGe4uowmAxKCS/XwKBgQDESbQ1Ix07XjLvy6KT9/d3sLXHH5v7vJ1IDi5VXXDIrgXftc1aOsau/XAn+uBNL/pPZ3aeaXgs45FoKbgoMGu2cNia4E2sWHGFNTVwwdYBy1MyFe4FH4045MomyYgosU6yXR/jKWNgspjgw9hm5H7mokECSODjf0gf5w4f7z6epwKBgBhAIpTBX2XP5JfbUifql7ITJtoKIU1kZcW8W095yLvdjnw9AcxtSu/UmepWdSw9bahxswjPWMqi6NXAAhNa3SvEsxcp7D1g97otSlBRYPF9qY4bOk1KoJcKZjR/7LNlmAcadnhVsEM2/6xemvkJeVKKuqPJdhZ1MkDZJ51G23Ih";

        return ResponseEntity.ok(PubKeyResponse.builder()
                .publicKey(pubKey)
                .createdAt(ZonedDateTime.now())
                .build());
    }

    private String base64(byte[] code) {
        return Base64.getEncoder().encodeToString(code);
    }

}
