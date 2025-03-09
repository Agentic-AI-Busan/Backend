package hyu.erica.capstone.web.dto.style.response;

import hyu.erica.capstone.domain.Style;
import java.time.LocalDateTime;

public record UserStyleResponseDTO(String city, LocalDateTime startDate, LocalDateTime endDate,
                                   String preferActivity, String requirement) {

    public static UserStyleResponseDTO of(Style style) {
        return new UserStyleResponseDTO(style.getCity().name(), style.getStartDate(), style.getEndDate(),
                                        style.getPreferActivity(), style.getRequirement());
    }
}

