package modules.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import modules.user.User;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Review {
    private User user;
    private String platformName;
    private Integer rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return this.user.equals(review.user) &&
                this.platformName.equals(review.platformName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.user, this.platformName);
    }

    @Override
    public String toString() {
        return "Review{" +
                "user=" + user.getName() +
                ", platformName='" + platformName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
