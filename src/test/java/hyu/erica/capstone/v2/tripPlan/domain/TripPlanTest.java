package hyu.erica.capstone.v2.tripPlan.domain;

import static hyu.erica.capstone.v2.common.fixture.UserFixtures.createNewUser;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import hyu.erica.capstone.v2.common.fixture.UserFixtures;
import hyu.erica.v2.capstone.tripPlan.domain.TripPlan;
import hyu.erica.v2.capstone.tripPlan.exception.InvalidTripPlanDateException;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TripPlanTest {

    @DisplayName("여행 계획을 생성한다.")
    @Test
    void 여행_계획을_생성한다() {
        assertDoesNotThrow( () ->new TripPlan("부산 여행", "재미 있겠다!", "기본 이미지",
                LocalDate.MIN, LocalDate.MAX, createNewUser()));
    }

    @DisplayName("여행 시작일은 종료일 이전이어야 한다.")
    @Test
    void 여행_시작일은_종료일_이전이어야_한다() {
        assertThatThrownBy(() -> new TripPlan("부산 여행", "재미 있겠다!", "기본 이미지",
                LocalDate.MAX, LocalDate.MIN, createNewUser())).isInstanceOf(InvalidTripPlanDateException.class);
    }
}
