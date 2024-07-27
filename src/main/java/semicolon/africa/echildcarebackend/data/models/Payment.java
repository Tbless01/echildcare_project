package semicolon.africa.echildcarebackend.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.PaymentMethod;
import semicolon.africa.echildcarebackend.data.models.enumClasses.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    private String id;
    private String orderId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String senderId;
    private Status orderStatus;
    private String paidAt;
    private LocalDateTime createdAt;
    private String channel;
}
