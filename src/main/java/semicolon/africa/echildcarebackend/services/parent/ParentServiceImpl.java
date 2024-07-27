package semicolon.africa.echildcarebackend.services.parent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.Child;
import semicolon.africa.echildcarebackend.data.models.DebitCardDetails;
import semicolon.africa.echildcarebackend.data.models.Parent;
import semicolon.africa.echildcarebackend.data.models.User;
import semicolon.africa.echildcarebackend.data.models.enumClasses.GenderType;
import semicolon.africa.echildcarebackend.data.models.payStack.PaystackApiClient;
import semicolon.africa.echildcarebackend.data.repositories.ParentRepository;
import semicolon.africa.echildcarebackend.dtos.request.AddCardDetailsRequest;
import semicolon.africa.echildcarebackend.dtos.request.AddChildRequest;
import semicolon.africa.echildcarebackend.dtos.request.EditProfileRequest;
import semicolon.africa.echildcarebackend.dtos.request.MakePaymentRequest;
import semicolon.africa.echildcarebackend.dtos.response.AddCardDetailsResponse;
import semicolon.africa.echildcarebackend.dtos.response.MakePaymentResponse;
import semicolon.africa.echildcarebackend.exceptions.AddChildException;
import semicolon.africa.echildcarebackend.exceptions.AddDebitCardException;
import semicolon.africa.echildcarebackend.exceptions.ChildCareException;
import semicolon.africa.echildcarebackend.exceptions.PaystackApiException;
import semicolon.africa.echildcarebackend.services.child.ChildService;
import semicolon.africa.echildcarebackend.services.debitCard.DebitCardService;
import semicolon.africa.echildcarebackend.services.parent.helperClasses.CardValidation;
import semicolon.africa.echildcarebackend.services.user.UserService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;
import semicolon.africa.echildcarebackend.utils.GenerateApiResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static semicolon.africa.echildcarebackend.services.parent.helperClasses.BuildResponse.buildAddCardResponse;
import static semicolon.africa.echildcarebackend.services.parent.helperClasses.BuildResponse.buildFailedToAddCardResponse;
import static semicolon.africa.echildcarebackend.utils.ExceptionUtils.*;


@Service
@AllArgsConstructor
@Slf4j
public class ParentServiceImpl implements ParentService {


    private final ModelMapper modelMapper;
    private final ParentRepository parentRepository;
    private final DebitCardService debitCardService;

    private final ChildService childService;

    private final UserService userService;

    private final PaystackApiClient paystackApiClient;


    @Override
    public Parent save(Parent parent) {
        return parentRepository.save(parent);
    }

    //    @Override
//    public AddChildResponse add(String parentEmailAddress, AddChildRequest addChildRequest) throws ChildCareException {
//        Child child = modelMapper.map(addChildRequest, Child.class);
//        var foundParent  = parentRepository.findByUserEmailAddress(parentEmailAddress);
//
//        if(foundParent.isPresent()) {
//            var listOfChild = foundParent.get().getChild();
//            boolean isPresent = listOfChild.contains(child);
//
//            if (isPresent) {
//                throw new ChildCareException(FAILED_TO_ADD_CHILD);
//            }
//
//            listOfChild.add(child);
//            foundParent.get().setChild(new ArrayList<>(listOfChild));
//            Parent savedParent = parentRepository.save(foundParent.get());
//            return buildAddChildResponse(savedParent.getId());
//        }
//        return buildFailedToAddChildResponse(parentEmailAddress);
//
//    }
    @Override
    public ApiResponse add(String parentEmailAddress, AddChildRequest addChildRequest) throws ChildCareException {
        Child child = modelMapper.map(addChildRequest, Child.class);
        var foundParent = parentRepository.findByUser_EmailAddress(parentEmailAddress);

        if (foundParent.isPresent()) {
            var childrenSet = foundParent.get().getChild();
            var foundChild = childService.findChild(child);
            boolean isPresent = childrenSet.contains(child);

            if (foundChild != null) {
                throw new AddChildException(FAILED_TO_ADD_CHILD);
            }

            childrenSet.add(child);
            foundParent.get().setChild(new HashSet<>(childrenSet));
            Parent savedParent = parentRepository.save(foundParent.get());
            return GenerateApiResponse.createdResponse(GenerateApiResponse.CHILD_ADDED_SUCCESSFULLY);
        }
        return GenerateApiResponse.BadResponse(GenerateApiResponse.UNABLE_TO_ADD_CHILD);
    }

