package semicolon.africa.echildcarebackend.services.bookedCareTaker;

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

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;



@Slf4j
@SpringBootTest
class BookCareTakerServiceImplTest {

    @Autowired
    private BookCareTakerService bookCareTakerService;
    @Autowired
    private BookSessionService bookSessionService;
    @Autowired
    private RegisterService registerService;


    @Test
    void testThatAParentCanBookACareTaker() throws UserRegistrationException {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUserCategory("PARENT");
        registrationRequest.setEmailAddress("emailAddress");
        registrationRequest.setPassword("password");
        registrationRequest.setGenderType("FEMALE");

        registerService.register(registrationRequest);


        RegistrationRequest registrationRequest1 = new RegistrationRequest();
        registrationRequest1.setUserCategory("CARETAKER");
        registrationRequest1.setEmailAddress("emailAddress2");
        registrationRequest1.setPassword("password");
        registrationRequest1.setGenderType("MALE");
        registrationRequest1.setLastName("lastName2");
        registrationRequest1.setFirstName("firstName2");
        registerService.register(registrationRequest1);



        RegistrationRequest registrationRequest2 = new RegistrationRequest();
        registrationRequest2.setUserCategory("CARETAKER");
        registrationRequest2.setEmailAddress("emailAddress3");
        registrationRequest2.setPassword("password");
        registrationRequest2.setGenderType("MALE");
        registrationRequest2.setFirstName("firstName");
        registrationRequest2.setLastName("lastName");

      registerService.register(registrationRequest2);


        RegistrationRequest registrationRequest3 = new RegistrationRequest();
        registrationRequest3.setUserCategory("CARETAKER");
        registrationRequest3.setEmailAddress("emailAddress4");
        registrationRequest3.setPassword("password");
        registrationRequest3.setGenderType("MALE");

      // registerService.register(registrationRequest3);

        BookCareTakerRequest bookCareTakerRequest = new BookCareTakerRequest();
        bookCareTakerRequest.setGenderType("MALE");
        bookCareTakerRequest.setTimeDuration("ONE");
        bookCareTakerRequest.setNumberOfKids(3);
        bookCareTakerRequest.setCareTimeDuration(4);

      // var response =  bookCareTakerService.bookCareTaker( "emailAddress", bookCareTakerRequest);
//      log.info((String) response.getData());
//     assertThat(response.isSuccessful()).isTrue();
     // log.info(String.valueOf(response));
     // assertThat(response.getAmount()).isGreaterThanOrEqualTo(BigDecimal.valueOf(50));
    }
}