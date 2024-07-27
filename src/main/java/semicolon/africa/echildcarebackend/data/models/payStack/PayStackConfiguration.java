package semicolon.africa.echildcarebackend.data.models.payStack;

import lombok.*;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component

public class PayStackConfiguration {

//    @Value("${paystack.api.key}")
    private String apiKey = "sk_test_24524a4eb3ca5947d0185b679cf7cbdba455acf4";

}