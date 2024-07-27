package semicolon.africa.echildcarebackend.services.checkBookedSessions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.BookedSessions;
import semicolon.africa.echildcarebackend.services.bookSession.BookSessionService;

import java.util.List;


@Service
@AllArgsConstructor
public class CheckBookedSessionsServiceImpl implements CheckCareTakerBookedSessionsService {

    private final BookSessionService bookSessionService;
//    @Override
//    public List<BookedSessions> checkBookedSessions(String careTakerEmailAddress) {
//        return bookSessionService.findAllBookedSessionsByUserEmailAddress(careTakerEmailAddress);
//    }
}
