package com.example.connectchallengeutils.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "keypair")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Keys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long createdAt;
    private String publicKey;
    private String privateKey;

    public Keys(Long createdAt, String publicKey, String privateKey) {
        this.createdAt = createdAt;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
}
