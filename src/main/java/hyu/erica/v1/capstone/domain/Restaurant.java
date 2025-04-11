package hyu.erica.v1.capstone.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @Column(name = "RSTR_ID")
    private Long id; // 식당 ID

    @Column(name = "RSTR_NM")
    private String restaurantName; // 식당 이름

    @Column(name = "RSTR_RDNMADR")
    private String roadAddress; // 도로명 주소

    @Column(name = "RSTR_LNNO_ADRES")
    private String lotAddress; // 지번 주소

    @Column(name = "RSTR_LA")
    private Double latitude; // 위도

    @Column(name = "RSTR_LO")
    private Double longitude; // 경도

    @Column(name = "RSTR_TELNO")
    private String phoneNumber; // 전화번호

    @Column(name = "BSNS_STATM_BZCND_NM")
    private String businessStatus; // 영업 상태

    @Column(name = "BSNS_LCNC_NM")
    private String businessType; // 업종명

    @Column(name = "RSTR_INTRCN_CONT", columnDefinition = "TEXT")
    private String description; // 소개

    @Column(name = "AREA_NM")
    private String area; // 지역명

    @Column(name = "PRDL_SEAT_CNT")
    private Integer predictedSeatCount; // 예상 좌석 수

    @Column(name = "SEAT_CNT")
    private Integer seatCount; // 실제 좌석 수

    @Column(name = "PRKG_POS_YN")
    private String hasParking; // 주차 가능 여부 (Y/N)

    @Column(name = "WIFI_OFR_YN")
    private String hasWifi; // 와이파이 제공 여부 (Y/N)

    @Column(name = "DCRN_YN")
    private String hasDiscount; // 할인 여부 (Y/N)

    @Column(name = "PET_ENTRN_POSBL_YN")
    private String allowsPets; // 반려동물 동반 가능 여부 (Y/N)

    @Column(name = "FGGG_MENU_OFR_YN")
    private String hasForeignMenu; // 외국인 메뉴 제공 여부 (Y/N)

    @Column(name = "TLROM_INFO_CN")
    private String restroomInfo; // 화장실 정보

    @Column(name = "RESTDY_INFO_CN")
    private String holidayInfo; // 휴무일 정보

    @Column(name = "BSNS_TM_CN")
    private String businessHours; // 영업시간

    @Column(name = "HMDLV_SALE_YN")
    private String offersHomeDelivery; // 홈딜리버리 판매 여부 (Y/N)

    @Column(name = "DSBR_CVNTL_YN")
    private String hasAccessibility; // 장애인 편의시설 제공 여부 (Y/N)

    @Column(name = "DELV_SRVIC_YN")
    private String offersDelivery; // 배달 서비스 제공 여부 (Y/N)

    @Column(name = "RSRV_MTHD_NM")
    private String reservationMethod; // 예약 방법

    @Column(name = "ONLINE_RSRV_INFO_CN")
    private String onlineReservationInfo; // 온라인 예약 정보

    @Column(name = "HMPG_URL")
    private String homepageUrl; // 홈페이지 URL

    @Column(name = "CRCMF_LDMARK_NM")
    private String nearbyLandmarkName; // 근처 랜드마크 이름

    @Column(name = "CRCMF_LDMARK_LA")
    private Double nearbyLandmarkLatitude; // 근처 랜드마크 위도

    @Column(name = "CRCMF_LDMARK_LO")
    private Double nearbyLandmarkLongitude; // 근처 랜드마크 경도

    @Column(name = "CRCMF_LDMARK_DIST")
    private Double nearbyLandmarkDistance; // 근처 랜드마크 거리

    @Column(name = "KIOSK_YN")
    private String hasKiosk; // 키오스크 여부 (Y/N)

    @Column(name = "MB_PMAMT_YN")
    private String allowsMobilePayment; // 모바일 결제 가능 여부 (Y/N)

    @Column(name = "SMORDER_YN")
    private String supportsSmartOrder; // 스마트 오더 가능 여부 (Y/N)

    @Column(name = "REPRSNT_MENU_NM")
    private String representativeMenu; // 대표 메뉴

    @Column(name = "AWARD_INFO_DSCRN")
    private String awardDescription; // 수상 정보

    @Column(name = "RTI_IDEX")
    private Double rtiIndex; // RTI 지수

    @Column(name = "ONLINE_CONV_PRGS_YN")
    private String onlineConversionStatus; // 온라인 컨버전 진행 여부 (Y/N)

    @Column(name = "ACCPN_STTUS_IDEX")
    private Double acceptanceStatusIndex; // 수용 상태 지수

    @Column(name = "RATING_IDEX")
    private Double ratingIndex; // 평점 지수

    @Column(name = "TRPDVSR_GRAD")
    private Double tripAdvisorRating; // 트립어드바이저 평점

    @Column(name = "CTRIP_GRAD")
    private Double cTripRating; // 씨트립 평점

    @Column(name = "NAVER_GRAD")
    private Double naverRating; // 네이버 평점
}
