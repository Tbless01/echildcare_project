package semicolon.africa.echildcarebackend.services.otpMail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.echildcarebackend.services.verification.TokenVerificationService;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class sendOtpServiceImplTest {

    @Autowired
    private TokenVerificationService tokenVerificationService;

    @Autowired
    private SendOtpService sendOtpService;

    @Test
    void testThatOtpCanBeSent(){
     var response  = sendOtpService.sendOtp("omoseebiomotayo@gmail.com");
    assertThat(response.isSuccessful()).isTrue();
    }
}