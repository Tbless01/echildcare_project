package semicolon.africa.echildcarebackend.services.parent.helperClasses;

import semicolon.africa.echildcarebackend.dtos.response.AddCardDetailsResponse;
import semicolon.africa.echildcarebackend.dtos.response.AddChildResponse;

import static semicolon.africa.echildcarebackend.utils.ResponseUtils.*;

public class BuildResponse {
//    public static AddChildResponse buildAddChildResponse(Long parentId) {
//        AddChildResponse addChildResponse = new AddChildResponse();
//        addChildResponse.setMessage(CHILD_ADDITION_SUCCESSFUL);
//        addChildResponse.setId(parentId);
//        return addChildResponse;
//    }

    public static AddChildResponse buildFailedToAddChildResponse(String parentEmailAddress) {
        AddChildResponse addChildResponse = new AddChildResponse();
        addChildResponse.setMessage(CHILD_ADDITION_UNSUCCESSFUL);
        addChildResponse.setEmailAddress(parentEmailAddress);

        return addChildResponse;
    }

    public static AddCardDetailsResponse buildAddCardResponse(Long parentId) {
        AddCardDetailsResponse addCardDetailsResponse = new  AddCardDetailsResponse();
        addCardDetailsResponse.setMessage(CARD_ADDITION_SUCCESSFUL);
        addCardDetailsResponse.setId(parentId);

        return addCardDetailsResponse;
    }
    public static AddCardDetailsResponse buildFailedToAddCardResponse(String parentEmailAddress) {
        AddCardDetailsResponse addCardDetailsResponse = new AddCardDetailsResponse();
        addCardDetailsResponse.setMessage(CARD_ADDITION_UNSUCCESSFUL);
        addCardDetailsResponse.setEmailAddress(parentEmailAddress);

        return addCardDetailsResponse;
    }
}
