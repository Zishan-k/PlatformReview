package modules.platform;

import constants.PlatformStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import modules.review.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class Platform {
    private String name;
    private String vertical;
    private PlatformStatus status;
    private final List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        reviews.add(review);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Platform)) return false;
        Platform platform = (Platform) o;
        return getName().equals(platform.getName()) && getVertical().equals(platform.getVertical());
    }

    public boolean isReleased() {
        return status.equals(PlatformStatus.RELEASED);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getVertical());
    }
}
