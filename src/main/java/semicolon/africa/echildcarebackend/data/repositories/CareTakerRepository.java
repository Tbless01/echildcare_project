package semicolon.africa.echildcarebackend.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semicolon.africa.echildcarebackend.data.models.enumClasses.BookedStatus;
import semicolon.africa.echildcarebackend.data.models.CareTaker;
import semicolon.africa.echildcarebackend.data.models.enumClasses.GenderType;

import java.util.List;
import java.util.Optional;

public interface CareTakerRepository extends JpaRepository<CareTaker, Long> {

    List<CareTaker> findByUserGenderTypeAndBookedStatus(GenderType genderType, BookedStatus bookedStatus);

    Optional<CareTaker> findByUserFirstNameAndUserIgnoreCase_LastNameAndBookedStatus(String firstName, String lastName, BookedStatus bookedStatus);

    Optional<CareTaker> findCareTakersByUser_EmailAddress(String emailAddress);
}
