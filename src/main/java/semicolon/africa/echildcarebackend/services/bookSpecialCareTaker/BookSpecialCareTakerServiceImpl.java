package semicolon.africa.echildcarebackend.services.bookSpecialCareTaker;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.*;
import semicolon.africa.echildcarebackend.dtos.request.BookSpecialCareTakeRequest;
import semicolon.africa.echildcarebackend.dtos.response.BookSessionResponse;
import semicolon.africa.echildcarebackend.services.bookSession.BookSessionService;
import semicolon.africa.echildcarebackend.services.careTaker.CareTakerService;
import semicolon.africa.echildcarebackend.services.parent.ParentService;
import semicolon.africa.echildcarebackend.utils.GenerateApiResponse;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class BookSpecialCareTakerServiceImpl implements BookSpecialCareTakerService {

    private final CareTakerService careTakerService;
    private final BookSessionService bookSessionService;
    private final ParentService parentService;
    private final ModelMapper modelMapper;
    @Override
    public BookSessionResponse bookSpecialCareTaker(String parentEmailAddress, BookSpecialCareTakeRequest bookSpecialCareTakeRequest) {
        System.out.println("i am the first name " + bookSpecialCareTakeRequest.getFirstName());
        System.out.println("I am the last name" + bookSpecialCareTakeRequest.getLastName());

        bookSpecialCareTakeRequest.setTimeDuration(String.valueOf(TimeDuration.valueOf(bookSpecialCareTakeRequest.getTimeDuration())));

        System.out.println("I got here");
        CareTaker foundCareTaker = careTakerService.getSpecialCareTaker(bookSpecialCareTakeRequest.getFirstName(), bookSpecialCareTakeRequest.getLastName());
        System.out.println("I'm the foundCareTaker " + foundCareTaker);
        if (foundCareTaker==null) {return buildCareTakerNoAvailable();}

        BookedSessions bookedSessions = modelMapper.map(bookSpecialCareTakeRequest, BookedSessions.class);
        bookedSessions.setParentEmailAddress(parentEmailAddress);
        Parent foundParent = parentService.findByUserEmailAddress(parentEmailAddress);
        System.out.println(" i got here before isNotAssigned");

        if (isNotAssigned(foundParent)) {

              foundParent.setCareTakerAssigned(CareTakerAssigned.ASSIGNED);
              System.out.println("I'm the found careTaker" + foundCareTaker);
              foundCareTaker.setBookedStatus(BookedStatus.BOOKED);

              bookedSessions.setCareTakerEmailAddress(foundCareTaker.getUser().getEmailAddress());
              bookedSessions.setBookingId(getBookingId());
              bookedSessions.setAmount(getAmount(bookSpecialCareTakeRequest));
              bookedSessions.setPaymentStatus(PaymentStatus.PENDING);
              bookedSessions.setParentFullName(foundParent.getUser().getFirstName() + " " + foundParent.getUser().getLastName());

             var updatedBookedSessions = bookSessionService.save(bookedSessions);
             var bookedSessionList  = foundParent.getBookedSessions();
             bookedSessionList.add(updatedBookedSessions);
              foundParent.setBookedSessions(new ArrayList<>(bookedSessionList));
             Parent savedParent =  parentService.save(foundParent);
            var careTakerBookedSessionList = foundCareTaker.getBookedSessions();
            careTakerBookedSessionList.add(updatedBookedSessions);
            foundCareTaker.setBookedSessions(new ArrayList<>(careTakerBookedSessionList));
            CareTaker updatedCareTaker = careTakerService.save(foundCareTaker);

              return buildBookedSessionResponse(updatedCareTaker, bookedSessions, savedParent);

        }
        return  buildNoCareTakerBookedSessionResponse();
    }

    private BookSessionResponse buildCareTakerNoAvailable() {
        BookSessionResponse bookSessionResponse = new BookSessionResponse();
        bookSessionResponse.setMessage(GenerateApiResponse.CARETAKER_NOT_AVAILABLE);
        return bookSessionResponse;
    }

    private BookSessionResponse buildNoCareTakerBookedSessionResponse() {
        BookSessionResponse bookSessionResponse = new BookSessionResponse();
        bookSessionResponse.setMessage(GenerateApiResponse.NO_AVAILABLE_CARETAKER);
        return bookSessionResponse;

    }

    private boolean isNotAssigned(Parent parent) {
            return !parent.getCareTakerAssigned().equals(CareTakerAssigned.ASSIGNED);
        }

    private BigDecimal getAmount(BookSpecialCareTakeRequest bookSpecialCareTakeRequest) {

        int month = bookSpecialCareTakeRequest.getCareTimeDuration();

        int numberOfKids = bookSpecialCareTakeRequest.getNumberOfKids();

        BigDecimal finalAmount;

        finalAmount = BigDecimal.valueOf(((long) month * numberOfKids) * 100000);

        if (numberOfKids > 1){
            finalAmount = BigDecimal.valueOf((month * numberOfKids) * (100000 * 0.85));
        }
        return finalAmount;
    }

    private String getBookingId() {
        SecureRandom random = new SecureRandom();
        int bookingID = random.nextInt(1000,9000);

        return "#"+bookingID;
    }

    private BookSessionResponse buildBookedSessionResponse(CareTaker careTaker, BookedSessions bookedSessions, Parent parent) {
        BookSessionResponse bookSessionResponse = new BookSessionResponse();
        bookSessionResponse.setPaymentStatus((bookedSessions.getPaymentStatus()));
        bookSessionResponse.setMessage(GenerateApiResponse.SUCCESSFULLY_ASSIGNED_A_CARETAKER);
        bookSessionResponse.setAmount(bookedSessions.getAmount());
        bookSessionResponse.setParentFullName(bookedSessions.getParentFullName());
        bookSessionResponse.setFirstName(careTaker.getUser().getFirstName());
        bookSessionResponse.setLastName(careTaker.getUser().getLastName());
        bookSessionResponse.setCareTakerEmailAddress(careTaker.getUser().getEmailAddress());
        bookSessionResponse.setResumptionDate(bookedSessions.getResumptionDate);
        bookSessionResponse.setTimeDuration(String.valueOf(bookedSessions.getTimeDuration()));
        bookSessionResponse.setNumberOfKids(bookedSessions.getNumberOfKids());
        bookSessionResponse.setCareTimeDuration(bookedSessions.getCareTimeDuration());
        bookSessionResponse.setBookingId(bookedSessions.getBookingId());

        return bookSessionResponse;
    }

    private BookSessionResponse buildFailedBookedSessionResponse() {
        BookSessionResponse bookSessionResponse = new BookSessionResponse();
        bookSessionResponse.setMessage(GenerateApiResponse.ALREADY_ASSIGNED_A_CARETAKER);
        return bookSessionResponse;
    }
}




