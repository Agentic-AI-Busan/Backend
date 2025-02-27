package hyu.erica.capstone.api.exception;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralException extends RuntimeException {
    private ErrorStatus status;
    public GeneralException(ErrorStatus status) {
        super(status.getCode());
        this.status = status;
    }
}
