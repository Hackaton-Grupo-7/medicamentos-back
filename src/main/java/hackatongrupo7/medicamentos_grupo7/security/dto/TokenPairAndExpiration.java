package hackatongrupo7.medicamentos_grupo7.security.dto;

public record TokenPairAndExpiration(String accessToken, String refreshToken, long jwtExpirationMs) {}

