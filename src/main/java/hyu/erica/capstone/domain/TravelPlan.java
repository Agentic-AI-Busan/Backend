package hyu.erica.capstone.domain;


import static jakarta.persistence.GenerationType.IDENTITY;

import hyu.erica.capstone.domain.enums.City;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
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
public class TravelPlan {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private City city;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String preferActivity;

    private String requirement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
