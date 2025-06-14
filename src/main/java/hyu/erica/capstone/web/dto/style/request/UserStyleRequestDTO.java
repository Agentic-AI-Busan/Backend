package hyu.erica.capstone.web.dto.style.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserStyleRequestDTO(String city, LocalDate startDate, LocalDate endDate,
                                  String preferActivity, String preferFood,
								  String dislikedFood, String requirement,
								  String transportation, String numberOfPeople, String ageRange
) {
}
