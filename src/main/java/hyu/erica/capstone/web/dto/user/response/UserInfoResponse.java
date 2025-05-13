package hyu.erica.capstone.web.dto.user.response;

import java.time.LocalDate;

import hyu.erica.capstone.domain.enums.Gender;

public record UserInfoResponse(Long id, String name, String nickname,
							   String email, LocalDate birthDay, Gender gender,
							   String profileImage, String phoneNumber) {

	public static UserInfoResponse of(Long id, String name, String nickname, String email, LocalDate birthDay, Gender gender,
			String profileImage, String phoneNumber ) {
		return new UserInfoResponse(id, name, nickname, email, birthDay, gender, profileImage, phoneNumber);
	}
}
