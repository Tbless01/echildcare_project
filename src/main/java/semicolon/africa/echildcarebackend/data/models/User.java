package semicolon.africa.echildcarebackend.data.models;

import jakarta.persistence.*;
import lombok.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.GenderType;
import semicolon.africa.echildcarebackend.data.models.enumClasses.Roles;
import semicolon.africa.echildcarebackend.data.models.enumClasses.UserCategory;

import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserCategory userCategory;
    @CollectionTable(name = "user_role")
    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;
    @Enumerated(EnumType.STRING)
    private GenderType genderType;
    private String imageUrl;
}
