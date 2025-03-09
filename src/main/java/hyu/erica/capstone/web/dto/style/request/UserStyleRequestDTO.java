package hyu.erica.capstone.web.dto.style.request;

import java.time.LocalDateTime;

public record UserStyleRequestDTO(String city, LocalDateTime startDate, LocalDateTime endDate,
                                  String preferActivity, String requirement) {
}
