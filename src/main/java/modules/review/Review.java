package modules.review;


import java.util.Objects;

import static constants.ReviewConstants.MAX_RATING;
import static constants.ReviewConstants.MIN_RATING;

public class Review {
       private String userName;
    private String platformName;
    private int rating;

    public Review(String user, String platform, int rating) {
        this.userName = user;
        this.platformName = platform;
        this.rating = rating;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String user) {
        this.userName = user;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating > MAX_RATING || rating < MIN_RATING) {
            System.out.println("Rating must be between 1 and 5");
        } else this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return this.getUsername().equals(review.getUsername()) &&
                this.getPlatformName().equals(review.getPlatformName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPlatformName());
    }

    @Override
    public String toString() {
        return "Review{" +
                "userName='" + userName + '\'' +
                ", platformName='" + platformName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
