package semicolon.africa.echildcarebackend.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semicolon.africa.echildcarebackend.data.models.BookedSessions;

import java.util.Optional;

public interface BookedSessionRepository extends JpaRepository<BookedSessions, Long> {
   // Optional<BookedSessions> findAllByCareTakerEmailAddress(String CareTakerEmailAddress);

}
