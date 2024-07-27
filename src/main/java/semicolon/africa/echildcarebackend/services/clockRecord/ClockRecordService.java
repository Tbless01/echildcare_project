package semicolon.africa.echildcarebackend.services.clockRecord;

import semicolon.africa.echildcarebackend.data.models.ClockRecord;
import semicolon.africa.echildcarebackend.data.models.enumClasses.ClockRecordStatus;
import semicolon.africa.echildcarebackend.exceptions.ClockException;
import semicolon.africa.echildcarebackend.exceptions.UserRegistrationException;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

import java.util.List;

public interface ClockRecordService {
    ApiResponse saveClock(String careTakerEmailAddress) throws ClockException;

    ApiResponse clockIn(String careTakerEmailAddress) ;

    ApiResponse clockInn(String careTakerEmailAddress) throws ClockException;

    ApiResponse clockOut(String careTakerEmailAddress);

    List<ClockRecord> viewCareTakerWorkHistory(String careTakerEmailAddress);

}
