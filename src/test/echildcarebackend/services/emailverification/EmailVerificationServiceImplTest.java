package semicolon.africa.echildcarebackend.services.emailverification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.echildcarebackend.dtos.request.RegistrationRequest;
import semicolon.africa.echildcarebackend.exceptions.UserRegistrationException;
import semicolon.africa.echildcarebackend.services.authentication.RegisterService;
import semicolon.africa.echildcarebackend.services.user.UserService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

import static org.assertj.core.api.Assertions.assertThat;

 @SpringBootTest
class EmailVerificationServiceImplTest {

     @Autowired
     private EmailVerificationService emailVerificationService;
     @Autowired
     private UserService userService;

     @Autowired
     private RegisterService registerService;
     @Test
     void testThatEmailCanBeVerified() throws UserRegistrationException {

         RegistrationRequest registrationRequest = new RegistrationRequest();
         registrationRequest.setUserCategory("PARENT");
         registrationRequest.setEmailAddress("Tayo@gmail.com");
         registrationRequest.setPassword("password");

       var registrationResponse =   registerService.register(registrationRequest);

         ApiResponse response = emailVerificationService.verifyEmailAddress("dami@gmail.com");

         ApiResponse emailVerification = emailVerificationService.verifyEmailAddress("Tayo@gmail.com");
         assertThat(registrationResponse.isSuccessful()).isTrue();
         assertThat(response.isSuccessful()).isTrue();
         assertThat(emailVerification.isSuccessful()).isFalse();
     }
}