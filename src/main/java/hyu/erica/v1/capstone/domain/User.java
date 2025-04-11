package hyu.erica.v1.capstone.domain;


import static jakarta.persistence.EnumType.*;

import hyu.erica.v1.capstone.domain.base.BaseEntity;
import hyu.erica.v1.capstone.domain.enums.Gender;
import hyu.erica.v1.capstone.domain.enums.PhoneService;
import hyu.erica.v1.capstone.domain.enums.ThirdPartyLogin;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;

    private String email;

    private String password;

    private LocalDate birthday;

    private Gender gender;

    private String profileImage;

    private String phoneNumber;

    // 약관
    private boolean termsOfService;

    // 개인 정보 동의
    private boolean privacyPolicy;

    // 마케팅 수신 동의
    private boolean marketingAgreement;

    @Enumerated(value = STRING)
    private ThirdPartyLogin thirdPartyLogin;

    @Enumerated(value = STRING)
    private PhoneService phoneService;

    public void updateInfo(String nickname, String phoneNumber, String profileImage) {
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }
}
