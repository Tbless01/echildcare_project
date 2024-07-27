package semicolon.africa.echildcarebackend.services.authentication;


import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.Token;
import semicolon.africa.echildcarebackend.dtos.request.LoginRequest;
import semicolon.africa.echildcarebackend.exceptions.UserLoginException;
import semicolon.africa.echildcarebackend.security.JwtService;
import semicolon.africa.echildcarebackend.services.token.TokenService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;
import semicolon.africa.echildcarebackend.utils.GenerateApiResponse;

@Service
@AllArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final TokenService tokenService;

    public ApiResponse login(LoginRequest loginRequest) throws UserLoginException {
        authenticateUser(loginRequest);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmailAddress());
        if(userDetails==null) throw new UserLoginException(GenerateApiResponse.INVALID_CREDENTIALS);
        String jwt = jwtService.generateToken(userDetails);
       // revokeAllUserToken(loginRequest.getEmailAddress());
        saveToken(jwt, loginRequest.getEmailAddress());
        return GenerateApiResponse.okResponse(GenerateApiResponse.BEARER +jwt);
    }

    private void saveToken(String jwt, String emailAddress) {
        Token token = Token.builder()
                .jwt(jwt)
                .isExpired(false)
                .isRevoked(false)
                .userEmailAddress(emailAddress)
                .build();
        tokenService.saveToken(token);
    }

    private void revokeAllUserToken(String emailAddress) {
        var allUsertoken = tokenService.findTokenByUserEmailAddress(emailAddress);
        if(allUsertoken.isEmpty()) return;
        allUsertoken.
                ifPresent(token -> {
                    token.setRevoked(true);
                    token.setExpired(true);
                    tokenService.saveToken(token);
                });
    }

    private void authenticateUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmailAddress(), loginRequest.getPassword()));
    }
}
