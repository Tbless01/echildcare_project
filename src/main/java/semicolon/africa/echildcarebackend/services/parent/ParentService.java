package semicolon.africa.echildcarebackend.services.parent;

import semicolon.africa.echildcarebackend.data.models.Parent;
import semicolon.africa.echildcarebackend.dtos.request.AddCardDetailsRequest;
import semicolon.africa.echildcarebackend.dtos.request.AddChildRequest;
import semicolon.africa.echildcarebackend.dtos.request.EditProfileRequest;
import semicolon.africa.echildcarebackend.dtos.request.MakePaymentRequest;
import semicolon.africa.echildcarebackend.dtos.response.AddCardDetailsResponse;
import semicolon.africa.echildcarebackend.dtos.response.MakePaymentResponse;
import semicolon.africa.echildcarebackend.exceptions.ChildCareException;
import semicolon.africa.echildcarebackend.exceptions.PaystackApiException;
import semicolon.africa.echildcarebackend.utils.ApiResponse;

public interface ParentService {
    Parent save(Parent parent);
    ApiResponse add(String parentEmailAddress, AddChildRequest addChildRequest) throws ChildCareException;

    MakePaymentResponse makePayment (String parentEmail, MakePaymentRequest makePaymentRequest) throws PaystackApiException;

    AddCardDetailsResponse addDebitCard(String parentEmailAddress, AddCardDetailsRequest addChildRequest) throws ChildCareException;

    Parent findByUserEmailAddress(String parentEmailAddress);

    ApiResponse updateProfile (String parentEmail, EditProfileRequest editProfileRequest);
}
