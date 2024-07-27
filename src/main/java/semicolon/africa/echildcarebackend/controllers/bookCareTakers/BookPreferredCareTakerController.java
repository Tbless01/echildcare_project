package semicolon.africa.echildcarebackend.controllers.bookCareTakers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.dtos.request.BookSpecialCareTakeRequest;
import semicolon.africa.echildcarebackend.dtos.response.BookSessionResponse;

import semicolon.africa.echildcarebackend.services.bookSpecialCareTaker.BookSpecialCareTakerService;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/careTaker/")
public class BookPreferredCareTakerController {

    private final BookSpecialCareTakerService bookSpecialCareTakerService;

    @PostMapping("bookPreferredCareTaker")
    public ResponseEntity<BookSessionResponse> bookPreferredCareTaker(@RequestParam String emailAddress, @RequestBody BookSpecialCareTakeRequest bookSpecialCareTakeRequest){
        return new ResponseEntity<>(bookSpecialCareTakerService.bookSpecialCareTaker(emailAddress, bookSpecialCareTakeRequest), HttpStatus.OK);
    }
}
