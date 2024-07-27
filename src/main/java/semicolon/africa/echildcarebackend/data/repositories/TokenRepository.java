package semicolon.africa.echildcarebackend.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semicolon.africa.echildcarebackend.data.models.Token;

import java.util.Optional;

public interface TokenRepository  extends JpaRepository<Token, Long> {

        Optional<Token> findTokenByUserEmailAddress(String userEmailAddress);

        Optional<Token> findTokenByJwt(String jwt);
}
