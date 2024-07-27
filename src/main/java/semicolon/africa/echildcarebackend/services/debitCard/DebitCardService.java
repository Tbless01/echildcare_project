package semicolon.africa.echildcarebackend.services.debitCard;

import semicolon.africa.echildcarebackend.data.models.DebitCardDetails;
import semicolon.africa.echildcarebackend.data.models.Parent;

public interface DebitCardService {

    DebitCardDetails save(DebitCardDetails debitCardDetails);
    DebitCardDetails findDebitCard (DebitCardDetails debitCardDetails);

    DebitCardDetails findByUserEmailAddress(String emailAddress);
}
