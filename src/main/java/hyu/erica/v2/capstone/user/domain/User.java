package hyu.erica.v2.capstone.user.domain;

import hyu.erica.v2.capstone.global.entity.BaseEntity;
import hyu.erica.v2.capstone.user.domain.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "profile_image", nullable = false)
    private String profileImage;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    protected User() {

    }

    public User(String name, String nickname, String email, String password,
                LocalDate birthday, Gender gender, String profileImage, String phoneNumber ) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.profileImage = profileImage;
        this.phoneNumber = phoneNumber;
    }
}
