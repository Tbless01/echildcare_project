package semicolon.africa.echildcarebackend.services.confirmOtp;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.VerificationToken;
import semicolon.africa.echildcarebackend.services.verification.TokenVerificationService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmOtpServiceImpl implements ConfirmOtpService {

    private final TokenVerificationService tokenVerificationService;

    @Override
    public ApiResponse confirmOtp(String Otp) {
        Optional<VerificationToken> foundToken = tokenVerificationService.findVerificationTokenByToken(Otp);
        if (foundToken.isPresent()){
            return ApiResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .isSuccessful(true)
                    .build();
    }
        return ApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .isSuccessful(false)
                .build();
        }
}
