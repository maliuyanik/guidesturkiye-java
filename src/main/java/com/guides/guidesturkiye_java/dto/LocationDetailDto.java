// File: LocationDetailDto.java

public class LocationDetailDto {
    private double lat;
    private double lng;
    private String about;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String website;
    private String image;

    @JsonProperty("formatted_address")
    private String formattedAddress;

    private GeometryDto geometry;

    @JsonProperty("image_id")
    private String imageId;
}