package semicolon.africa.echildcarebackend.services.bookSession;

import semicolon.africa.echildcarebackend.data.models.BookedSessions;
import semicolon.africa.echildcarebackend.dtos.response.BookSessionResponse;

import java.util.List;

public interface BookSessionService {
    BookedSessions save(BookedSessions bookedSessions);

   List<BookedSessions> findAllCareTakerBookedSessionsByUserEmailAddress(String emailAddress);

   List<BookedSessions> findAllParentBookedSessionsByUserEmailAddress(String emailAddress);


}
