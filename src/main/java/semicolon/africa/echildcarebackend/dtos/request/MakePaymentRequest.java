package semicolon.africa.echildcarebackend.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MakePaymentRequest {

    private String creditCardNumber;
    private String  expiringMonth;
    private String expiringYear;
    private String cvv;
    private String userEmail;
    private BigDecimal amount;

}
