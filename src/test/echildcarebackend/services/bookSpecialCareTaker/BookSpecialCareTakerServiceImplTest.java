package semicolon.africa.echildcarebackend.services.bookSpecialCareTaker;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.echildcarebackend.data.models.enumClasses.TimeDuration;
import semicolon.africa.echildcarebackend.dtos.request.BookSpecialCareTakeRequest;
import semicolon.africa.echildcarebackend.dtos.request.RegistrationRequest;
import semicolon.africa.echildcarebackend.exceptions.UserRegistrationException;
import semicolon.africa.echildcarebackend.services.authentication.RegisterService;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Slf4j
class BookSpecialCareTakerServiceImplTest {
    @Autowired
    private BookSpecialCareTakerService bookSpecialCareTakerService;
    @Autowired
    private RegisterService registerService;


    @Test
    void testThatAParentCanBookASpecialCareTaker() throws UserRegistrationException {

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUserCategory("PARENT");
        registrationRequest.setEmailAddress("emailAddress11");
        registrationRequest.setPassword("password");
        registrationRequest.setGenderType("MALE");
        registrationRequest.setFirstName("first name");
        registrationRequest.setFirstName("last name");

        registerService.register(registrationRequest);


        RegistrationRequest registrationRequest1 = new RegistrationRequest();
        registrationRequest1.setUserCategory("CARETAKER");
        registrationRequest1.setEmailAddress("emailAddress9");
        registrationRequest1.setPassword("password12");
        registrationRequest1.setGenderType("MALE");
        registrationRequest1.setFirstName("Tayo");
        registrationRequest1.setLastName("Omo");
        registerService.register(registrationRequest1);


        BookSpecialCareTakeRequest bookSpecialCareTakeRequest = new BookSpecialCareTakeRequest();
        bookSpecialCareTakeRequest.setFirstName("tayo");
        bookSpecialCareTakeRequest.setLastName("omo");
        bookSpecialCareTakeRequest.setTimeDuration("ONE");
        bookSpecialCareTakeRequest.setNumberOfKids(3);
        bookSpecialCareTakeRequest.setCareTimeDuration(4);

        var response =  bookSpecialCareTakerService.bookSpecialCareTaker("emailAddress11", bookSpecialCareTakeRequest);
        log.info(response.getMessage());
        assertThat(response.getMessage()).isNotNull();
    }
}
