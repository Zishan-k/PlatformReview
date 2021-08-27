import constants.PlatformStatus;
import constants.Rating;
import constants.UserType;
import exceptions.MultipleReviewsException;
import exceptions.PlatformNotReadyException;
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
    String[] platforms = {"Wint Wealth", "BuyUCoin", "5paisa", "Zerodha","IB", "Grip"};
    String[] users = {"Adam", "Patrick", "Trump", "Salman"};
    String[] verticals = {"Alternative Investment", "Cryptocurrency", "Equity"};

    @BeforeEach
    public void init() {
        as.addPlatform(platforms[0], verticals[0], PlatformStatus.RELEASED);
        as.addPlatform(platforms[1], verticals[1], PlatformStatus.RELEASED);
        as.addPlatform(platforms[2], verticals[2], PlatformStatus.RELEASED);
        as.addPlatform(platforms[3], verticals[0], PlatformStatus.RELEASED);
        as.addPlatform(platforms[4], verticals[0], PlatformStatus.RELEASED);
        as.addPlatform(platforms[5], verticals[0], PlatformStatus.NOT_RELEASED);

        as.addUser(users[0], UserType.VIEWER);
        as.addUser(users[1], UserType.ADMIN);
        as.addUser(users[2], UserType.EXPERT);
        as.addUser(users[3], UserType.CRITIC);

        as.addReview(users[0], platforms[2], Rating.TWO);
        as.addReview(users[1], platforms[2], Rating.FIVE);
        as.addReview(users[2], platforms[2], Rating.FOUR);
        as.addReview(users[2], platforms[1], Rating.ONE);

        as.addReview(users[3], platforms[0], Rating.FOUR);
        as.addReview(users[3], platforms[3], Rating.THREE);
        as.addReview(users[3], platforms[4], Rating.FIVE);
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
    void addReviewTest1() {
        int beforeSize = as.getPlatform(platforms[0]).getReviews().size();
        as.addReview(users[0], platforms[0], Rating.FOUR);
        int afterSize = as.getPlatform(platforms[0]).getReviews().size();
        Assertions.assertNotEquals(beforeSize, afterSize);
    }

    @Test
    void addReviewTest2(){
        Exception exception = Assertions.assertThrows(MultipleReviewsException.class,
                () -> as.addReview(users[0], platforms[2], Rating.FOUR));
        String expected = "Multiple reviews not allowed!";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addReviewTest3(){
        Exception exception = Assertions.assertThrows(PlatformNotReadyException.class,
                () -> as.addReview(users[0], platforms[5], Rating.FOUR));
        String expected = "Platform not ready";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sortPlatformsRatedBy() {
        List<Platform> sortedPlatforms = as.sortPlatformsRatedBy(UserType.CRITIC, verticals[0]);
        Platform firstPlatform = sortedPlatforms.get(0);
        Platform secondPlatform = sortedPlatforms.get(1);
        Platform thirdPlatform = sortedPlatforms.get(2);

        Assertions.assertEquals(10, getSum(firstPlatform.getReviews()));
        Assertions.assertEquals(8, getSum(secondPlatform.getReviews()));
        Assertions.assertEquals(6, getSum(thirdPlatform.getReviews()));

    }

    @Test
    void getAvgRatingOf() {
        OptionalDouble avg = as.getAvgRatingOf(platforms[0]);
        Assertions.assertEquals(8.0, avg.getAsDouble());
    }

    @Test
    void getAllReviewsOfPlatform() {
        List<Review> reviews = as.getAllReviewsOfPlatform(platforms[2]);
        Assertions.assertEquals(platforms[2], reviews.get(0).getPlatformName());
        Assertions.assertEquals(platforms[2], reviews.get(1).getPlatformName());
        Assertions.assertEquals(platforms[2], reviews.get(2).getPlatformName());
    }

    @Test
    void promoteUserIfNeeded() {
        UserType before = as.getUser(users[0]).getType();
        as.addReview(users[0], platforms[3], Rating.FOUR);
        as.addReview(users[0], platforms[0], Rating.FOUR);
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

    @Test
    void updatePlatformStatus(){
        Assertions.assertTrue(as.updatePlatformStatus(platforms[5], PlatformStatus.RELEASED));
    }

    private int getSum(List<Review> reviews){
        return reviews.stream()
                .mapToInt(Review::getRating)
                .sum();
    }
}