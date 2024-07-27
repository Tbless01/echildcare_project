package semicolon.africa.echildcarebackend.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileDetailsResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String imageUrl;
}
