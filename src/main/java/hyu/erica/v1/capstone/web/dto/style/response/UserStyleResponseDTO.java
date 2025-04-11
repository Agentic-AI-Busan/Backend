package hyu.erica.v1.capstone.web.dto.style.response;

import hyu.erica.v1.capstone.domain.Style;
import java.time.LocalDate;

public record UserStyleResponseDTO(String city, LocalDate startDate, LocalDate endDate,
                                   String preferActivity, String requirement) {

    public static UserStyleResponseDTO of(Style style) {
        return new UserStyleResponseDTO(style.getCity().name(), style.getStartDate(), style.getEndDate(),
                                        style.getPreferActivity(), style.getRequirement());
    }
}

