package semicolon.africa.echildcarebackend.services.emailverification;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.services.user.UserService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;
import semicolon.africa.echildcarebackend.utils.GenerateApiResponse;


@Service
@AllArgsConstructor
public class EmailVerificationServiceImpl implements EmailVerificationService {
    private final UserService userService;

    @Override
    public ApiResponse verifyEmailAddress(String emailAddress) {

    var isNotRegistered = userService.findUserByEmailAddress(emailAddress)==null;
    if(isNotRegistered){
        return ApiResponse.builder()
                .isSuccessful(true)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
        return ApiResponse.builder()
                .isSuccessful(false)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .data(GenerateApiResponse.EMAIL_ALREADY_EXISTS)
                .build();
    }
}
