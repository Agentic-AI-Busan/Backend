package hyu.erica.capstone.v2.common.fixture;

import hyu.erica.v2.capstone.user.domain.User;
import hyu.erica.v2.capstone.user.domain.enums.Gender;
import java.time.LocalDate;

public class UserFixtures {

    public static User createNewUser() {
        return new User("name", "nickname", "email@naver.com", "password",
                LocalDate.MAX, Gender.MALE, "profileImage", "phoneNumber");
    }
}
