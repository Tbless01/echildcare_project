package semicolon.africa.echildcarebackend.data.models;


import jakarta.persistence.*;
import lombok.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.BookedStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CareTaker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @Enumerated(EnumType.STRING)
    private BookedStatus bookedStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name = "clockInAndOut")
    List<ClockRecord> clockRecordList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@Column(name = "booked_sessions")
    List<BookedSessions> bookedSessions;
}
