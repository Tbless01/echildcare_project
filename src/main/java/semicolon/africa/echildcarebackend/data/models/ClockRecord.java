package semicolon.africa.echildcarebackend.data.models;

import jakarta.persistence.*;
import lombok.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.ClockRecordStatus;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClockRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clockId;
    private String careTakerEmailAddress;
    private LocalDateTime clockIn;
    private LocalDateTime ClockOut;
    @Enumerated(EnumType.STRING)
    private ClockRecordStatus clockRecordStatus;
}
