package at.ac.fernfh.foodtracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Rating")
public class Rating {

    @Id
    private String id;

    private String user;
    private String restaurant;
    private String dish;
    private String comment;
    private Date date;
    private double rating;
    private List<Image> images;

    /**
     * Constructor
     *
     * @param user       userId of the rating
     * @param restaurant restaurantId of the rating
     * @param dish       name of the dish
     * @param comment    additional comment
     * @param date       date and time of the rating
     * @param rating     rating itself
     * @param images     images of the dish
     */
    public Rating(final String user, final String restaurant, final String dish, final String comment, final Date date, final double rating,
                  final List<Image> images) {
        this.user = user;
        this.restaurant = restaurant;
        this.dish = dish;
        this.comment = comment;
        this.date = date;
        this.rating = rating;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
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
