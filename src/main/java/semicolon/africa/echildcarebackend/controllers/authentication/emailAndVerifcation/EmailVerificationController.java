package semicolon.africa.echildcarebackend.controllers.authentication.emailAndVerifcation;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.services.emailverification.EmailVerificationService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;

    @PostMapping("verifyEmail")
    public ResponseEntity<ApiResponse> verifyEmailAddress(@RequestParam String emailAddress){
        return new ResponseEntity<>(emailVerificationService.verifyEmailAddress(emailAddress), HttpStatus.OK);
    }
}
