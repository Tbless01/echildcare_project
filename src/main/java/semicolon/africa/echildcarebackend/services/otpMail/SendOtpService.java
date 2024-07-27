package semicolon.africa.echildcarebackend.services.otpMail;

import semicolon.africa.echildcarebackend.utils.ApiResponse;

public interface SendOtpService {
    ApiResponse sendOtp(String emailAddress);
}
