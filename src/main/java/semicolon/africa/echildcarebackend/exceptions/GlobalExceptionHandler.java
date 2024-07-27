package semicolon.africa.echildcarebackend.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> globalExceptionHandler(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

// @ExceptionHandler(UserRegistrationException.class)
//    public ResponseEntity<ApiResponse> userRegistrationExceptionHandler(UserRegistrationException userRegistrationException){
//        ApiResponse apiResponse = ApiResponse.builder()
//                .data(userRegistrationException.getMessage())
//                .statusCode(HttpStatus.BAD_REQUEST.value())
//                .httpStatus(HttpStatus.BAD_REQUEST)
//                .isSuccessful(false)
//                .build();
//        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler(UserLoginException.class)
//    public ResponseEntity<ApiResponse> userLoginExceptionHandler( UserLoginException userLoginException){
//     ApiResponse apiResponse = ApiResponse.builder()
//             .data(userLoginException.getMessage())
//             .httpStatus(HttpStatus.BAD_REQUEST)
//             .statusCode(HttpStatus.BAD_REQUEST.value())
//             .isSuccessful(false)
//             .build();
//     return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//    }
}
