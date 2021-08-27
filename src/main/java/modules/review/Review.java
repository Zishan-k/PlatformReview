package modules.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import modules.user.User;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
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

}
