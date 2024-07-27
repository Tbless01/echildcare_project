package semicolon.africa.echildcarebackend.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semicolon.africa.echildcarebackend.data.models.ClockRecord;
import semicolon.africa.echildcarebackend.data.models.enumClasses.ClockRecordStatus;

import java.util.Optional;

public interface ClockRecordRepository extends JpaRepository<ClockRecord, Long> {

    Optional<ClockRecord> findByClockId(String clockId);
    Optional<ClockRecord> findByCareTakerEmailAddress(String careTakerEmailAddress);

    ClockRecord findByCareTakerEmailAddressAndClockRecordStatus(String careTakerEmail, ClockRecordStatus clockRecordStatus);
}
