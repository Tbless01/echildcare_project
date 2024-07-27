package semicolon.africa.echildcarebackend.dtos.request;

import lombok.Getter;

import lombok.Setter;


@Getter
@Setter

public class EditProfileRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
}
