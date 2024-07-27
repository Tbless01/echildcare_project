package semicolon.africa.echildcarebackend.dtos.response;

import lombok.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookSessionResponse {

    private String bookingId;
    private PaymentStatus paymentStatus;
    private BigDecimal amount;
    private String message;
    private String firstName;
    private String lastName;
    private String careTakerEmailAddress;
    private String dateOfBirth;
    private String resumptionDate;
    private String parentFullName;
    private int numberOfKids;
    private String timeDuration;
    private int careTimeDuration;


}
