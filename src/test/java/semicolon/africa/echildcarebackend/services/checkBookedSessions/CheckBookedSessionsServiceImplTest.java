
package semicolon.africa.echildcarebackend.services.checkBookedSessions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import semicolon.africa.echildcarebackend.data.models.enumClasses.TimeDuration;
import semicolon.africa.echildcarebackend.dtos.request.BookCareTakerRequest;
import semicolon.africa.echildcarebackend.dtos.request.RegistrationRequest;
import semicolon.africa.echildcarebackend.exceptions.UserRegistrationException;
import semicolon.africa.echildcarebackend.services.authentication.RegisterService;
import semicolon.africa.echildcarebackend.services.bookSession.BookSessionService;
import semicolon.africa.echildcarebackend.services.bookedCareTaker.BookCareTakerService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Slf4j
class CheckBookedSessionsServiceImplTest {
    @Autowired
    private BookSessionService bookSessionService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private BookCareTakerService bookCareTakerService;

    @Test
    void testThatACareTakerCanCheckBookedSessions() throws UserRegistrationException {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmailAddress("careTakerEmailAddress");
        registrationRequest.setFirstName("firstName");
        registrationRequest.setLastName("lastName");
        registrationRequest.setPassword("passwords");
        registrationRequest.setGenderType("MALE");
        registrationRequest.setUserCategory("CARETAKER");

       registerService.register(registrationRequest);

        RegistrationRequest registrationRequest1 = new RegistrationRequest();
        registrationRequest1.setUserCategory("PARENT");
        registrationRequest1.setEmailAddress("emailAddress");
        registrationRequest1.setPassword("password");
        registrationRequest1.setGenderType("FEMALE");
        registrationRequest1.setFirstName("Peters");
        registrationRequest1.setLastName("Henry");

        var testParent = registerService.register(registrationRequest1);
        log.info("I'm the response {}", testParent.getData());

        BookCareTakerRequest bookCareTakerRequest = new BookCareTakerRequest();
        bookCareTakerRequest.setCareTimeDuration(5);
        bookCareTakerRequest.setNumberOfKids(3);
        bookCareTakerRequest.setTimeDuration(String.valueOf(TimeDuration.valueOf("ONE")));
        bookCareTakerRequest.setGenderType("MALE");

       var response = bookCareTakerService.bookCareTaker(registrationRequest1.getEmailAddress(), bookCareTakerRequest );
       assertThat(response.getAmount()).isGreaterThanOrEqualTo(BigDecimal.valueOf(50));

      var response2 = bookSessionService.findAllCareTakerBookedSessionsByUserEmailAddress("careTakerEmailAddress");
        System.out.println(response2);
       assertThat(response2).hasSize(1);
    }

}

