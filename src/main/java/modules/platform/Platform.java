package modules.platform;

import constants.PlatformStatus;
import lombok.Builder;
import lombok.Data;
import modules.review.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class Platform extends MainPlatform {
    private String name;
    private String vertical;
    private PlatformStatus status;
    private List<Review> reviews;

    public Platform(String name, String vertical, PlatformStatus status, List<Review> reviews) {
        super(name, vertical, status, reviews);
        this.name = name;
        this.vertical = vertical;
        this.status = status;
        this.reviews = reviews;
    }

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

    public boolean isReleased() {
        return status.equals(PlatformStatus.RELEASED);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getVertical());
    }
}
