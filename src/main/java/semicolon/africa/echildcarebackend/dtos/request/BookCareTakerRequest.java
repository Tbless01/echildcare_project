package semicolon.africa.echildcarebackend.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BookCareTakerRequest {
    private int numberOfKids;

    private String timeDuration;
    private String genderType;
    private int careTimeDuration;
}
