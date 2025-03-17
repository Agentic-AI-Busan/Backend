package hyu.erica.capstone.service.util;

import static java.lang.Double.parseDouble;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.domain.Attraction;
import hyu.erica.capstone.domain.Restaurant;
import hyu.erica.capstone.repository.AttractionRepository;
import hyu.erica.capstone.repository.RestaurantRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class CsvImportService {

    private final AttractionRepository attractionRepository;
    private final RestaurantRepository restaurantRepository;


    public void importAttraction(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            List<Attraction> touristSpots = new ArrayList<>();

            for (CSVRecord record : csvParser) {
                Attraction spot = Attraction.builder()
                        .ucSeq(Long.parseLong(record.get("UC_SEQ")))
                        .mainTitle(record.get("MAIN_TITLE"))
                        .gugunNm(record.get("GUGUN_NM"))
                        .lat(parseDouble(record.get("LAT")))
                        .lng(parseDouble(record.get("LNG")))
                        .place(record.get("PLACE"))
                        .title(record.get("TITLE"))
                        .subtitle(record.get("SUBTITLE"))
                        .mainPlace(record.get("MAIN_PLACE"))
                        .addr1(record.get("ADDR1"))
                        .addr2(record.get("ADDR2"))
                        .cntctTel(record.get("CNTCT_TEL"))
                        .homepageUrl(record.get("HOMEPAGE_URL"))
                        .trfcInfo(trimString(record.get("TRFC_INFO"), 1000))
                        .usageDay(record.get("USAGE_DAY"))
                        .hldyInfo(record.get("HLDY_INFO"))
                        .usageDayWeekAndTime(record.get("USAGE_DAY_WEEK_AND_TIME"))
                        .usageAmount(record.get("USAGE_AMOUNT"))
                        .middleSizeRm1(record.get("MIDDLE_SIZE_RM1"))
                        .mainImgNormal(record.get("MAIN_IMG_NORMAL"))
                        .mainImgThumb(record.get("MAIN_IMG_THUMB"))
                        .build();

                touristSpots.add(spot);
            }
            attractionRepository.saveAll(touristSpots);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(ErrorStatus._FILE_INPUT_ERROR);
        }
    }

    public void importRestaurant(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            List<Restaurant> batchList = new ArrayList<>();

            for (CSVRecord record : csvParser) {
                Restaurant restaurant = Restaurant.builder()
                        .id(Long.parseLong(record.get("RSTR_ID")))
                        .restaurantName(record.get("RSTR_NM"))
                        .roadAddress(record.get("RSTR_RDNMADR"))
                        .lotAddress(record.get("RSTR_LNNO_ADRES"))
                        .latitude(parseDouble(record.get("RSTR_LA")))
                        .longitude(parseDouble(record.get("RSTR_LO")))
                        .phoneNumber(record.get("RSTR_TELNO"))
                        .businessStatus(record.get("BSNS_STATM_BZCND_NM"))
                        .businessType(record.get("BSNS_LCNC_NM"))
                        .description(record.get("RSTR_INTRCN_CONT"))
                        .area(record.get("AREA_NM"))
                        .seatCount(parseInteger(record.get("SEAT_CNT")))
                        .hasParking(record.get("PRKG_POS_YN"))
                        .hasWifi(record.get("WIFI_OFR_YN"))
                        .hasDiscount(record.get("DCRN_YN"))
                        .allowsPets(record.get("PET_ENTRN_POSBL_YN"))
                        .hasForeignMenu(record.get("FGGG_MENU_OFR_YN"))
                        .restroomInfo(record.get("TLROM_INFO_CN"))
                        .holidayInfo(record.get("RESTDY_INFO_CN"))
                        .businessHours(record.get("BSNS_TM_CN"))
                        .offersHomeDelivery(record.get("HMDLV_SALE_YN"))
                        .offersDelivery(record.get("DELV_SRVIC_YN"))
                        .homepageUrl(record.get("HMPG_URL"))
                        .nearbyLandmarkName(record.get("CRCMF_LDMARK_NM"))
                        .nearbyLandmarkLatitude(parseDouble(record.get("CRCMF_LDMARK_LA")))
                        .nearbyLandmarkLongitude(parseDouble(record.get("CRCMF_LDMARK_LO")))
                        .nearbyLandmarkDistance(parseDouble(record.get("CRCMF_LDMARK_DIST")))
                        .tripAdvisorRating(parseDouble(record.get("TRPDVSR_GRAD")))
                        .cTripRating(parseDouble(record.get("CTRIP_GRAD")))
                        .naverRating(parseDouble(record.get("NAVER_GRAD")))
                        .build();
                batchList.add(restaurant);
            }

            restaurantRepository.saveAll(batchList);

        } catch (Exception e) {
            throw new GeneralException(ErrorStatus._FILE_INPUT_ERROR);
        }
    }

    private String trimString(String value, int maxLength) {
        if (value == null) return null;
        return value.length() > maxLength ? value.substring(0, maxLength) : value;
    }

    private Double parseDouble(String value) {
        return (value == null || value.trim().isEmpty()) ? null : Double.parseDouble(value);
    }

    private Integer parseInteger(String value) {
        return (value == null || value.trim().isEmpty()) ? null : Integer.parseInt(value);
    }



}
