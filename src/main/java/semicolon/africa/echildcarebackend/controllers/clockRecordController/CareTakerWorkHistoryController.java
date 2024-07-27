package semicolon.africa.echildcarebackend.controllers.clockRecordController;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.echildcarebackend.data.models.ClockRecord;
import semicolon.africa.echildcarebackend.services.clockRecord.ClockRecordService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/")
public class CareTakerWorkHistoryController {

    private final ClockRecordService clockRecordService;

    @PostMapping("careTakerWorkHistory")
    public ResponseEntity<List<ClockRecord>> viewCareTakerWorkHistory(@RequestParam String careTakerEmailAddress){
        return new ResponseEntity<>(clockRecordService.viewCareTakerWorkHistory(careTakerEmailAddress), HttpStatus.OK);
    }
}
