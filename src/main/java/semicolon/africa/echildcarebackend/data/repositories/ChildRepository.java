package semicolon.africa.echildcarebackend.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semicolon.africa.echildcarebackend.data.models.Child;
import semicolon.africa.echildcarebackend.data.models.enumClasses.SpecialNeedCategory;

public interface ChildRepository extends JpaRepository<Child, Long> {

    Child findChildByFirstNameAndDateOfBirthAndSpecialNeedCategoryAndLastName
            (String firstname, String dateOfBirth, SpecialNeedCategory specialNeedCategory,
             String lastname);


}
