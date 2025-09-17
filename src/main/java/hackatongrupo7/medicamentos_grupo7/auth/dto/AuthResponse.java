package hackatongrupo7.medicamentos_grupo7.auth.dto;

public record AuthResponse(
        String message,
        String tokenType,
        String token,
        String username,
        String refreshToken) {
}