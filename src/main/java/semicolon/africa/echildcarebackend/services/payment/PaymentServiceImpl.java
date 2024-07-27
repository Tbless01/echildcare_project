package semicolon.africa.echildcarebackend.services.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.Payment;
import semicolon.africa.echildcarebackend.data.repositories.PaymentRepository;

@Service
@AllArgsConstructor

public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
