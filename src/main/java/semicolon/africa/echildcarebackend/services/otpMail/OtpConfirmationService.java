package semicolon.africa.echildcarebackend.services.otpMail;


import semicolon.africa.echildcarebackend.utils.ApiResponse;

public interface OtpConfirmationService {
    ApiResponse confirmOtp(String otp);
}
