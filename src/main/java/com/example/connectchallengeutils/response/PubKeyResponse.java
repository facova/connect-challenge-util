package com.example.connectchallengeutils.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming( PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PubKeyResponse {

    String publicKey;

    Long createdAt;
}
