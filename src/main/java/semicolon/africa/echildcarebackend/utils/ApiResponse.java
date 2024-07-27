package semicolon.africa.echildcarebackend.utils;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
public class ApiResponse {
    private Object data;
    private HttpStatus httpStatus;
    private int statusCode;
    private boolean isSuccessful;
}
