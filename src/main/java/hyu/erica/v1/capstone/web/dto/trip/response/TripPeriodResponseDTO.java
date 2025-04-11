package hyu.erica.v1.capstone.web.dto.trip.response;

import java.time.LocalDate;

public record TripPeriodResponseDTO(LocalDate startDate, Integer startHour, LocalDate endDate, Integer endHour) {
    public static TripPeriodResponseDTO of(LocalDate startDate, Integer startHour, LocalDate endDate, Integer endHour) {
        return new TripPeriodResponseDTO(startDate, startHour, endDate, endHour);
    }
}
