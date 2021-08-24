package modules.review;

import exceptions.WrongRatingException;
import modules.platform.Platform;
import modules.user.User;

public class Review {
    private static final int MAX_RATING = 5;
    private static final int MIN_RATING = 1;
    private User user;
    private Platform platform;
    private int rating;

    public Review(User user, Platform platform, int rating) {
        this.user = user;
        this.platform = platform;
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating > MAX_RATING || rating < MIN_RATING) {
            try {
                throw new WrongRatingException("Illegal Rating Exception!!");
            } catch (WrongRatingException e) {
                e.printMessage();
            }
        } else this.rating = rating;
    }
}
