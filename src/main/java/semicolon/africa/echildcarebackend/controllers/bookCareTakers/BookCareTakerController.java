package semicolon.africa.echildcarebackend.controllers.bookCareTakers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.dtos.request.BookCareTakerRequest;
import semicolon.africa.echildcarebackend.dtos.response.BookSessionResponse;
import semicolon.africa.echildcarebackend.services.bookedCareTaker.BookCareTakerService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BookCareTakerController {

    private final BookCareTakerService bookCareTakerService;

    @PostMapping("bookCareTaker")
    public ResponseEntity<BookSessionResponse> bookACareTaker(@RequestParam String parentEmailAddress, @RequestBody BookCareTakerRequest bookCareTakerRequest){
        return new ResponseEntity<>(bookCareTakerService.bookCareTaker(parentEmailAddress, bookCareTakerRequest), HttpStatus.OK);
    }
}
