package semicolon.africa.echildcarebackend.services.child;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.Child;
import semicolon.africa.echildcarebackend.data.repositories.ChildRepository;

@Service
@AllArgsConstructor

public class ChildServiceImpl implements ChildService{
    private final ChildRepository childRepository;

    @Override
    public Child findChild(Child child) {
        return childRepository.
                findChildByFirstNameAndDateOfBirthAndSpecialNeedCategoryAndLastName(
                        child.getFirstName(), child.getDateOfBirth(), child.getSpecialNeedCategory(),
                        child.getLastName());
    }
}
