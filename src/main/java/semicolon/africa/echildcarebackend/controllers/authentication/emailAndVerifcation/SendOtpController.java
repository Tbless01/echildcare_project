package semicolon.africa.echildcarebackend.controllers.authentication.emailAndVerifcation;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.services.otpMail.SendOtpService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/verifyEmail/")
@CrossOrigin(origins = "*")
public class SendOtpController {

    private final SendOtpService sendOtpService;

    @PostMapping ("sendOtp")
    public ResponseEntity<ApiResponse> sendOtp(@RequestParam String emailAddress){
        return new ResponseEntity<>(sendOtpService.sendOtp(emailAddress), HttpStatus.OK);
    }
}
