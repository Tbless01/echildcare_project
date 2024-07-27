package semicolon.africa.echildcarebackend.services.bookSession;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.BookedSessions;
import semicolon.africa.echildcarebackend.data.models.CareTaker;
import semicolon.africa.echildcarebackend.data.repositories.BookedSessionRepository;
import semicolon.africa.echildcarebackend.data.repositories.ParentRepository;
import semicolon.africa.echildcarebackend.dtos.response.BookSessionResponse;
import semicolon.africa.echildcarebackend.services.careTaker.CareTakerService;
import semicolon.africa.echildcarebackend.services.parent.ParentService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookSessionServiceImpl implements BookSessionService{
    private final BookedSessionRepository bookedSessionRepository;
    private final CareTakerService careTakerService;
    private final ParentService parentService;
    private final ParentRepository parentRepository;

    @Override
    public BookedSessions save(BookedSessions bookedSessions) {

        return bookedSessionRepository.save(bookedSessions);
    }
    @Override
    public List<BookedSessions> findAllCareTakerBookedSessionsByUserEmailAddress(String emailAddress) {
        System.out.println("i got here before printing");
        var foundCareTaker = careTakerService.findByUserEmailAddress(emailAddress);
        System.out.println("I'm the printed found caretaker" + foundCareTaker);
        return foundCareTaker.getBookedSessions();
    }


    @Override
    public List<BookedSessions> findAllParentBookedSessionsByUserEmailAddress(String emailAddress) {
       // var foundParent = parentService.findByUserEmailAddress(emailAddress);
        var foundParent = parentRepository.findByUser_EmailAddress(emailAddress);

        if(foundParent.isPresent()){
            log.info("i'm the found parent {}", foundParent);
            return foundParent.get().getBookedSessions();
        }
       return null;
    }
}
