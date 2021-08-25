package modules.platform;

import lombok.Builder;
import lombok.Data;
import modules.review.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class Platform{
    private String name;
    private String vertical;
    private String status;
    private List<Review> reviews;

    public void setReview(Review review) {
        if (reviews == null) reviews = new ArrayList<>();
        this.reviews.add(review);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Platform)) return false;
        Platform platform = (Platform) o;
        return getName().equals(platform.getName()) && getVertical().equals(platform.getVertical());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getVertical());
    }
}
