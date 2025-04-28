package hyu.erica.capstone.web.dto.client;

import java.time.LocalDate;

public record StyleRequestDTO (String city, LocalDate startDate, LocalDate endDate,
                               String preferActivity, String requirement){

    public static StyleRequestDTO of(String city, LocalDate startDate, LocalDate endDate, String preferActivity, String requirement){
        return new StyleRequestDTO(city, startDate, endDate, preferActivity, requirement);
    }
}
