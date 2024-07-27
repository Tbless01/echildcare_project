package semicolon.africa.echildcarebackend.services.token;

import semicolon.africa.echildcarebackend.data.models.Token;

import java.util.Optional;

public interface TokenService {
    Token saveToken(Token token);

    Optional<Token> findTokenByUserEmailAddress(String emailAddress);

    Optional<Token> findTokenByJwt(String jwt);
}
