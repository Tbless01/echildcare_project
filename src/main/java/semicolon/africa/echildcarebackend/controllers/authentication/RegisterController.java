package semicolon.africa.echildcarebackend.controllers.authentication;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.dtos.request.RegistrationRequest;
import semicolon.africa.echildcarebackend.services.authentication.RegisterService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth/")
@CrossOrigin(origins = "*")

public class RegisterController {
    private final RegisterService registerService;

    @SneakyThrows
    @PostMapping("register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest registrationRequest) {
        return new ResponseEntity<>(registerService.register(registrationRequest), HttpStatus.CREATED);
    }
}
