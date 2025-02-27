package hyu.erica.capstone.web.dto.user.request;

import hyu.erica.capstone.domain.enums.PhoneService;
import java.time.LocalDate;

public record SignUpRequestDTO (String email, String password, String name, String phoneNumber, LocalDate birthDate,
                                PhoneService phoneService) {

}
