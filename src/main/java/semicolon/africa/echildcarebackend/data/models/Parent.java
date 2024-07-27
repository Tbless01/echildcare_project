package semicolon.africa.echildcarebackend.data.models;


import jakarta.persistence.*;
import lombok.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.CareTakerAssigned;

import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
//   @PrimaryKeyJoinColumn(name = "user_id")
//    @Column(name = "user_id")
    private User user;
    @CollectionTable(name = "children")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Child> child;
    private Set<Child> child;
    @OneToMany(fetch = FetchType.EAGER)
//    @PrimaryKeyJoinColumn(name = "debit_card")
    private List<DebitCardDetails> debitCardDetails;
    @Enumerated(EnumType.STRING)
    private CareTakerAssigned careTakerAssigned;
    @OneToMany(fetch = FetchType.EAGER)
    private List<BookedSessions> bookedSessions;

}
