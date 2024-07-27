package semicolon.africa.echildcarebackend.dtos.request;

import lombok.Getter;
import lombok.Setter;
import semicolon.africa.echildcarebackend.data.models.enumClasses.GenderType;
import semicolon.africa.echildcarebackend.data.models.enumClasses.SpecialNeedCategory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import static semicolon.africa.echildcarebackend.utils.AppUtils.FIELD_MUST_NOT_BE_EMPTY;

@Getter
@Setter

public class AddChildRequest {
    @NotNull(message = FIELD_MUST_NOT_BE_EMPTY)
    @NotEmpty(message = FIELD_MUST_NOT_BE_EMPTY)
    private String firstName;
    @NotNull(message = FIELD_MUST_NOT_BE_EMPTY)
    @NotEmpty(message = FIELD_MUST_NOT_BE_EMPTY)
    private String lastName;
    @NotNull(message = FIELD_MUST_NOT_BE_EMPTY)
    @NotEmpty(message = FIELD_MUST_NOT_BE_EMPTY)
    private String  dateOfBirth;
    @NotNull(message = FIELD_MUST_NOT_BE_EMPTY)
    @NotEmpty(message = FIELD_MUST_NOT_BE_EMPTY)
    private SpecialNeedCategory specialNeedCategory;
    @NotNull(message = FIELD_MUST_NOT_BE_EMPTY)
    @NotEmpty(message = FIELD_MUST_NOT_BE_EMPTY)
    private GenderType genderType;
    private String homeAddress;
    private String specialNeedDescription;
}
