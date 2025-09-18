package hackatongrupo7.medicamentos_grupo7.user;


import hackatongrupo7.medicamentos_grupo7.user.dto.UserMapper;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequest;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserResponse;
import hackatongrupo7.medicamentos_grupo7.user.role.Role;
import hackatongrupo7.medicamentos_grupo7.user.utils.UserServiceHelper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserServiceHelper userServiceHelper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Nested
    class LoadUserTest {

        @Test
        void should_loadExistingUser_fromRequest() {
            UserRequest userRequest = new UserRequest("userTest", "nameTest", "usertest@test.com", "password123");
            User userSaved = new User();
            userSaved.setId(1L);
            userSaved.setUsername("userTest");
            userSaved.setName("nameTest");
            userSaved.setEmail("usertest@test.com");
            userSaved.setPassword("password123");

            when(userServiceHelper.findByUsername("userTest")).thenReturn(userSaved);

            List<GrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );

            UserDetails userLogExpected = new CustomUserDetails(userSaved);


            UserDetails userLogResponse = userService.loadUserByUsername("userTest");

            assertEquals(userLogExpected.getUsername(), userLogResponse.getUsername());
            assertEquals(userLogExpected.getAuthorities(), userLogResponse.getAuthorities());
            assertEquals(userLogExpected.getPassword(), userLogResponse.getPassword());


        }

        @Test
        void should_loadExistingUser_throw_exception() {

            when(userServiceHelper.findByUsername("userTest"))
                    .thenThrow(new UsernameNotFoundException("userTest does not exist."));

            assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("userTest"));
        }

    }

    @Nested
    class getLoggedUser{

        @Test
        void when_getLoggesUser_return_loggedUser(){
            User user = new User(99L,"usernameTest", "nameTest", "email@test.com", "testPassword", Role.USER);
            UserResponse userResponse = new UserResponse(99L,"usernameTest", "nameTest", "email@test.com", Role.USER);

            when(userServiceHelper.findById(99L)).thenReturn(user);
            when(userMapper.toResponse(user)).thenReturn(userResponse);
            UserResponse response = userService.getLoggedUser(99L);

            assertEquals("usernameTest", response.username());
            assertEquals("nameTest", response.name());
            assertEquals("email@test.com", response.email());

        }

        @Test
        void when_getLoggedUser_throw_exception() {
            when(userServiceHelper.findById(99L))
                    .thenThrow(new IllegalArgumentException("Username by id does not exist"));

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> userService.getLoggedUser(99L)
            );

            assertEquals("Username by id does not exist", exception.getMessage());

        }
    }

    @Nested
    class updateLoggedUser {

        @Test
        void when_updateLoggedUser_with_new_data_then_return_updated_response() {
            User user = new User(99L, "oldUsername", "oldName", "old@email.com", "oldPassword", Role.USER);
            UserRequest request = new UserRequest("newUsername", "","new@email.com", "newPassword");

            User updatedUser = new User(99L, "newUsername", "oldName", "new@email.com", "encodedPassword", Role.USER);
            UserResponse expectedResponse = new UserResponse(99L, "newUsername", "oldName", "new@email.com", Role.USER);

            when(userServiceHelper.findById(99L)).thenReturn(user);
            doAnswer(invocation -> {
                user.setUsername("newUsername");
                user.setEmail("new@email.com");
                user.setPassword("encodedPassword");
                return null;
            }).when(userServiceHelper).updateUserData(request, user);
            when(userMapper.toResponse(user)).thenReturn(expectedResponse);

            UserResponse response = userService.updateLoggedUser(request, 99L);

            assertEquals("newUsername", response.username());
            assertEquals("new@email.com", response.email());
            assertEquals("oldName", response.name());
        }

        @Test
        void when_updateLoggedUser_with_empty_request_then_return_same_response() {
            User user = new User(99L, "sameUsername", "sameName", "same@email.com", "samePassword", Role.USER);
            UserRequest request = new UserRequest("", "", "", "");

            UserResponse expectedResponse = new UserResponse(99L, "sameUsername", "sameName", "same@email.com", Role.USER);

            when(userServiceHelper.findById(99L)).thenReturn(user);
            doAnswer(invocation -> {
                return null;
            }).when(userServiceHelper).updateUserData(request, user);
            when(userMapper.toResponse(user)).thenReturn(expectedResponse);

            UserResponse response = userService.updateLoggedUser(request, 99L);

            assertEquals("sameUsername", response.username());
            assertEquals("same@email.com", response.email());
            assertEquals("sameName", response.name());
        }

        @Test
        void when_updateLoggedUser_user_not_found_then_throw_exception() {
            UserRequest request = new UserRequest("any", "any", "any", "any");

            when(userServiceHelper.findById(99L))
                    .thenThrow(new IllegalArgumentException("User not found"));

            assertThrows(IllegalArgumentException.class, () -> {
                userService.updateLoggedUser(request, 99L);
            });
        }
    }

    @Nested
    class deleteLoggedUser {
        @Test
        void when_user_exists_then_delete_successfully() {
            User user = new User(99L, "deleteUser", "Delete Name", "delete@email.com", "password", Role.USER);

            when(userServiceHelper.findById(99L)).thenReturn(user);

            userService.deleteMyUser(99L);

            verify(userRepository).deleteById(99L);
        }

        @Test
        void when_user_not_found_then_throw_exception() {
            when(userServiceHelper.findById(99L))
                    .thenThrow(new IllegalArgumentException("User not found"));

            assertThrows(IllegalArgumentException.class, () -> {
                userService.deleteMyUser(99L);
            });

            verify(userRepository, never()).deleteById(anyLong());
        }


    }

}
