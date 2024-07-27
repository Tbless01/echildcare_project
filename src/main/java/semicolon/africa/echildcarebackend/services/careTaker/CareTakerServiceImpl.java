package semicolon.africa.echildcarebackend.services.careTaker;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import semicolon.africa.echildcarebackend.data.models.enumClasses.BookedStatus;
import semicolon.africa.echildcarebackend.data.models.CareTaker;
import semicolon.africa.echildcarebackend.data.models.enumClasses.GenderType;
import semicolon.africa.echildcarebackend.data.repositories.CareTakerRepository;





@Service
@AllArgsConstructor
public class CareTakerServiceImpl implements CareTakerService{

    private final CareTakerRepository careTakerRepository;

    @Override
    public CareTaker save(CareTaker careTaker) {
        return careTakerRepository.save(careTaker);
    }

    @Override
    public CareTaker getCareTaker(String genderType) {
        var foundCareTaker =
                careTakerRepository.findByUserGenderTypeAndBookedStatus(GenderType.valueOf(genderType), BookedStatus.NOT_BOOKED);

        if (foundCareTaker.listIterator().hasNext()){
            return foundCareTaker.listIterator().next();
        } else{
            return null;
        }
    }

    @Override
    public CareTaker getSpecialCareTaker(String firstName, String lastName) {
        System.out.println("I entered here");
        var found =  careTakerRepository.findByUserFirstNameAndUserIgnoreCase_LastNameAndBookedStatus(firstName, lastName, BookedStatus.NOT_BOOKED);
        System.out.println("i'm the found" + found);
        return found.orElse(null);
    }

    @Override
    public CareTaker findByUserEmailAddress(String emailAddress){
        System.out.println("i got here sha");
       var result =  careTakerRepository.findCareTakersByUser_EmailAddress(emailAddress);
       return result.orElse(null);
    }
}
