package modules.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import static constants.ReviewConstants.MAX_RATING;
import static constants.ReviewConstants.MIN_RATING;

@Getter
@Setter
@AllArgsConstructor
public class Review {
    private String userName;
    private String platformName;
    private int rating;

    public void setRating(int rating) {
        if (rating < MAX_RATING || rating > MIN_RATING)
            this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return this.userName.equals(review.userName) &&
                this.platformName.equals(review.platformName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userName, this.platformName);
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
