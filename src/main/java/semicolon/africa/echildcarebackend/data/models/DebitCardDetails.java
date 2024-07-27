package semicolon.africa.echildcarebackend.data.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebitCardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String debitCardNumber;
    private String  expiringMonth;
    private String expiringYear;
    private String cvv;
    private String userEmail;
}

