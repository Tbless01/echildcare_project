package semicolon.africa.echildcarebackend.services.parent;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.echildcarebackend.data.models.Parent;
import semicolon.africa.echildcarebackend.data.models.enumClasses.GenderType;
import semicolon.africa.echildcarebackend.data.models.enumClasses.SpecialNeedCategory;
import semicolon.africa.echildcarebackend.dtos.request.AddCardDetailsRequest;
import semicolon.africa.echildcarebackend.dtos.request.AddChildRequest;
import semicolon.africa.echildcarebackend.dtos.request.EditProfileRequest;
import semicolon.africa.echildcarebackend.dtos.request.MakePaymentRequest;
import semicolon.africa.echildcarebackend.dtos.response.AddCardDetailsResponse;
import semicolon.africa.echildcarebackend.dtos.response.AddChildResponse;
import semicolon.africa.echildcarebackend.dtos.response.MakePaymentResponse;
import semicolon.africa.echildcarebackend.exceptions.ChildCareException;
import semicolon.africa.echildcarebackend.exceptions.PaystackApiException;
import semicolon.africa.echildcarebackend.exceptions.UserRegistrationException;
import semicolon.africa.echildcarebackend.services.authentication.RegisterService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;


import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class ParentServiceImplTest {

        @Autowired
        private ParentService parentService;

        @Autowired
        private RegisterService registerService;


    @Test

        public void testThatAChildCanBeAdded() throws ChildCareException {

//        RegistrationRequest registrationRequest = new RegistrationRequest();
//        registrationRequest.setUserCategory("PARENT");
//        registrationRequest.setEmailAddress("emailAddress");
//        registrationRequest.setPassword("password");
//        registrationRequest.setGenderType("MALE");
//        registrationRequest.setLastName("a");
//        registrationRequest.setFirstName("b");
//
//        registerService.register(registrationRequest);
//        Parent parent = parentService.findByUserEmailAddress(registrationRequest
//                .getEmailAddress());
        Parent parent = parentService.findByUserEmailAddress("emailAddress");
            AddChildRequest addChildRequest = new AddChildRequest();
            addChildRequest.setFirstName("John");
            addChildRequest.setLastName("Doe");
            addChildRequest.setDateOfBirth("2000-01-01");
            addChildRequest.setSpecialNeedCategory(SpecialNeedCategory.NO);
            addChildRequest.setSpecialNeedDescription("N/A");
            ApiResponse addChildResponse = parentService.add(parent.getUser()
                .getEmailAddress(), addChildRequest);

        assertThat(addChildResponse).isNotNull();

        }

@Test
    public void testThatACardCanBeAdded() throws ChildCareException{

        AddCardDetailsRequest addCardDetailsRequest = new AddCardDetailsRequest();
        addCardDetailsRequest.setDebitCardNumber("5399839001607398");
        addCardDetailsRequest.setExpiringMonth("11");
        addCardDetailsRequest.setCvv("123");
        addCardDetailsRequest.setExpiringYear("25");
        addCardDetailsRequest.setEmailAddress("omoseebiomotayo@gmail.com");

        AddCardDetailsResponse addCardDetailsResponse = parentService.addDebitCard("omoseebiomotayo@gmail.com", addCardDetailsRequest);

        assertThat(addCardDetailsResponse).isNotNull();

    }

//    @Test
//    public void testThatAUserCanMakePayment() throws PaystackApiException {
//
//        MakePaymentRequest makePaymentRequest = new MakePaymentRequest();
//
//        makePaymentRequest.setUserEmail("omoseebiomotayo@gmail.com");
//        makePaymentRequest.setCreditCardNumber("5399839001607398");
//        makePaymentRequest.setAmount(BigDecimal.valueOf(1000));
//        makePaymentRequest.setCvv("457");
//        makePaymentRequest.setExpiringMonth("11");
//        makePaymentRequest.setExpiringYear("26");
//
//        MakePaymentResponse makePaymentResponse = parentService.makePayment
//                ("omoseebiomotayo@gmail.com", makePaymentRequest);
//
//        assertThat(makePaymentResponse).isNotNull();
//
//    }

    @Test
    public void testThatUserCanUpdateProfile() {
        EditProfileRequest editProfileRequest = new EditProfileRequest();

        editProfileRequest.setPhoneNumber("12345");
        editProfileRequest.setLastName("omo");
        editProfileRequest.setFirstName("tayo");

        var response = parentService.updateProfile(
                "omoseebiomotayo@gmail.com", editProfileRequest);
        log.info("I'm the response {}", response.getData());
        assertThat(response).isNotNull();

    }
}