    @Override
    public MakePaymentResponse makePayment(String parentEmail, MakePaymentRequest makePaymentRequest) throws PaystackApiException {

        DebitCardDetails foundDebitCard = debitCardService.findByUserEmailAddress(parentEmail);

        MakePaymentRequest paymentRequest = new MakePaymentRequest();
        paymentRequest.setCreditCardNumber(foundDebitCard.getDebitCardNumber());
        paymentRequest.setExpiringMonth(foundDebitCard.getExpiringMonth());
        paymentRequest.setExpiringYear(foundDebitCard.getExpiringYear());
        paymentRequest.setCvv(foundDebitCard.getCvv());
        paymentRequest.setUserEmail(parentEmail);
        paymentRequest.setAmount(BigDecimal.valueOf(100.0));

        return paystackApiClient.chargeCard(paymentRequest);
    }


    @Override
    public AddCardDetailsResponse addDebitCard(String parentEmailAddress, AddCardDetailsRequest addCardDetailsRequest) throws ChildCareException {
        DebitCardDetails debitCard = modelMapper.map(addCardDetailsRequest, DebitCardDetails.class);
        debitCard.setUserEmail(parentEmailAddress);
        var foundParent = parentRepository.findByUser_EmailAddress(parentEmailAddress);

        if (foundParent.isPresent()) {
            var listOfDebitCards = foundParent.get().getDebitCardDetails();
            var foundCard = debitCardService.findDebitCard(debitCard);
            boolean isPresent = listOfDebitCards.contains(debitCard);

            if (foundCard != null) {
                throw new AddDebitCardException(FAILED_TO_ADD_CARD);
            }

            boolean validationResult = CardValidation.isDebitCardValid(debitCard.getDebitCardNumber());

            if (!validationResult) {
                throw new AddDebitCardException(INVALID_CARD);
            }
            debitCardService.save(debitCard);

            listOfDebitCards.add(debitCard);

            foundParent.get().setDebitCardDetails(new ArrayList<>(listOfDebitCards));
            Parent savedParent = parentRepository.save(foundParent.get());
            return buildAddCardResponse(savedParent.getId());
        }
        return buildFailedToAddCardResponse(parentEmailAddress);
    }

    @Override
    public Parent findByUserEmailAddress(String parentEmailAddress) {
        for (var each : parentRepository.findAll()) {
            System.out.println(each.getUser().getEmailAddress());
        }
        Optional<Parent> foundParent = parentRepository.findByUser_EmailAddress(parentEmailAddress);
        foundParent.ifPresent(parent -> log.info("I'm the parent from repository {}", parent));
        return foundParent.orElse(null);
    }

