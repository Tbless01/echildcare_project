package semicolon.africa.echildcarebackend.services.clockRecord;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.CareTaker;
import semicolon.africa.echildcarebackend.data.models.ClockRecord;
import semicolon.africa.echildcarebackend.data.models.enumClasses.ClockRecordStatus;
import semicolon.africa.echildcarebackend.data.repositories.ClockRecordRepository;
import semicolon.africa.echildcarebackend.exceptions.ClockException;
import semicolon.africa.echildcarebackend.services.careTaker.CareTakerService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;
import semicolon.africa.echildcarebackend.utils.GenerateApiResponse;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static semicolon.africa.echildcarebackend.utils.ExceptionUtils.UNABLE_T0_CLOCK_IN;

@Service
@AllArgsConstructor
public class ClockRecordServiceImpl implements ClockRecordService {
    private final ClockRecordRepository clockRecordRepository;
    private final CareTakerService careTakerService;
    @Override
    public ApiResponse saveClock(String careTakerEmailAddress)  {
        var foundCareTaker = careTakerService.findByUserEmailAddress(careTakerEmailAddress);
       if(foundCareTaker==null) {return GenerateApiResponse.BadResponse(GenerateApiResponse.INVALID_CREDENTIALS);}
           ClockRecord savedClockRecord = buildClockRecord(careTakerEmailAddress);
           savedClockRecord.setClockRecordStatus(ClockRecordStatus.IN_PROGRESS);
           List<ClockRecord> foundCareTakerClockRecordList = foundCareTaker.getClockRecordList();
           foundCareTakerClockRecordList.add(savedClockRecord);
           foundCareTaker.setClockRecordList(new ArrayList<>(foundCareTakerClockRecordList));
           careTakerService.save(foundCareTaker);
           return GenerateApiResponse.okClockResponse(GenerateApiResponse.CLOCKED_IN_SUCCESSFULLY);
    }

    @Override
    public ApiResponse clockIn(String careTakerEmailAddress) {

        return saveClock(careTakerEmailAddress);
    }

    @Override
    public ApiResponse clockInn(String careTakerEmailAddress) throws ClockException {
        CareTaker foundCareTaker = careTakerService.findByUserEmailAddress(careTakerEmailAddress);

        if (foundCareTaker == null){
            throw new ClockException(UNABLE_T0_CLOCK_IN);
        }
        ClockRecord savedClockRecord = buildClockRecord(careTakerEmailAddress);
        List<ClockRecord> foundCareTakerClockRecordList = foundCareTaker.getClockRecordList();
        foundCareTakerClockRecordList.add(savedClockRecord);
        foundCareTaker.setClockRecordList(new ArrayList<>(foundCareTakerClockRecordList));
        careTakerService.save(foundCareTaker);

        return GenerateApiResponse.okClockResponse(GenerateApiResponse.CLOCKED_IN_SUCCESSFULLY);
    }




    @Override
    public ApiResponse clockOut(String careTakerEmailAddress) {
        ClockRecord foundClockRecord = clockRecordRepository.findByCareTakerEmailAddressAndClockRecordStatus(careTakerEmailAddress, ClockRecordStatus.IN_PROGRESS);
       // CareTaker foundCareTaker = careTakerService.findByUserEmailAddress(careTakerEmailAddress);
        if( foundClockRecord!= null && isInProgress(foundClockRecord)){

        foundClockRecord.setClockRecordStatus(ClockRecordStatus.COMPLETED);
        foundClockRecord.setClockOut(LocalDateTime.now());
         clockRecordRepository.save(foundClockRecord);

        return GenerateApiResponse.okClockResponse(GenerateApiResponse.CLOCKED_OUT_SUCCESSFUL);
        }

        return GenerateApiResponse.BadResponse(GenerateApiResponse.NO_RECORD_FOUND);
    }

    @Override
    public List<ClockRecord> viewCareTakerWorkHistory(String careTakerEmailAddress) {
        CareTaker foundCareTaker = careTakerService.findByUserEmailAddress(careTakerEmailAddress);
        return foundCareTaker.getClockRecordList();
    }

    private ClockRecord buildClockRecord(String careTakerEmailAddress){
        ClockRecord clockRecord = new ClockRecord();
        clockRecord.setClockIn(LocalDateTime.now());
        clockRecord.setClockId(getBookingId());
        clockRecord.setCareTakerEmailAddress(careTakerEmailAddress);
        clockRecord.setClockRecordStatus(ClockRecordStatus.IN_PROGRESS);
        return clockRecordRepository.save(clockRecord);
    }

    private boolean isInProgress(ClockRecord clockRecord) {
        return !clockRecord.getClockRecordStatus().equals(ClockRecordStatus.COMPLETED);
    }


    private String getBookingId() {
        SecureRandom random = new SecureRandom();
        int bookingID = random.nextInt(1000,9000);
        return "#"+bookingID;
    }
}
