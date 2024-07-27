package semicolon.africa.echildcarebackend.services.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.User;
import semicolon.africa.echildcarebackend.data.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddressIgnoreCase(emailAddress).orElse(null);
   }
}