    @Override
    public ApiResponse updateProfile(String emailAddress, EditProfileRequest editProfileRequest) {
        var foundUser = userService.findUserByEmailAddress(emailAddress);

        if (foundUser != null) {
            foundUser.setFirstName(editProfileRequest.getFirstName());
            foundUser.setLastName(editProfileRequest.getLastName());
            foundUser.setPhoneNumber(editProfileRequest.getPhoneNumber());
            userService.save(foundUser);
            return GenerateApiResponse.okResponse(GenerateApiResponse.PROFILE_UPDATED_SUCCESSFULLY);
        }
        return GenerateApiResponse.BadResponse(GenerateApiResponse.UPDATE_FAILED);
    }


//    @Override
//       @Transactional
//        public PaymentVerificationResponse paymentVerification(String orderId, String senderId) throws ChildCareException  {
//            PaymentVerificationResponse paymentVerificationResponse = null;
//            Payment payment= null;
//            if (orderId.isEmpty() || senderId.isEmpty()) {
//                throw new ChildCareException("reference and id must be provided");
//            }
//            try {
//                HttpClient client = HttpClientBuilder.create().build();
//                HttpGet request = new HttpGet(PAYSTACK_VERIFY + orderId);
//                request.addHeader("Content-type", "application/json");
//                //   request.addHeader("Authorization");
//                StringBuilder result = new StringBuilder();
//                HttpResponse response = client.execute(request);
//
//                if (response.getStatusLine().getStatusCode() == STATUS_CODE_OK) {
//                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//                    String line;
//
//                    while ((line = rd.readLine()) != null) {
//                        result.append(line);
//                    }
//                } else {
//                    throw new ChildCareException("Paystack is unable to verify payment at the moment");
//                }
//                ObjectMapper mapper = new ObjectMapper();
//
//                paymentVerificationResponse = mapper.readValue(result.toString(), PaymentVerificationResponse.class);
//
//                if (paymentVerificationResponse == null || !paymentVerificationResponse.isSuccess()) {
//                    throw new ChildCareException("An error occurred");
//                } else if (paymentVerificationResponse.getData().getStatus().equals("success")) {
//                    payment=  Payment.builder()
//                            .channel(paymentVerificationResponse.getData().getChannel())
//                            .senderId(senderId)
//                            .amount(paymentVerificationResponse.getData().getAmount())
//                            .orderId(orderId)
//                            .paidAt(paymentVerificationResponse.getData().getPaidAt())
//                            .orderStatus(Status.CONFIRMED)
//                            .createdAt(LocalDateTime.now())
//                            .build();
//                }
//
//            }catch(ChildCareException | IOException e){
//                throw new RuntimeException("Error Paystack");
//
//            }
//            if (payment != null) {
//                paymentService.save(payment);
//            }
//            return paymentVerificationResponse;
//        }}

//    @Service
//    @AllArgsConstructor
//    public class SpedirePaymentService implements PaymentService {
//        private final PaymentRepository paymentRepository;
//
//        //  @Value("${paystack.secret.key}")
//        // private String paystackSecretKey;
//
//        @Override
//        @Transactional
//        public PaymentVerificationResponse paymentVerification(String orderId, String senderId) throws SpedireException {
//            PaymentVerificationResponse paymentVerificationResponse = null;
//            Payment payment= null;
//            if (orderId.isEmpty() || senderId.isEmpty()) {
//                throw new SpedireException("reference and id must be provided");
//            }
//            try {
//                HttpClient client = HttpClientBuilder.create().build();
//                HttpGet request = new HttpGet(PAYSTACK_VERIFY + orderId);
//                request.addHeader("Content-type", "application/json");
//                //   request.addHeader("Authorization");
//                StringBuilder result = new StringBuilder();
//                HttpResponse response = client.execute(request);
//
//                if (response.getStatusLine().getStatusCode() == STATUS_CODE_OK) {
//                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//                    String line;
//
//                    while ((line = rd.readLine()) != null) {
//                        result.append(line);
//                    }
//                } else {
//                    throw new SpedireException("Paystack is unable to verify payment at the moment");
//                }
//                ObjectMapper mapper = new ObjectMapper();
//
//                paymentVerificationResponse = mapper.readValue(result.toString(), PaymentVerificationResponse.class);
//
//                if (paymentVerificationResponse == null || !paymentVerificationResponse.isSuccess()) {
//                    throw new SpedireException("An error occurred");
//                } else if (paymentVerificationResponse.getData().getStatus().equals("success")) {
//                    payment=  Payment.builder()
//                            .channel(paymentVerificationResponse.getData().getChannel())
//                            .senderId(senderId)
//                            .amount(paymentVerificationResponse.getData().getAmount())
//                            .orderId(orderId)
//                            .paidAt(paymentVerificationResponse.getData().getPaidAt())
//                            .orderStatus(STATUS.COMPLETED)
//                            .createdAt(LocalDateTime.now())
//                            .build();
//                }
//
//            }catch(SpedireException | IOException e){
//                throw new RuntimeException("Error Paystack");
//
//            }
//            if (payment != null) {
//                paymentRepository.save(payment);
//            }
//            return paymentVerificationResponse;
//        }}
}
