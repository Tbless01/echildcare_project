package semicolon.africa.echildcarebackend.services.verification;

import semicolon.africa.echildcarebackend.data.models.VerificationToken;

import java.util.Optional;

public interface TokenVerificationService {
    VerificationToken save(VerificationToken token);

    Optional<VerificationToken> findVerificationTokenByToken(String token);

    String generateVerificationToken(int length);

}
