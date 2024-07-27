package semicolon.africa.echildcarebackend.controllers.authentication;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.dtos.request.LoginRequest;
import semicolon.africa.echildcarebackend.services.authentication.LoginService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth/")
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService loginService;

    @SneakyThrows
    @PostMapping("login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(loginService.login(loginRequest), HttpStatus.OK);
    }
}
