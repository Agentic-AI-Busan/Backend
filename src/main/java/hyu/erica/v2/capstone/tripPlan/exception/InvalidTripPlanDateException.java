package hyu.erica.v2.capstone.tripPlan.exception;

public class InvalidTripPlanDateException extends RuntimeException{

    public InvalidTripPlanDateException() {
        super("여행 종료일은 여행 시작일 이후여야 합니다.");
    }


}
