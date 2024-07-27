package semicolon.africa.echildcarebackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.User;
import semicolon.africa.echildcarebackend.services.user.UserService;


@Service
@RequiredArgsConstructor
public class SecuredUserService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByEmailAddress(username);
        return new SecuredUser(user);
    }
}
