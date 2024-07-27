package semicolon.africa.echildcarebackend.controllers.clockRecordController;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.exceptions.ClockException;
import semicolon.africa.echildcarebackend.services.clockRecord.ClockRecordService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/careTaker/")
public class ClockInController {

    private final ClockRecordService clockRecordService;

    @PostMapping("clockIn")
    public ResponseEntity<ApiResponse> clockIn(@RequestParam String careTakerEmailAddress) {
        return new ResponseEntity<>(clockRecordService.clockIn(careTakerEmailAddress), HttpStatus.OK);
    }
}
