package hyu.erica.capstone.service.user;

import hyu.erica.capstone.web.dto.user.response.MyTripPlanResponse;
import hyu.erica.capstone.web.dto.user.response.UserInfoResponse;

public interface UserQueryService {

	MyTripPlanResponse getMyTripPlans(Long userId);
	UserInfoResponse getProfile(Long userId);
}
