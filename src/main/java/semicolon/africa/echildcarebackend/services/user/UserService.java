package semicolon.africa.echildcarebackend.services.user;

import semicolon.africa.echildcarebackend.data.models.User;

public interface UserService {

    User save(User user);
    User findUserByEmailAddress(String emailAddress);
}
