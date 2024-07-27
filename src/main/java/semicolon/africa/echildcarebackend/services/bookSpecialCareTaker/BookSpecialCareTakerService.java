package semicolon.africa.echildcarebackend.services.bookSpecialCareTaker;

import semicolon.africa.echildcarebackend.dtos.request.BookCareTakerRequest;
import semicolon.africa.echildcarebackend.dtos.request.BookSpecialCareTakeRequest;
import semicolon.africa.echildcarebackend.dtos.response.BookSessionResponse;

public interface BookSpecialCareTakerService {
    BookSessionResponse bookSpecialCareTaker(String parentEmailAddress, BookSpecialCareTakeRequest bookSpecialCareTakeRequest);
}
