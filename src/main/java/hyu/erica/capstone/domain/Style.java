package hyu.erica.capstone.domain;


import static jakarta.persistence.GenerationType.IDENTITY;

import hyu.erica.capstone.domain.enums.City;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Style {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private City city;

    private LocalDate startDate;

    private LocalDate endDate;

    private String preferActivity;

    private String preferFood;

    private String dislikedFood;

    private String requirement;

    private String ageRange;

    private String numberOfPeople;

    private String transportation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void updateStyle(
            LocalDate startDate, LocalDate endDate, String preferActivity,
            String preferFood, String dislikedFood, String requirement,
            String ageRange, String numberOfPeople, String transportation) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.preferActivity = preferActivity;
        this.preferFood = preferFood;
        this.dislikedFood = dislikedFood;
        this.requirement = requirement;
        this.ageRange = ageRange;
        this.numberOfPeople = numberOfPeople;
        this.transportation = transportation;
    }
}
