package hyu.erica.capstone.web.dto.style.response;

import hyu.erica.capstone.domain.Style;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserStyleResponseDTO(String city, LocalDate startDate, LocalDate endDate,
                                   String preferActivity, String preferFood, String dislikedFood,String requirement) {

    public static UserStyleResponseDTO of(Style style) {
        return new UserStyleResponseDTO(style.getCity().name(), style.getStartDate(), style.getEndDate(),
                                        style.getPreferActivity(), style.getPreferFood(), style.getDislikedFood(), style.getRequirement());
    }
}

