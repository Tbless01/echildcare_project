package semicolon.africa.echildcarebackend.services.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.Token;
import semicolon.africa.echildcarebackend.data.repositories.TokenRepository;

import java.util.Optional;

@Service
@AllArgsConstructor

public class TokenServiceImpl implements TokenService{
    private final TokenRepository tokenRepository;
    @Override
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Optional<Token> findTokenByUserEmailAddress(String emailAddress) {
        return tokenRepository.findTokenByUserEmailAddress(emailAddress);
    }

    @Override
    public Optional<Token> findTokenByJwt(String jwt) {
        return tokenRepository.findTokenByJwt(jwt);
    }


}
