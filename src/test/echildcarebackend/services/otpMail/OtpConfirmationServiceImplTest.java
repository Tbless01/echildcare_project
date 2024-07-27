package semicolon.africa.echildcarebackend.services.otpMail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.echildcarebackend.services.verification.TokenVerificationService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OtpConfirmationServiceImplTest {

    @Autowired
    private TokenVerificationService  tokenVerificationService;

    @Autowired
    private OtpConfirmationService otpConfirmationService;

    @Test
    void testThatOtpExists(){

       var response = otpConfirmationService.confirmOtp("Mvao");
        assertThat(response.isSuccessful()).isTrue();
    }
}