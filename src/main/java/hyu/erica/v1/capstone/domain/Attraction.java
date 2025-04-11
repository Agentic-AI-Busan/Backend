package hyu.erica.v1.capstone.domain;

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
    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "content_name")
    private String contentName;

    @Column(name = "district")
    private String district;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "travel_destination")
    private String travelDestination;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @Column(name = "homepage")
    private String homepage;

    @Column(name = "transportation_info", columnDefinition = "TEXT")
    private String transportationInfo;

    @Column(name = "operating_days")
    private String operatingDays;

    @Column(name = "closed_days")
    private String closedDays;

    @Column(name = "operating_hours")
    private String operatingHours;

    @Column(name = "admission_fee")
    private String admissionFee;

    @Column(name = "amenities")
    private String amenities;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "thumbnail_image_url")
    private String thumbnailImageUrl;

    @Column(name = "details", columnDefinition = "LONGTEXT")
    private String details;

}
