package hyu.erica.capstone.domain;

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
@Table(name = "restaurant") // 테이블명 설정
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @Column(name = "RSTR_ID")
    private Long rstrId; // 식당 고유 ID

    @Column(name = "RSTR_NM")
    private String rstrNm; // 식당 이름

    @Column(name = "RSTR_RDNMADR")
    private String rstrRdnmAdr; // 도로명 주소

    @Column(name = "RSTR_LNNO_ADRES")
    private String rstrLnnoAdres; // 지번 주소

    @Column(name = "RSTR_LA")
    private Double rstrLa; // 위도

    @Column(name = "RSTR_LO")
    private Double rstrLo; // 경도

    @Column(name = "RSTR_TELNO")
    private String rstrTelNo; // 전화번호

    @Column(name = "BSNS_STATM_BZCND_NM")
    private String bsnsStatmBzcndNm; // 영업 상태

    @Column(name = "BSNS_LCNC_NM")
    private String bsnsLcncNm; // 업종명

    @Column(name = "RSTR_INTRCN_CONT", columnDefinition = "TEXT")
    private String rstrIntrcnCont; // 식당 소개

    @Column(name = "AREA_NM")
    private String areaNm; // 지역명

    @Column(name = "PRDL_SEAT_CNT")
    private Integer prdlSeatCnt; // 예상 좌석 수

    @Column(name = "SEAT_CNT")
    private Integer seatCnt; // 실제 좌석 수

    @Column(name = "PRKG_POS_YN")
    private String prkgPosYn; // 주차 가능 여부 (Y/N)

    @Column(name = "WIFI_OFR_YN")
    private String wifiOfrYn; // 와이파이 제공 여부 (Y/N)

    @Column(name = "DCRN_YN")
    private String dcrnYn; // 할인 여부 (Y/N)

    @Column(name = "PET_ENTRN_POSBL_YN")
    private String petEntrnPosblYn; // 반려동물 동반 가능 여부 (Y/N)

    @Column(name = "FGGG_MENU_OFR_YN")
    private String fgggMenuOfrYn; // 외국인 메뉴 제공 여부 (Y/N)

    @Column(name = "TLROM_INFO_CN")
    private String tlromInfoCn; // 화장실 정보

    @Column(name = "RESTDY_INFO_CN")
    private String restdyInfoCn; // 휴무일 정보

    @Column(name = "BSNS_TM_CN")
    private String bsnsTmCn; // 영업시간

    @Column(name = "HMDLV_SALE_YN")
    private String hmdlvSaleYn; // 홈딜리버리 판매 여부 (Y/N)

    @Column(name = "DSBR_CVNTL_YN")
    private String dsbrCvntlYn; // 장애인 편의시설 제공 여부 (Y/N)

    @Column(name = "DELV_SRVIC_YN")
    private String delvSrvicYn; // 배달 서비스 제공 여부 (Y/N)

    @Column(name = "RSRV_MTHD_NM")
    private String rsrvMthdNm; // 예약 방법

    @Column(name = "ONLINE_RSRV_INFO_CN")
    private String onlineRsrvInfoCn; // 온라인 예약 정보

    @Column(name = "HMPG_URL")
    private String hmpgUrl; // 홈페이지 URL

    @Column(name = "CRCMF_LDMARK_NM")
    private String crcmfLdmarkNm; // 근처 랜드마크 이름

    @Column(name = "CRCMF_LDMARK_LA")
    private Double crcmfLdmarkLa; // 근처 랜드마크 위도

    @Column(name = "CRCMF_LDMARK_LO")
    private Double crcmfLdmarkLo; // 근처 랜드마크 경도

    @Column(name = "CRCMF_LDMARK_DIST")
    private Double crcmfLdmarkDist; // 근처 랜드마크 거리

    @Column(name = "KIOSK_YN")
    private String kioskYn; // 키오스크 여부 (Y/N)

    @Column(name = "MB_PMAMT_YN")
    private String mbPmamtYn; // 모바일 결제 가능 여부 (Y/N)

    @Column(name = "SMORDER_YN")
    private String smorderYn; // 스마트 오더 가능 여부 (Y/N)

    @Column(name = "REPRSNT_MENU_NM")
    private String reprsntMenuNm; // 대표 메뉴

    @Column(name = "AWARD_INFO_DSCRN")
    private String awardInfoDscrn; // 수상 정보

    @Column(name = "RTI_IDEX")
    private Double rtiIdex; // RTI 지수

    @Column(name = "ONLINE_CONV_PRGS_YN")
    private String onlineConvPrgsYn; // 온라인 컨버전 진행 여부 (Y/N)

    @Column(name = "ACCPN_STTUS_IDEX")
    private Double accpnSttusIdex; // 수용 상태 지수

    @Column(name = "RATING_IDEX")
    private Double ratingIdex; // 평점 지수

    @Column(name = "TRPDVSR_GRAD")
    private Double trpdvsrGrad; // 트립어드바이저 평점

    @Column(name = "CTRIP_GRAD")
    private Double ctripGrad; // 씨트립 평점

    @Column(name = "NAVER_GRAD")
    private Double naverGrad; // 네이버 평점
}
