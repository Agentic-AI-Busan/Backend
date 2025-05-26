package hyu.erica.capstone.web.dto.user.response;

import hyu.erica.capstone.domain.TripPlan;
import hyu.erica.capstone.domain.enums.TripPlanStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record MyTripPlanResponse (List<TripDetail> tripPlans, int totalElements) {

    public static MyTripPlanResponse of(List<TripPlan> tripPlans) {
        List<TripDetail> tripPlanDetails = tripPlans.stream()
                .map(tripPlan -> TripDetail.of(
                        tripPlan.getId(),
                        tripPlan.getTitle(),
                        tripPlan.getTripPlanStatus(),
                        tripPlan.getStartDate(),
                        tripPlan.getEndDate(),
                        tripPlan.getProfileImage(),
                        tripPlan.getDescription(),
                        "부산"))
                .toList();
        return new MyTripPlanResponse(tripPlanDetails, tripPlans.size());
    }

    private record TripDetail(Long tripPlanId, String tripPlanName, TripPlanStatus tripPlanStatus, LocalDate startDate, LocalDate endDate, Integer dayDiff, String imageUrl, String memo, String city) {
        public static TripDetail of(
                Long tripPlanId, String tripPlanName, TripPlanStatus tripPlanStatus, LocalDate startDate, LocalDate endDate,
                String imageUrl, String memo, String city) {

            // dayDiff 오늘부터 여행 종료일 까지의 일수
            int dayDiff = (int) (LocalDate.now().toEpochDay() - startDate.toEpochDay());

            return new TripDetail(tripPlanId, tripPlanName, tripPlanStatus, startDate, endDate, dayDiff ,imageUrl, memo, city);
        }
    }
}
