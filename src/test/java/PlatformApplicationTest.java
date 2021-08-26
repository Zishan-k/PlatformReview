import constants.PlatformStatus;
import constants.UserType;
import modules.platform.Platform;
import modules.review.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.OptionalDouble;

class PlatformApplicationTest {
    PlatformApplication as = new PlatformApplication();
    String[] platforms = {"p1", "p2", "p3", "p4"};
    String[] users = {"u1", "u2", "u3", "u4"};

    @BeforeEach
    public void init() {
        as.addPlatform(platforms[0], "vv", PlatformStatus.RELEASED);
        as.addPlatform(platforms[1], "vv", PlatformStatus.RELEASED);
        as.addPlatform(platforms[2], "vv", PlatformStatus.RELEASED);
        as.addPlatform(platforms[3], "vv", PlatformStatus.RELEASED);

        as.addUser(users[0], UserType.VIEWER); // invalid user ?
        as.addUser(users[1], UserType.CRITIC);
        as.addUser(users[2], UserType.CRITIC);

        as.addReview(users[1], platforms[0], Rating.FOUR);
        as.addReview(users[1], platforms[1], Rating.TWO);
        as.addReview(users[1], platforms[2], Rating.FIVE);
        as.addReview(users[2], platforms[0], Rating.FOUR);
        as.addReview(users[2], platforms[1], Rating.FOUR);
        as.addReview(users[2], platforms[2], Rating.FOUR);
    }

    @Test
    void getPlatform() {
        Assertions.assertNotNull(as.getPlatform(platforms[0]));
    }

    @Test
    void getUser(){
        Assertions.assertNotNull(as.getUser(users[0]));
    }

    @Test
    void addReview() {
        int beforeSize = as.getPlatform(platforms[0]).getReviews().size();
        as.addReview(users[0], platforms[0], Rating.FOUR);
        int afterSize = as.getPlatform(platforms[0]).getReviews().size();
        Assertions.assertNotEquals(beforeSize, afterSize);
    }

    @Test
    void sortPlatformsRatedBy() {
        List<Platform> sortedPlatforms = as.sortPlatformsRatedBy(UserType.CRITIC, "vv");
        Platform firstPlatform = sortedPlatforms.get(0);
        Platform secondPlatform = sortedPlatforms.get(1);
        Platform thirdPlatform = sortedPlatforms.get(2);

        Assertions.assertEquals(18, getSum(firstPlatform.getReviews()));
        Assertions.assertEquals(16, getSum(secondPlatform.getReviews()));
        Assertions.assertEquals(12, getSum(thirdPlatform.getReviews()));

    }

    @Test
    void getAvgRatingOf() {
        OptionalDouble avg = as.getAvgRatingOf(platforms[0]);
        Assertions.assertEquals(8.0, avg.getAsDouble());
    }

    @Test
    void getAllReviewsOfPlatform() {
        List<Review> reviews = as.getAllReviewsOfPlatform(platforms[0]);
        Assertions.assertEquals(platforms[0], reviews.get(0).getPlatformName());
        Assertions.assertEquals(platforms[0], reviews.get(1).getPlatformName());
    }

    @Test
    void promoteUserIfNeeded() {
        UserType before = as.getUser(users[0]).getType();
        as.addReview(users[0], platforms[3], Rating.FOUR);
        as.addReview(users[0], platforms[2], Rating.FOUR);
        as.addReview(users[0], platforms[1], Rating.FOUR);
        UserType after = as.getUser(users[0]).getType();
        Assertions.assertNotEquals(before, after);
    }

    @Test
    void getPlatforms() {
        Assertions.assertNotEquals(0, as.getPlatforms().size());
    }

    @Test
    void getUsers() {
        Assertions.assertNotEquals(0, as.getUsers().size());
    }

    private int getSum(List<Review> reviews){
        return reviews.stream()
                .mapToInt(review-> review.getRating())
                .sum();
    }
}