
package semicolon.africa.echildcarebackend.controllers.parentController;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.dtos.request.AddCardDetailsRequest;
import semicolon.africa.echildcarebackend.dtos.request.AddChildRequest;
import semicolon.africa.echildcarebackend.dtos.request.EditProfileRequest;
import semicolon.africa.echildcarebackend.dtos.request.MakePaymentRequest;
import semicolon.africa.echildcarebackend.dtos.response.AddCardDetailsResponse;
import semicolon.africa.echildcarebackend.dtos.response.MakePaymentResponse;
import semicolon.africa.echildcarebackend.exceptions.PaystackApiException;
import semicolon.africa.echildcarebackend.services.parent.ParentService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/parent/")
@CrossOrigin(origins = "*")
public class ParentController {
    private final ParentService parentService;

    @SneakyThrows
    @PostMapping("addChild")
    public ResponseEntity<ApiResponse> addChild(@RequestParam String parentEmail, @RequestBody AddChildRequest addChildRequest) {
        return new ResponseEntity<>(parentService.add(parentEmail, addChildRequest), HttpStatus.OK);
    }

    @SneakyThrows
    @PostMapping("addCardDetails")
    public ResponseEntity<AddCardDetailsResponse> addCardDetails(@RequestParam String parentEmail, AddCardDetailsRequest addCardDetailsRequest) {
        return new ResponseEntity<>(parentService.addDebitCard(parentEmail, addCardDetailsRequest), HttpStatus.OK);
    }

    @PostMapping("makePayment")
    public ResponseEntity<MakePaymentResponse> makePayment(@RequestBody String parentEmail, MakePaymentRequest paymentRequest) {
        try {
            MakePaymentResponse paymentResponse = parentService.makePayment(parentEmail, paymentRequest);
            return ResponseEntity.ok(paymentResponse);
        } catch (PaystackApiException error) {
            throw new RuntimeException(error);
        }
    }

    @PostMapping("updateProfile")
    public ResponseEntity<ApiResponse> updateProfile(@RequestParam String emailAddress, @RequestBody EditProfileRequest editProfileRequest)  {
        return new ResponseEntity<>(parentService.updateProfile(emailAddress, editProfileRequest), HttpStatus.OK);
    }
}
