package semicolon.africa.echildcarebackend.services.profile;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.User;
import semicolon.africa.echildcarebackend.dtos.response.ProfileDetailsResponse;
import semicolon.africa.echildcarebackend.services.parent.ParentService;
import semicolon.africa.echildcarebackend.services.user.UserService;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private final UserService userService;
    private final ParentService parentService;
    @Override
    public ProfileDetailsResponse getProfileUpdate(String emailAddress) {

        User user = userService.findUserByEmailAddress(emailAddress);
        ProfileDetailsResponse profileDetailsResponse = new ProfileDetailsResponse();
        profileDetailsResponse.setFirstName(user.getFirstName());
        profileDetailsResponse.setLastName(user.getLastName());
        profileDetailsResponse.setPhoneNumber(user.getPhoneNumber());
        profileDetailsResponse.setImageUrl(user.getImageUrl());
        return profileDetailsResponse;
    }
}
