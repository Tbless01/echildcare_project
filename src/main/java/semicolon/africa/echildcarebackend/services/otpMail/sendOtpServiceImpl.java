package semicolon.africa.echildcarebackend.services.otpMail;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import semicolon.africa.echildcarebackend.data.models.enumClasses.TakenStatus;
import semicolon.africa.echildcarebackend.data.models.VerificationToken;
import semicolon.africa.echildcarebackend.services.emailverification.EmailVerificationService;
import semicolon.africa.echildcarebackend.services.verification.TokenVerificationService;
import semicolon.africa.echildcarebackend.utils.ApiResponse;
import semicolon.africa.echildcarebackend.utils.GenerateApiResponse;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class sendOtpServiceImpl implements SendOtpService {
    private final TokenVerificationService tokenVerificationService;
    private final JavaMailSender mailSender;
    private final EmailVerificationService emailVerificationService;

    //    @Value("${spring.mail.username}")
//    private  String EMAIL_SENDER;
    @Override
    public ApiResponse sendOtp(String emailAddress) {

        ApiResponse response = emailVerificationService.verifyEmailAddress(emailAddress);
        if (response.isSuccessful()) {
            String generatedOtp = tokenVerificationService.generateVerificationToken(3);
            VerificationToken verificationToken = VerificationToken.builder()
                    .token(generatedOtp)
                    .createdAt(LocalDateTime.now())
                    .expiredAt(LocalDateTime.now().plusMinutes(15L))
                    .takenStatus(TakenStatus.NOT_TAKEN)
                    .build();

            tokenVerificationService.save(verificationToken);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("awoyeleolakunle@gmail.com");
            simpleMailMessage.setTo(emailAddress);
            simpleMailMessage.setSubject(GenerateApiResponse.VERIFY_YOUR_ITS_YOU);
            simpleMailMessage.setText(verificationToken.getToken());
            System.out.println("I got here");
            mailSender.send(simpleMailMessage);

            return ApiResponse.builder()
                    .isSuccessful(true)
                    .build();
        }
         return ApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .isSuccessful(false)
                .build();
        }
    }
