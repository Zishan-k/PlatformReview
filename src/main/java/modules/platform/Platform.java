package modules.platform;

import constants.PlatformStatus;
import modules.review.Review;

import java.util.ArrayList;
import java.util.List;

public class Platform {
    private String name;
    private String vertical;
    private String status;
    private List<Review> reviews;

    public Platform(String name, String vertical, String status) {
        this.name = name;
        this.vertical = vertical;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReviews(Review review) {
        if (reviews == null) reviews = new ArrayList<>();
        this.reviews.add(review);
    }

    @Override
    public String toString() {
        return "Platform{" +
                "name='" + name + '\'' +
                ", vertical='" + vertical + '\'' +
                ", status='" + status + '\'' +
                '}' + '\n';
    }
}
