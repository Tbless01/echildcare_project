package semicolon.africa.echildcarebackend.services.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.*;
import semicolon.africa.echildcarebackend.data.models.enumClasses.*;
import semicolon.africa.echildcarebackend.dtos.request.RegistrationRequest;
import semicolon.africa.echildcarebackend.exceptions.UserRegistrationException;
import semicolon.africa.echildcarebackend.security.JwtService;
import semicolon.africa.echildcarebackend.services.careTaker.CareTakerService;
import semicolon.africa.echildcarebackend.services.parent.ParentService;
import semicolon.africa.echildcarebackend.services.user.UserService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;
import semicolon.africa.echildcarebackend.utils.GenerateApiResponse;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserService userService;
    private final ParentService parentService;
    private final CareTakerService careTakerService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse register(RegistrationRequest registrationRequest) throws UserRegistrationException {
        boolean isRegisteredUser = userService.findUserByEmailAddress(registrationRequest.getEmailAddress()) != null;
        if (isRegisteredUser) throw new UserRegistrationException("User Already exist");
        User savedUser = buildUser(registrationRequest);
        if (registrationRequest.getUserCategory().equalsIgnoreCase("PARENT")) {
            Set<Roles> userRoles = new HashSet<>();
            userRoles.add(Roles.PARENT);
            savedUser.setRoles(userRoles);
            userService.save(savedUser);
            Parent parent = Parent.builder()
                    .user(savedUser)
                    .careTakerAssigned(CareTakerAssigned.NOT_ASSIGNED)
                    .build();
            parentService.save(parent);
            System.out.println("Parent registered");
        } else {
            if (registrationRequest.getUserCategory().equalsIgnoreCase("CARETAKER")) {
                Set<Roles> userRoles = new HashSet<>();
                userRoles.add(Roles.CARETAKER);
                savedUser.setRoles(userRoles);
                userService.save(savedUser);
                CareTaker careTaker = CareTaker.builder()
                        .user(savedUser)
                        .bookedStatus(BookedStatus.NOT_BOOKED)
                        .build();
                careTakerService.save(careTaker);
                System.out.println("Caretaker registered");
            }
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getEmailAddress());
        String jwt = jwtService.generateToken(userDetails);
        return GenerateApiResponse.createdResponse("Bearer "+jwt);
    }
    private User buildUser(RegistrationRequest registrationRequest) {
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(Roles.USER);
         User user = User.builder()
                .emailAddress(registrationRequest.getEmailAddress())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                 .userCategory(UserCategory.valueOf(registrationRequest.getUserCategory()))
                 .genderType(GenderType.valueOf(registrationRequest.getGenderType()))
                .roles(userRoles)
                .build();
         return userService.save(user);
    }

}
