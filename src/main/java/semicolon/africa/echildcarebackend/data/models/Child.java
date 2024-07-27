package semicolon.africa.echildcarebackend.data.models;

import jakarta.persistence.*;
import lombok.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.GenderType;
import semicolon.africa.echildcarebackend.data.models.enumClasses.SpecialNeedCategory;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String  dateOfBirth;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @Enumerated(EnumType.STRING)
    private SpecialNeedCategory specialNeedCategory;
    private String specialNeedDescription;
    private String homeAddress;
}
