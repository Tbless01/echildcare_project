package semicolon.africa.echildcarebackend.data.models;

import jakarta.persistence.*;
import lombok.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.GenderType;
import semicolon.africa.echildcarebackend.data.models.enumClasses.PaymentStatus;
import semicolon.africa.echildcarebackend.data.models.enumClasses.TimeDuration;

import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookedSessions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int numberOfKids;
    @Enumerated(EnumType.STRING)
    private TimeDuration timeDuration;
    @Enumerated(EnumType.STRING)
    private GenderType genderType;
    private int careTimeDuration;
    private String parentEmailAddress;
    private String parentFullName;
    private String CareTakerEmailAddress;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String bookingId;
    private BigDecimal amount;
    public String getResumptionDate;
}
