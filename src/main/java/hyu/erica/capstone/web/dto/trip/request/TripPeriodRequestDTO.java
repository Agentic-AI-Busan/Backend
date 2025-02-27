package hyu.erica.capstone.web.dto.trip.request;

import java.time.LocalDate;

public record TripPeriodRequestDTO(LocalDate startDate, Integer startHour, LocalDate endDate, Integer endHour) {
}
