package modules;

import exceptions.WrongRatingException;

public class Rating {
    private static final int MAX_RATING = 5;
    Platform platform;
    int rating;

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
        if (rating > MAX_RATING || rating < 1) {
            try {
                throw new WrongRatingException("Rating Exception!!");
            } catch (WrongRatingException e) {
                e.printMessage();
            }
        } else this.rating = rating;
    }
}
