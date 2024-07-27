package semicolon.africa.echildcarebackend.data.models;


import jakarta.persistence.*;
import lombok.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.TakenStatus;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TakenStatus takenStatus;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;

//    public VerificationToken(String token, TakenStatus takenStatus, LocalDateTime plusMinutes, LocalDateTime Now){
//        this.token = token;
//        this.createdAt = Now;
//        this.expiredAt = plusMinutes;
//        this.takenStatus = takenStatus;
//    }
}

