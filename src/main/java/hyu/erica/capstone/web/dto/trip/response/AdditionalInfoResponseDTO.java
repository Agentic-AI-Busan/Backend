package hyu.erica.capstone.web.dto.trip.response;

public record AdditionalInfoResponseDTO (String additionalInfo){
    public static AdditionalInfoResponseDTO of(String additionalInfo) {
        return new AdditionalInfoResponseDTO(additionalInfo);
    }
}
