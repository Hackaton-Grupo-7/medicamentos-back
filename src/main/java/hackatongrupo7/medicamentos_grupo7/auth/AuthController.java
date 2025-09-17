package hackatongrupo7.medicamentos_grupo7.auth;


import hackatongrupo7.medicamentos_grupo7.auth.dto.AuthResponse;
import hackatongrupo7.medicamentos_grupo7.auth.dto.LoginRequest;
import hackatongrupo7.medicamentos_grupo7.auth.dto.RefreshRequest;
import hackatongrupo7.medicamentos_grupo7.user.CustomUserDetails;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequest;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserResponse;
import hackatongrupo7.medicamentos_grupo7.utils.ApiMessageDto;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody UserRequest request) throws MessagingException {
        return authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse refresh(@Valid @RequestBody RefreshRequest request,
                                @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return authService.refresh(request, customUserDetails);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public ApiMessageDto logout(HttpServletRequest request) {
        return authService.logout(request);
    }
}
