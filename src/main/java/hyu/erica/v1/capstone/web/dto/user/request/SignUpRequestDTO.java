package hyu.erica.v1.capstone.web.dto.user.request;

import hyu.erica.v1.capstone.domain.enums.PhoneService;
import java.time.LocalDate;

public record SignUpRequestDTO (String email, String password, String name, String phoneNumber, LocalDate birthDate,
                                PhoneService phoneService, boolean termsOfService, boolean privacyPolicy, boolean marketingAgreement) {

}
