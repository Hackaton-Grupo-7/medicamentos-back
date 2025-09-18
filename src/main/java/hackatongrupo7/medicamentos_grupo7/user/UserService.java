package hackatongrupo7.medicamentos_grupo7.user;

import hackatongrupo7.medicamentos_grupo7.exceptions.EmptyListException;
import hackatongrupo7.medicamentos_grupo7.medication.Medication;
import hackatongrupo7.medicamentos_grupo7.medication.MedicationRepository;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserMapper;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequest;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequestAdmin;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserResponse;
import hackatongrupo7.medicamentos_grupo7.user.utils.UserServiceHelper;
import hackatongrupo7.medicamentos_grupo7.utils.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EntityUtil mapperUtil;
    private final UserServiceHelper userServiceHelper;
    private final MedicationRepository medicationRepository;

    public void addAllergyToUser(User user, String allergy) {
        List<Medication> medications = medicationRepository.findByUserId(user.getId());
        for (Medication med : medications) {
            if (med.getAllergies() != null && med.getAllergies().contains(allergy)) {
                throw new IllegalArgumentException("No puedes añadir esta alergia porque estás tomando un medicamento que contiene esa alergia.");
            }
        }
        user.getAllergies().add(allergy);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServiceHelper.findByUsername(username);
        return new CustomUserDetails(user);
    }

    public UserResponse getLoggedUser(Long id){
        return userMapper.toResponse(userServiceHelper.findById(id));
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){throw new EmptyListException();}
        return mapperUtil.mapEntitiesToDTOs(users, userMapper::toResponse);
    }

    public UserResponse getUserById(Long userId) {
        return userMapper.toResponse(userServiceHelper.findById(userId));
    }


    public UserResponse updateLoggedUser(UserRequest userRequest, Long id){
        User user = userServiceHelper.findById(id);
        userServiceHelper.updateUserData(userRequest, user);
        return (userMapper.toResponse(user));
    }

    public UserResponse updateUserByAdmin(UserRequestAdmin userRequest, Long id){
        User user = userServiceHelper.findById(id);
        userServiceHelper.updateUserAdminData(userRequest, user);
        return (userMapper.toResponse(user));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(userServiceHelper.findById(id).getId());
    }



}
