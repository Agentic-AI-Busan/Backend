package hyu.erica.capstone.web.dto.style.response;

public record UserStyleInitResponseDTO(Long userId, Long styleId) {

    public static UserStyleInitResponseDTO of(Long userId, Long styleId) {
        return new UserStyleInitResponseDTO(userId, styleId);
    }
}
