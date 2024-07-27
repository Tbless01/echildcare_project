package semicolon.africa.echildcarebackend.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semicolon.africa.echildcarebackend.data.models.DebitCardDetails;
import semicolon.africa.echildcarebackend.data.models.Parent;

public interface DebitCardDetailsRepository extends JpaRepository<DebitCardDetails, Long> {

    DebitCardDetails findByDebitCardNumber(String debitCardNumber);

    DebitCardDetails findByUserEmail(String userEmailAddress);
}
