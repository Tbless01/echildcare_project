package semicolon.africa.echildcarebackend.services.bookedCareTaker;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.BookedStatus;
import semicolon.africa.echildcarebackend.data.models.enumClasses.CareTakerAssigned;
import semicolon.africa.echildcarebackend.data.models.enumClasses.GenderType;
import semicolon.africa.echildcarebackend.data.models.enumClasses.PaymentStatus;
import semicolon.africa.echildcarebackend.dtos.request.BookCareTakerRequest;
import semicolon.africa.echildcarebackend.dtos.response.BookSessionResponse;
import semicolon.africa.echildcarebackend.services.bookSession.BookSessionService;
import semicolon.africa.echildcarebackend.services.careTaker.CareTakerService;
import semicolon.africa.echildcarebackend.services.parent.ParentService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;
import semicolon.africa.echildcarebackend.utils.GenerateApiResponse;


import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class BookCareTakerServiceImpl implements BookCareTakerService {

    private final ModelMapper modelMapper;
    private final BookSessionService bookSessionService;
    private final CareTakerService careTakerService;
    private final ParentService parentService;

    @Override
    public BookSessionResponse bookCareTaker(String parentEmailAddress, BookCareTakerRequest bookCareTakerRequest) {

        bookCareTakerRequest.setGenderType(String.valueOf(GenderType.valueOf(bookCareTakerRequest.getGenderType())));
        Parent foundParent = parentService.findByUserEmailAddress(parentEmailAddress);

        if (isNotAssigned(foundParent)) {
            BookedSessions bookedSessions = modelMapper.map(bookCareTakerRequest, BookedSessions.class);
            bookedSessions.setParentEmailAddress(parentEmailAddress);

            System.out.println("i got here");
            CareTaker foundCareTaker = careTakerService.getCareTaker(bookCareTakerRequest.getGenderType());
            System.out.println("i got here too");
            if (foundCareTaker == null) {
                return buildNoCaretakerFoundResponse();
            }

            foundCareTaker.setBookedStatus(BookedStatus.BOOKED);

            bookedSessions.setCareTakerEmailAddress(foundCareTaker.getUser().getEmailAddress());
            bookedSessions.setBookingId(getBookingId());
            bookedSessions.setAmount(getAmount(bookCareTakerRequest));
            bookedSessions.setPaymentStatus(PaymentStatus.PENDING);
            bookedSessions.setParentFullName(foundParent.getUser().getFirstName() + " " + foundParent.getUser().getLastName());
            BookedSessions savedBookSessions = bookSessionService.save(bookedSessions);

            List<BookedSessions> careTakerListOfBookings = foundCareTaker.getBookedSessions();
            careTakerListOfBookings.add(savedBookSessions);
            foundCareTaker.setBookedSessions(new ArrayList<>(careTakerListOfBookings));
            CareTaker updatedCareTaker = careTakerService.save(foundCareTaker);

            List<BookedSessions> parentBookedSessionsList = foundParent.getBookedSessions();
            parentBookedSessionsList.add(savedBookSessions);
            foundParent.setCareTakerAssigned(CareTakerAssigned.ASSIGNED);
            foundParent.setBookedSessions(new ArrayList<>(parentBookedSessionsList));
            parentService.save(foundParent);

            BookSessionResponse response =  buildBookedSessionResponse(foundCareTaker, savedBookSessions);
            return response;
            // return GenerateApiResponse.okResponse(GenerateApiResponse.SUCCESSFULLY_ASSIGNED_A_CARETAKER);
        }
        return buildFailedBookedSessionResponse();
        // return GenerateApiResponse.notOkResponse(GenerateApiResponse.ALREADY_ASSIGNED_A_CARETAKER);
    }

    private BookSessionResponse buildNoCaretakerFoundResponse() {
        BookSessionResponse bookSessionResponse = new BookSessionResponse();
        bookSessionResponse.setMessage(GenerateApiResponse.NO_AVAILABLE_CARETAKER);
        return bookSessionResponse;
    }

    private BookSessionResponse buildFailedBookedSessionResponse() {
        BookSessionResponse bookSessionResponse = new BookSessionResponse();
        bookSessionResponse.setMessage(GenerateApiResponse.ALREADY_ASSIGNED_A_CARETAKER);
        return bookSessionResponse;
    }

    private boolean isNotAssigned (Parent parent){
        return !parent.getCareTakerAssigned().equals(CareTakerAssigned.ASSIGNED);
    }

    private BookSessionResponse buildBookedSessionResponse (CareTaker careTaker, BookedSessions bookedSessions){
        BookSessionResponse bookSessionResponse = new BookSessionResponse();
        bookSessionResponse.setPaymentStatus(bookedSessions.getPaymentStatus());
        bookSessionResponse.setMessage(GenerateApiResponse.SUCCESSFUL_KINDLY_MAKE_PAYMENT);
        bookSessionResponse.setAmount(bookedSessions.getAmount());
        bookSessionResponse.setCareTakerEmailAddress(careTaker.getUser().getEmailAddress());
        bookSessionResponse.setFirstName(careTaker.getUser().getFirstName());
        bookSessionResponse.setLastName(careTaker.getUser().getLastName());
        bookSessionResponse.setBookingId(bookedSessions.getBookingId());
        bookSessionResponse.setAmount(bookedSessions.getAmount());
        bookSessionResponse.setParentFullName(bookedSessions.getParentFullName());
        bookSessionResponse.setNumberOfKids(bookedSessions.getNumberOfKids());
        bookSessionResponse.setParentFullName(bookedSessions.getParentFullName());

        return bookSessionResponse;
    }

    private BigDecimal getAmount (BookCareTakerRequest bookCareTakerRequest) {

        var month = bookCareTakerRequest.getCareTimeDuration();

        var numberOfKids = bookCareTakerRequest.getNumberOfKids();

        BigDecimal finalAmount;

        finalAmount = BigDecimal.valueOf(((long) month * numberOfKids) * 100000);

        if (numberOfKids > 1){
            finalAmount = BigDecimal.valueOf((month * numberOfKids) * (100000 * 0.85));
        }
        return finalAmount;
    }

    private String getBookingId () {
        SecureRandom random = new SecureRandom();
        int bookingID = random.nextInt(10000, 90000);
        return "#" + bookingID;
    }

}