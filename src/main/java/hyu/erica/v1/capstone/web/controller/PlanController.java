package hyu.erica.v1.capstone.web.controller;


import hyu.erica.v1.capstone.api.ApiResponse;
import hyu.erica.v1.capstone.api.code.status.SuccessStatus;
import hyu.erica.v1.capstone.service.tripPlan.TripPlanCommandService;
import hyu.erica.v1.capstone.service.tripPlan.TripPlanQueryService;
import hyu.erica.v1.capstone.web.dto.tripPlan.request.UpdateAllScheduleOrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "여행 계획", description = "여행 계획 관련 API")
@CrossOrigin
@RestController
@RequestMapping("/api/trip-plans")
@RequiredArgsConstructor
public class PlanController {

    private final TripPlanQueryService tripPlanQueryService;
    private final TripPlanCommandService tripPlanCommandService;

    // 일정 제공
    @Operation(summary = "일정 제공", description = """
            ### 일정 제공 API 
            일정 확인을 위한 API입니다. 조회 하려는 여행 계획 ID를 Path Variable로 입력해주세요.
            
            ### Path Variables
            - tripPlanId: 여행 계획 ID
            
                  """)
    @GetMapping("/{tripPlanId}")
    public ApiResponse<?> getPlan(@PathVariable Long tripPlanId) {
        // 일정 제공
        return ApiResponse.onSuccess(SuccessStatus._OK, tripPlanQueryService.getTripPlan(tripPlanId));
    }

    // 일자 별 일정 조회
    @Operation(summary = "일자 별 일정 조회", description = """
            ### 일자 별 일정 조회 API 
            일자 별 일정을 조회하기 위한 API입니다. 조회 하려는 여행 계획 ID를 Path Variable로 입력해주세요.
            
            ### Path Variables
            - tripPlanId: 여행 계획 ID
           
            ### Request Parameters
            - day: 조회하려는 일자
                  """)
    @GetMapping("/{tripPlanId}/day/{day}")
    public ApiResponse<?> getPlanByDate(@PathVariable Long tripPlanId, @PathVariable Integer day) {
        // 일자 별 일정 조회
        return ApiResponse.onSuccess(SuccessStatus._OK, tripPlanQueryService.getTripPlanByDay(tripPlanId, day));
    }

    // 일자 별 일정 조회
    @Operation(summary = "일정 전체 동기화 (추가/수정/삭제 포함)", description = """
            ### 일정 편집 API (드래그 앤 드랍으로 순서 변경)
            여행 계획에 포함된 전체 일정을 드래그 앤 드랍 후,
            최종 상태를 서버에 반영하는 API입니다.

             - 이 API는 **순서 변경**, **일정 추가**, **일정 삭제**까지 전부 반영합니다.
            - 클라이언트는 사용자가 수정한 전체 일정을 items 배열로 정렬된 상태로 전송하면 됩니다.
            - 서버는 기존 일정을 덮어쓰며, 요청에 포함되지 않은 일정은 삭제됩니다.

            ### 사용 예시
            - 1일차 2번째 일정이 1번째로 옮겨짐
            - 새로운 레스토랑 일정이 추가됨
            - 기존에 있던 일정 중 하나가 삭제됨 (요청에 안 보내면 됨)

            ### Request Body 예시
            ```json
            {
                "tripPlanId": 42,
                "items": [
                    {
                        "id": 1,                           // 기존 일정 (수정)
                        "dayNumber": 1,
                        "orderInDay": 1,
                        "placeType": "ATTRACTION",
                        "attractionId": 101,
                        "memo": "사진 많이 찍자"
                    },
                    {
                        "dayNumber": 1,                    // 신규 추가 일정 (id 없음)
                        "orderInDay": 2,
                        "placeType": "RESTAURANT",
                        "restaurantId": 55,
                        "memo": "저녁 예약 필수"
                    }
                    // id=2 일정이 기존에 있었는데 요청에 없음 → 삭제 처리됨
                ]
            }
            ```
            ### ScheduleOrderItemDTO 설명
            - id : 기존 일정이면 포함, 새 일정이면 생략
            - dayNumber : 해당 일정이 포함될 날짜 (1일부터 시작)
            - orderInDay : 하루 내에서의 순서 (1부터 시작)
            - placeType : 장소 타입 (ATTRACTION or RESTAURANT)
            - attractionId / restaurantId : 장소 ID (placeType에 따라 하나만 사용)
            - memo : 메모 내용 (optional)
                  """)
    @PutMapping("")
    public ApiResponse<?> editPlan(@RequestBody UpdateAllScheduleOrderRequest request) {
        tripPlanCommandService.updatePlan(request);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }


//
//    // 채팅 입력 -> AI API 호출
//    @PostMapping("/chat")
//    public ApiResponse<?> chat() {
//        // 채팅 입력 -> AI API 호출
//        return null;
//    }
}
