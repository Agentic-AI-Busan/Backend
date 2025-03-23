package hyu.erica.capstone.domain;

import static jakarta.persistence.GenerationType.*;

import hyu.erica.capstone.domain.enums.TripPlanStatus;
import hyu.erica.capstone.domain.mapping.PreferAttraction;
import hyu.erica.capstone.domain.mapping.PreferRestaurant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
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
@Table(name = "trip_plan")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TripPlan {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String profileImage;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(value = EnumType.STRING)
    private TripPlanStatus tripPlanStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "tripPlan", cascade = CascadeType.ALL)
    private List<PreferAttraction> preferAttractions;

    @OneToMany(mappedBy = "tripPlan", cascade = CascadeType.ALL)
    private List<PreferRestaurant> preferRestaurants;


    public void setStatus(TripPlanStatus status) {
        this.tripPlanStatus = status;
    }


}
