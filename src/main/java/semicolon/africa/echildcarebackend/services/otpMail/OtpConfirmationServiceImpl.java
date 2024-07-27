package semicolon.africa.echildcarebackend.services.otpMail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.enumClasses.TakenStatus;
import semicolon.africa.echildcarebackend.data.models.VerificationToken;
import semicolon.africa.echildcarebackend.services.verification.TokenVerificationService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;
import semicolon.africa.echildcarebackend.utils.GenerateApiResponse;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OtpConfirmationServiceImpl implements OtpConfirmationService{
    private final TokenVerificationService tokenVerificationService;
    @Override
    public ApiResponse confirmOtp(String otp) {
log.info("I got here");
       Optional<VerificationToken> otpResponse =  tokenVerificationService.findVerificationTokenByToken(otp);
       log.info("i'm the found otp from the database {}", otpResponse );
       log.info("I'm the status {}", otpResponse);
       log.info("i'm the token itself {}", otpResponse);
       if(otpResponse.isPresent()) {
           var t = otpResponse.get();
           if (t.getTakenStatus().equals(TakenStatus.NOT_TAKEN)){
               var foundOtp = otpResponse.get();
               log.info("I'm the otp before saving {}", foundOtp);
               foundOtp.setTakenStatus(TakenStatus.TAKEN);
               var savedOtp = tokenVerificationService.save(foundOtp);
               log.info("I'm the otp {}", savedOtp);

               return ApiResponse.builder()
                       .data(GenerateApiResponse.VERIFICATION_SUCCESSFUL)
                       .isSuccessful(true)
                       .statusCode(HttpStatus.OK.value())
                       .httpStatus(HttpStatus.OK)
                       .build();
           }
       }
       return ApiResponse.builder()
               .data(GenerateApiResponse.INVALID_CREDENTIALS)
               .httpStatus(HttpStatus.BAD_REQUEST)
               .statusCode(HttpStatus.BAD_REQUEST.value())
               .isSuccessful(false)
               .build();
    }
}
