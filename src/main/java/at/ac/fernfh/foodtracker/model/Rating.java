package at.ac.fernfh.foodtracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Rating")
public class Rating {

    @Transient
    public static final String SEQUENCE_NAME = "rating_sequence";

    @Id
    private Long id;

    private Long user;
    private Long restaurant;
    private String dish;
    private String comment;
    private Date date;
    private double rating;
    private List<Image> images;

    /**
     * Constructor
     *
     * @param user       user of the rating
     * @param restaurant restaurant of the rating
     * @param dish       name of the dish
     * @param comment    additional comment
     * @param date       date and time of the rating
     * @param rating     rating itself
     * @param images     images of the dish
     */
    public Rating(final Long user, final Long restaurant, final String dish, final String comment, final Date date, final double rating,
                  final List<Image> images) {
        this.user = user;
        this.restaurant = restaurant;
        this.dish = dish;
        this.comment = comment;
        this.date = date;
        this.rating = rating;
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Long restaurant) {
        this.restaurant = restaurant;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", user=" + user + ", restaurant=" + restaurant + ", dish='" + dish + '\'' + ", comment='" +
                comment + '\'' + ", date=" + date + ", rating=" + rating + '}';
    }
}
