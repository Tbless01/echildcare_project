package semicolon.africa.echildcarebackend.data.models.payStack;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import semicolon.africa.echildcarebackend.dtos.request.MakePaymentRequest;
import semicolon.africa.echildcarebackend.dtos.response.MakePaymentResponse;
import semicolon.africa.echildcarebackend.exceptions.PaystackApiException;

@AllArgsConstructor

public class PaystackApiClient {
    private final RestTemplate restTemplate;
    private final PayStackConfiguration paystackConfiguration;

//    public PaystackApiClient(RestTemplate restTemplate, PayStackConfiguration paystackConfiguration) {
//        this.restTemplate = restTemplate;
//        this.paystackConfiguration = paystackConfiguration;
//    }

    public MakePaymentResponse chargeCard(MakePaymentRequest paymentRequest) throws PaystackApiException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(paystackConfiguration.getApiKey());

            HttpEntity<MakePaymentRequest> requestEntity = new HttpEntity<>(paymentRequest, headers);
            ResponseEntity<MakePaymentResponse> responseEntity = restTemplate.exchange(
                    "https://api.paystack.co/charge",
                    HttpMethod.POST,
                    requestEntity,
                    MakePaymentResponse.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                throw new PaystackApiException("Failed to process payment. Status code: " + responseEntity.getStatusCode());
            }
        } catch (HttpClientErrorException.UnprocessableEntity error) {
            throw new PaystackApiException("Payment validation failed: " + error.getMessage());
        } catch (Exception error) {
            throw new PaystackApiException("An error occurred during payment processing.");
        }
    }
}
