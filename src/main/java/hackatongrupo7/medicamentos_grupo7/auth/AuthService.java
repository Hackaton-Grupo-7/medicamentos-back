package hackatongrupo7.medicamentos_grupo7.auth;


import hackatongrupo7.medicamentos_grupo7.auth.dto.AuthResponse;
import hackatongrupo7.medicamentos_grupo7.auth.dto.LoginRequest;
import hackatongrupo7.medicamentos_grupo7.auth.dto.RefreshRequest;
import hackatongrupo7.medicamentos_grupo7.security.jwt.JwtService;
import hackatongrupo7.medicamentos_grupo7.user.User;
import hackatongrupo7.medicamentos_grupo7.user.UserRepository;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserMapper;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequest;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserResponse;
import hackatongrupo7.medicamentos_grupo7.user.role.Role;
import hackatongrupo7.medicamentos_grupo7.user.utils.UserServiceHelper;
import hackatongrupo7.medicamentos_grupo7.utils.ApiMessageDto;
import hackatongrupo7.medicamentos_grupo7.utils.EmailService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenBlacklistService tokenBlacklistService;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserServiceHelper userServiceHelper;
    private final EmailService emailService;

    @Transactional
    public UserResponse register(UserRequest request) throws MessagingException {
        userServiceHelper.validateUserDoesNotExist(request.username(), request.email());
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = userMapper.toEntity(request, encodedPassword, Role.USER);
        emailService.sendWelcomeEmail(request.email(), request.username());

        return userMapper.toResponse(userRepository.save(user));
    }

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new AuthResponse("login", "Bearer", accessToken, loginRequest.username(), refreshToken);
    }

    public AuthResponse refresh(RefreshRequest request, UserDetails userDetails) {
        if (tokenBlacklistService.isTokenInBlacklist(request.refreshToken())) {
            throw new AuthenticationCredentialsNotFoundException("Refresh token is blacklisted");
        }
        if (!jwtService.isValidToken(request.refreshToken())) {
            throw new AuthenticationCredentialsNotFoundException("Invalid or expired refresh token");
        }
        tokenBlacklistService.addToBlacklist(request.refreshToken());

        String newAccessToken = jwtService.refreshAccessToken(request.refreshToken(), userDetails);
        String newRefreshToken = jwtService.generateRefreshToken(userDetails);

        return new AuthResponse("refresh", "Bearer", newAccessToken, userDetails.getUsername(), newRefreshToken);
    }

    public ApiMessageDto logout(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new AuthenticationCredentialsNotFoundException("No Bearer token found in Authorization header");
        }

        String token = authorizationHeader.substring(7);
        if (jwtService.isValidToken(token)) {
            tokenBlacklistService.addToBlacklist(token);
        }

        String refreshToken = request.getHeader("Refresh-Token");
        if (refreshToken != null && jwtService.isValidToken(refreshToken)) {
            tokenBlacklistService.addToBlacklist(refreshToken);
        }

        return new ApiMessageDto("Logout successful");
    }

}

