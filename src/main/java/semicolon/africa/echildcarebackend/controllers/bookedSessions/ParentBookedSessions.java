package semicolon.africa.echildcarebackend.controllers.bookedSessions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.data.models.BookedSessions;
import semicolon.africa.echildcarebackend.services.bookSession.BookSessionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ParentBookedSessions {
    private final BookSessionService bookSessionService;

    @PostMapping("parentBookedSessions")
    public ResponseEntity <List<BookedSessions>> checkPrentBookedSession(@RequestParam String parentEmailAddress){
        return new ResponseEntity<>(bookSessionService.findAllParentBookedSessionsByUserEmailAddress(parentEmailAddress), HttpStatus.OK);
    }
}
