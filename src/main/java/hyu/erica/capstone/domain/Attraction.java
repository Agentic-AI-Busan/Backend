package hyu.erica.capstone.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Attraction {

    @Id
    @Column(name = "UC_SEQ")
    private Long ucSeq;

    @Column(name = "MAIN_TITLE")
    private String mainTitle;

    @Column(name = "GUGUN_NM")
    private String gugunNm;

    @Column(name = "LAT")
    private Double lat;

    @Column(name = "LNG")
    private Double lng;

    @Column(name = "PLACE")
    private String place;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SUBTITLE")
    private String subtitle;

    @Column(name = "MAIN_PLACE")
    private String mainPlace;

    @Column(name = "ADDR1")
    private String addr1;

    @Column(name = "ADDR2")
    private String addr2;

    @Column(name = "CNTCT_TEL")
    private String cntctTel;

    @Column(name = "HOMEPAGE_URL")
    private String homepageUrl;

    @Column(name = "TRFC_INFO")
    private String trfcInfo;

    @Column(name = "USAGE_DAY")
    private String usageDay;

    @Column(name = "HLDY_INFO")
    private String hldyInfo;

    @Column(name = "USAGE_DAY_WEEK_AND_TIME")
    private String usageDayWeekAndTime;

    @Column(name = "USAGE_AMOUNT")
    private String usageAmount;

    @Column(name = "MIDDLE_SIZE_RM1")
    private String middleSizeRm1;

    @Column(name = "MAIN_IMG_NORMAL")
    private String mainImgNormal;

    @Column(name = "MAIN_IMG_THUMB")
    private String mainImgThumb;

    @Lob
    @Column(name = "ITEMCNTNTS")
    private String itemcntnts; // ITEMCNTNTS (상세 설명)
}
