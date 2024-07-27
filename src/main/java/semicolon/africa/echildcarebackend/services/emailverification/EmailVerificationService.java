package semicolon.africa.echildcarebackend.services.emailverification;

import semicolon.africa.echildcarebackend.utils.ApiResponse;

public interface EmailVerificationService {
    ApiResponse verifyEmailAddress(String emailAddress);
}
