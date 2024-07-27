package semicolon.africa.echildcarebackend.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semicolon.africa.echildcarebackend.data.models.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findVerificationTokenByToken(String token);
}
