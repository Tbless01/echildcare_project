package semicolon.africa.echildcarebackend.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import semicolon.africa.echildcarebackend.data.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
