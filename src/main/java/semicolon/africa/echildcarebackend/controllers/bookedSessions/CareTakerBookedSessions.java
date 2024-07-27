package semicolon.africa.echildcarebackend.controllers.bookedSessions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.data.models.BookedSessions;
import semicolon.africa.echildcarebackend.services.bookSession.BookSessionService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping ("api/v1/")
public class CareTakerBookedSessions {

    private final BookSessionService bookSessionService;

    @PostMapping("careTakerBookedSessions")
    public ResponseEntity<List<BookedSessions>>checkBookedSessions(@RequestParam String careTakerEmailAddress) {
        return new ResponseEntity<>(bookSessionService.findAllCareTakerBookedSessionsByUserEmailAddress(careTakerEmailAddress), HttpStatus.OK);
    }

}
