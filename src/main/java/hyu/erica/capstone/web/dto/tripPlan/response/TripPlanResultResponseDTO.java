package hyu.erica.capstone.web.dto.tripPlan.response;

import static hyu.erica.capstone.utils.CategoryImageMapper.*;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.domain.Attraction;
import hyu.erica.capstone.domain.Restaurant;
import hyu.erica.capstone.domain.TripPlan;
import hyu.erica.capstone.domain.TripScheduleItem;
import hyu.erica.capstone.domain.enums.PlaceType;
import hyu.erica.capstone.utils.CategoryImageMapper;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record TripPlanResultResponseDTO(
        String title,
        String description,
        String profileImage,
        LocalDate startDate,
        LocalDate endDate,
        List<TripPlanDayDTO> days
) {

    public static TripPlanResultResponseDTO of(TripPlan tripPlan, List<TripScheduleItem> scheduleItems) {
        // 일자별로 그룹화 (dayNumber 기준)
        Map<Integer, List<TripScheduleItem>> groupedByDay = scheduleItems.stream()
                .collect(Collectors.groupingBy(TripScheduleItem::getDayNumber));

        List<TripPlanDayDTO> days = groupedByDay.entrySet().stream()
                .map(entry -> {
                    int dayNumber = entry.getKey();
                    List<TripScheduleItem> items = entry.getValue().stream()
                            .sorted(Comparator.comparingInt(TripScheduleItem::getOrderInDay)) // 정렬
                            .toList();

                    // startDate + (dayNumber - 1)로 실제 날짜 계산
                    LocalDate date = tripPlan.getStartDate().plusDays(dayNumber - 1);
                    return TripPlanDayDTO.of(dayNumber, date, items);
                })
                .sorted(Comparator.comparingInt(TripPlanDayDTO::dayNumber)) // day 순으로 정렬
                .toList();

        return new TripPlanResultResponseDTO(
                tripPlan.getTitle(),
                tripPlan.getDescription(),
                tripPlan.getProfileImage(),
                tripPlan.getStartDate(),
                tripPlan.getEndDate(),
                days
        );
    }

    public record TripPlanDayDTO(
            int dayNumber,
            LocalDate date,
            List<TripScheduleItemDTO> scheduleItems
    ) {
        public static TripPlanDayDTO of(int dayNumber, LocalDate date, List<TripScheduleItem> items) {
            List<TripScheduleItemDTO> dtos = items.stream()
                    .map(TripScheduleItemDTO::of)
                    .toList();
            return new TripPlanDayDTO(dayNumber, date, dtos);
        }
    }

    public record TripScheduleItemDTO(
            Long itemId,
            int order,
            PlaceType placeType, // "ATTRACTION" or "RESTAURANT"
            Long placeId,
            Double latitude,
            Double longitude,
            String name,
            String imageUrl,
            String memo
    ) {
        public static TripScheduleItemDTO of(TripScheduleItem tripScheduleItem) {
            PlaceType placeType = tripScheduleItem.getPlaceType();
            Long placeId;
            String name;
            String imageUrl;
            Double latitude;
            Double longitude;

            switch (placeType) {
                case ATTRACTION -> {
                    Attraction attraction = tripScheduleItem.getAttraction();
                    placeId = attraction.getContentId();
                    name = attraction.getContentName();
                    imageUrl = attraction.getImageUrl();
                    latitude = attraction.getLatitude();
                    longitude = attraction.getLongitude();
                }
                case RESTAURANT -> {
                    Restaurant restaurant = tripScheduleItem.getRestaurant();
                    placeId = restaurant.getId();
                    name = restaurant.getRestaurantName();
                    imageUrl = getImageUrl(restaurant.getBusinessStatus()); // 임시 처리
                    latitude = restaurant.getLatitude();
                    longitude = restaurant.getLongitude();
                }
                default -> throw new GeneralException(ErrorStatus._UNSUPPORTED_PLACE_TYPE);
            }

            return new TripScheduleItemDTO(
                    tripScheduleItem.getId(),
                    tripScheduleItem.getOrderInDay(),
                    placeType,
                    placeId,
                    latitude,
                    longitude,
                    name,
                    imageUrl,
                    tripScheduleItem.getMemo()
            );
        }
    }

}
