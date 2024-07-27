package semicolon.africa.echildcarebackend.services.bookedCareTaker;

import semicolon.africa.echildcarebackend.data.models.BookedSessions;
import semicolon.africa.echildcarebackend.dtos.request.BookCareTakerRequest;
import semicolon.africa.echildcarebackend.dtos.response.BookSessionResponse;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

public interface BookCareTakerService {
    BookSessionResponse bookCareTaker(String parentEmailAddress, BookCareTakerRequest bookCareTakerRequest);
}
