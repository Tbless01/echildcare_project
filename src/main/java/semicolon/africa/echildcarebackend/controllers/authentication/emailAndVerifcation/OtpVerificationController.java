package semicolon.africa.echildcarebackend.controllers.authentication.emailAndVerifcation;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.services.otpMail.OtpConfirmationService;
import semicolon.africa.echildcarebackend.services.verification.TokenVerificationService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/verifyEmail/sendOtp/")
@CrossOrigin(origins = "*")
public class OtpVerificationController {

    private final OtpConfirmationService otpConfirmationService;

    @PostMapping("confirmOtp")
    public ResponseEntity<ApiResponse> confirmOtp( @RequestParam String otp){
        return new ResponseEntity<>(otpConfirmationService.confirmOtp(otp), HttpStatus.OK);
    }
}
