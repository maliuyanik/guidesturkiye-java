// File: LocationDto.java

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDto {

    @JsonProperty("location_name")
    private String locationName;

    private String rating;

    @JsonProperty("comment_count")
    private String commentCount;

    private String tag1;
    private String tag2;
    private String tag3;
    private String adress;
    private String country;
    private String city;

    @JsonProperty("place_id")
    private String placeId;

    private String name;
}