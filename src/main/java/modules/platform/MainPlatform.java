package modules.platform;

import constants.PlatformStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import modules.review.Review;

import java.util.List;

@Data
@AllArgsConstructor
abstract public class MainPlatform {
    private String name;
    private String vertical;
    private PlatformStatus status;
    private List<Review> reviews;
}
