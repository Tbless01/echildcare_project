package semicolon.africa.echildcarebackend.controllers.clockRecordController;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.ClockRecordStatus;
import semicolon.africa.echildcarebackend.services.clockRecord.ClockRecordService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/careTaker/")
@CrossOrigin(origins = "*")
public class ClockOutController {
    private final ClockRecordService clockRecordService;
    @PostMapping("clockOut")
    public ResponseEntity<ApiResponse> clockOut(@RequestParam String careTakerEmailAddress){
        return new ResponseEntity<>(clockRecordService.clockOut(careTakerEmailAddress), HttpStatus.OK);
    }
}
