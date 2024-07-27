package semicolon.africa.echildcarebackend.services.debitCard;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.DebitCardDetails;
import semicolon.africa.echildcarebackend.data.repositories.DebitCardDetailsRepository;



@Service
@AllArgsConstructor
public class DebitCardServiceImpl implements DebitCardService{
    private final DebitCardDetailsRepository debitCardDetailsRepository;

    @Override
    public DebitCardDetails save(DebitCardDetails debitCardDetails) {
        return debitCardDetailsRepository.save(debitCardDetails);
    }

    @Override
    public DebitCardDetails findDebitCard(DebitCardDetails debitCardDetails) {
        return debitCardDetailsRepository.findByDebitCardNumber(debitCardDetails.getDebitCardNumber());
    }

    @Override
    public DebitCardDetails findByUserEmailAddress(String emailAddress) {
        return debitCardDetailsRepository.findByUserEmail(emailAddress);
    }
}
