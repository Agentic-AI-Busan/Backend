package hyu.erica.capstone.web.dto.user.request;

import java.time.LocalDate;

import hyu.erica.capstone.domain.enums.Gender;

public record UpdateInfoRequestDTO (String nickname, String phoneNumber, String profileImage,
									Gender gender, LocalDate birthDay) {
}
