package hackatongrupo7.medicamentos_grupo7.user;

import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequest;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequestAdmin;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserResponse;
import hackatongrupo7.medicamentos_grupo7.utils.ApiMessageDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/my-user/allergies")
    @ResponseStatus(HttpStatus.OK)
    public ApiMessageDto addAllergyToLoggedUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody String allergy) {
        userService.addAllergyToUser(customUserDetails.getUser(), allergy);
        return new ApiMessageDto("Alergia añadida correctamente.");
    }

    @GetMapping
    public List<UserResponse> listAllUsers(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                    @PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/my-user")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getLoggedUser(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        return userService.getLoggedUser(customUserDetails.getId());
    }

    @PutMapping("/my-user")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse putLoggedUser(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                      @RequestBody @Valid UserRequest userRequest){
        return userService.updateLoggedUser(userRequest, customUserDetails.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse putUserByAdmin(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                      @RequestBody @Valid UserRequestAdmin userRequest,
                                      @PathVariable Long id){
        return userService.updateUserByAdmin(userRequest, customUserDetails.getId());
    }

    @DeleteMapping("/my-user")
    @ResponseStatus(HttpStatus.OK)
    public ApiMessageDto deleteLoggedUser(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        userService.deleteUser(customUserDetails.getId());
        return new ApiMessageDto("Account deleted!!");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiMessageDto deleteUserByAdmin(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                           @PathVariable Long id){
        userService.deleteUser(id);
        return new ApiMessageDto("Account deleted!!");
    }
}
