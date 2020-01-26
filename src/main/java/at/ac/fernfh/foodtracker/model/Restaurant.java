package at.ac.fernfh.foodtracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "Restaurant")
public class Restaurant {

    @Transient
    public static final String SEQUENCE_NAME = "restaurant_sequence";

    @Id
    private Long id;

    private String name;
    private String street;
    private String number;
    private String postalCode;
    private String country;
    private float latitude;
    private float longitude;
    private String description;
    private Map<String, OpeningHours> openingHours;
    private PriceClass priceClass;
    private List<Image> images;

    /**
     * Constructor
     *
     * @param name         name of the restaurant
     * @param street       street name
     * @param number       house number
     * @param postalCode   postal code of the city
     * @param country      country
     * @param latitude     latitude of the restaurant
     * @param longitude    longitude of the restaurant
     * @param description  additional description
     * @param openingHours opening hours of the restaurant
     * @param priceClass   price class of the restaurant
     * @param images       images of the restaurant
     */
    public Restaurant(final String name, final String street, final String number, final String postalCode, final String country,
                      final float latitude, final float longitude, final String description, final Map<String, OpeningHours> openingHours,
                      final PriceClass priceClass, final List<Image> images) {
        this.name = name;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.openingHours = openingHours;
        this.priceClass = priceClass;
        this.images = images;
    }

    public Restaurant() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, OpeningHours> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Map<String, OpeningHours> openingHours) {
        this.openingHours = openingHours;
    }

    public PriceClass getPriceClass() {
        return priceClass;
    }

    public void setPriceClass(PriceClass priceClass) {
        this.priceClass = priceClass;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + id + ", name='" + name + '\'' + ", street='" + street + '\'' + ", number='" + number + '\'' + ", " +
                "postalCode='" + postalCode + '\'' + ", country='" + country + '\'' + ", latitude=" + latitude + ", longitude=" +
                longitude + ", description='" + description + '\'' + ", openingHours='" + openingHours + '\'' + ", priceClass=" +
                priceClass + '}';
    }
}
