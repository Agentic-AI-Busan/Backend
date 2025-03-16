package hyu.erica.capstone.service;

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
                        .rstrId(Long.parseLong(record.get("RSTR_ID")))
                        .rstrNm(record.get("RSTR_NM"))
                        .rstrRdnmAdr(record.get("RSTR_RDNMADR"))
                        .rstrLnnoAdres(record.get("RSTR_LNNO_ADRES"))
                        .rstrLa(parseDouble(record.get("RSTR_LA")))
                        .rstrLo(parseDouble(record.get("RSTR_LO")))
                        .rstrTelNo(record.get("RSTR_TELNO"))
                        .bsnsStatmBzcndNm(record.get("BSNS_STATM_BZCND_NM"))
                        .bsnsLcncNm(record.get("BSNS_LCNC_NM"))
                        .rstrIntrcnCont(record.get("RSTR_INTRCN_CONT"))
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



}
