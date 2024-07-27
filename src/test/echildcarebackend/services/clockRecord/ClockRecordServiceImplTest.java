package semicolon.africa.echildcarebackend.services.clockRecord;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.echildcarebackend.dtos.request.RegistrationRequest;

import semicolon.africa.echildcarebackend.exceptions.ClockException;
import semicolon.africa.echildcarebackend.exceptions.UserRegistrationException;
import semicolon.africa.echildcarebackend.services.authentication.RegisterService;
import semicolon.africa.echildcarebackend.services.careTaker.CareTakerService;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Slf4j
class ClockRecordServiceImplTest {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private ClockRecordService clockRecordService;
    @Autowired
    private CareTakerService careTakerService;

    @Test
    void testThatACareTakerCanClockIn() throws UserRegistrationException {

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmailAddress("omoseebiomotayo@gmail.com");
        registrationRequest.setFirstName("firstName");
        registrationRequest.setLastName("lastName");
        registrationRequest.setPassword("password");
        registrationRequest.setGenderType("MALE");
        registrationRequest.setUserCategory("CARETAKER");

        registerService.register(registrationRequest);

      // var response = clockRecordService.clockIn(registrationRequest.getEmailAddress());
       // log.info((String) response.getData());
       // assertThat(response.isSuccessful()).isTrue();
    }

    @Test
    void testThatACareTakerCanClockOut() throws UserRegistrationException, ClockException {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmailAddress("emailAddress");
        registrationRequest.setFirstName("firstName");
        registrationRequest.setLastName("lastName");
        registrationRequest.setPassword("password");
        registrationRequest.setGenderType("MALE");
        registrationRequest.setUserCategory("CARETAKER");

        registerService.register(registrationRequest);

        var response = clockRecordService.clockIn(registrationRequest.getEmailAddress());
        log.info((String) response.getData());

        var clockOut = clockRecordService.clockOut((registrationRequest.getEmailAddress()));
        log.info((String)clockOut.getData());

        assertThat(clockOut.isSuccessful()).isTrue();
    }

    @Test
    void testThatACareTakerCanViewWorkHistory() throws UserRegistrationException {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmailAddress("emailAddress");
        registrationRequest.setFirstName("firstName");
        registrationRequest.setLastName("lastName");
        registrationRequest.setPassword("password");
        registrationRequest.setGenderType("MALE");
        registrationRequest.setUserCategory("CARETAKER");

         registerService.register(registrationRequest);

        var response = clockRecordService.clockIn(registrationRequest.getEmailAddress());
        log.info((String) response.getData());

        var clockOut = clockRecordService.clockOut((registrationRequest.getEmailAddress()));

         var foundCareTaker = careTakerService.findByUserEmailAddress("emailAddress");
        assertThat(foundCareTaker.getClockRecordList()).hasSizeLessThanOrEqualTo(1);

    }
}