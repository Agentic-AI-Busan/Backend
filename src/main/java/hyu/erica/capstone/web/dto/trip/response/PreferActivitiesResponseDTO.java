package hyu.erica.capstone.web.dto.trip.response;

public record PreferActivitiesResponseDTO (String preferActivities) {
    public static PreferActivitiesResponseDTO of(String preferActivities) {
        return new PreferActivitiesResponseDTO(preferActivities);
    }
}
