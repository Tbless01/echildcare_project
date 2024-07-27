package semicolon.africa.echildcarebackend.services.profile;

import semicolon.africa.echildcarebackend.dtos.response.ProfileDetailsResponse;

public interface ProfileService {

    ProfileDetailsResponse getProfileUpdate(String emailAddress);
}
