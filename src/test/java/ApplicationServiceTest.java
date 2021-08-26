import constants.PlatformStatus;
import constants.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationServiceTest {
    ApplicationService as = new ApplicationService();

    @Test
    void addPlatform() {
        Assertions.assertNotNull(as.addPlatform("platform1", "v1", PlatformStatus.RELEASED));
    }

    @Test
    void addUser() {

    }

    @Test
    void addReview() {
    }

    @Test
    void sortPlatformsRatedBy() {
    }

    @Test
    void getAvgRatingOf() {
    }

    @Test
    void getAllReviewsOfPlatform() {
    }

    @Test
    void getPlatform() {
    }

    @Test
    void promoteUserIfNeeded() {
    }

    @Test
    void getPlatforms() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void getReviews() {
    }
}