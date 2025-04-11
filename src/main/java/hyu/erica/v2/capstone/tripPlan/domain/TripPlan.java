package hyu.erica.v2.capstone.tripPlan.domain;

import hyu.erica.v1.capstone.domain.User;
import hyu.erica.v2.capstone.global.entity.BaseEntity;
import hyu.erica.v2.capstone.tripPlan.domain.enums.TripPlanStatus;
import hyu.erica.v2.capstone.tripPlan.exception.InvalidTripPlanDateException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Table(name = "trip_plan")
@Entity
public class TripPlan extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "profile_image", nullable = false)
    private String profileImage;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TripPlanStatus tripPlanStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected TripPlan() {

    }

    public TripPlan(final String title, final String description, final String profileImage,
                    final LocalDate startDate, final LocalDate endDate, final User user) {
        this.title = title;
        this.description = description;
        this.profileImage = profileImage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.tripPlanStatus = TripPlanStatus.PROGRESSING;
    }

    private void validateTripDate(final LocalDate startDate, final LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new InvalidTripPlanDateException();
        }
    }

}
