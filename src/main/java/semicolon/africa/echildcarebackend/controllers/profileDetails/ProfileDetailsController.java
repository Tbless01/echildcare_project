package semicolon.africa.echildcarebackend.controllers.profileDetails;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.dtos.response.ProfileDetailsResponse;
import semicolon.africa.echildcarebackend.services.profile.ProfileService;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ProfileDetailsController {

    private final ProfileService profileService;

    @GetMapping("userProfileData")
    public ResponseEntity<ProfileDetailsResponse> getProfileUpdate(@RequestParam String emailAddress){
        return new ResponseEntity<>(profileService.getProfileUpdate(emailAddress), HttpStatus.OK);
    }
}
