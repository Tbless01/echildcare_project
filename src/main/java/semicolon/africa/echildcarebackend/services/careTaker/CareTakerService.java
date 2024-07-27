package semicolon.africa.echildcarebackend.services.careTaker;

import semicolon.africa.echildcarebackend.data.models.CareTaker;
import semicolon.africa.echildcarebackend.data.models.User;

import java.util.Optional;

public interface CareTakerService {
    CareTaker save(CareTaker careTaker);
    CareTaker getCareTaker(String  genderType);

    CareTaker getSpecialCareTaker(String firstName, String lastName);

     CareTaker findByUserEmailAddress(String emailAddress);

}
