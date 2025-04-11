package hyu.erica.v1.capstone.service.util;

import hyu.erica.v1.capstone.api.code.status.ErrorStatus;
import hyu.erica.v1.capstone.api.exception.GeneralException;
import hyu.erica.v1.capstone.domain.Attraction;
import hyu.erica.v1.capstone.domain.Restaurant;
import hyu.erica.v1.capstone.repository.AttractionRepository;
import hyu.erica.v1.capstone.repository.RestaurantRepository;
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
                        .contentId(Long.parseLong(record.get("content_id")))
                        .contentName(record.get("content_name"))
                        .district(record.get("district"))
                        .latitude(parseDouble(record.get("latitude")))
                        .longitude(parseDouble(record.get("longitude")))
                        .travelDestination(record.get("travel_destination"))
                        .title(record.get("title"))
                        .subtitle(record.get("subtitle"))
                        .address(record.get("address"))
                        .contact(record.get("contact"))
                        .homepage(record.get("homepage"))
                        .transportationInfo(trimString(record.get("transportation_info"), 1000))
                        .operatingDays(record.get("operating_days"))
                        .closedDays(record.get("closed_days"))
                        .operatingHours(record.get("operating_hours"))
                        .admissionFee(record.get("admission_fee"))
                        .amenities(record.get("amenities"))
                        .imageUrl(record.get("image_url"))
                        .thumbnailImageUrl(record.get("thumbnail_image_url"))
                        .details(record.get("details"))
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
