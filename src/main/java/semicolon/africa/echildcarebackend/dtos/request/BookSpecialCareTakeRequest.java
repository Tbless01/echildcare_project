package semicolon.africa.echildcarebackend.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookSpecialCareTakeRequest {
    private int numberOfKids;

    private String timeDuration;
    private String genderType;
    private int careTimeDuration;
    private String firstName;
    private String lastName;

}
