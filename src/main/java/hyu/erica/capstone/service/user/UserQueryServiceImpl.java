package hyu.erica.capstone.service.user;

import hyu.erica.capstone.domain.enums.TripPlanStatus;
import java.util.List;

import org.springframework.stereotype.Service;

import hyu.erica.capstone.domain.TripPlan;
import hyu.erica.capstone.domain.User;
import hyu.erica.capstone.repository.TripPlanRepository;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.web.dto.user.response.MyTripPlanResponse;
import hyu.erica.capstone.web.dto.user.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

	private final TripPlanRepository tripPlanRepository;
	private final UserRepository userRepository;


	@Override
	public MyTripPlanResponse getMyTripPlans(Long userId) {
		List<TripPlan> allByUserId = tripPlanRepository.findAllByUser_IdAndTripPlanStatus(userId, TripPlanStatus.DONE);
		return MyTripPlanResponse.of(allByUserId);
	}

	@Override
	public UserInfoResponse getProfile(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));

		return UserInfoResponse.of(user.getId() , user.getName(), user.getNickname(),
				user.getEmail(), user.getBirthday(), user.getGender(),
				user.getProfileImage(), user.getPhoneNumber());
	}
}
