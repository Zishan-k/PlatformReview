import exceptions.*;
import modules.platform.Platform;
import constants.PlatformStatus;
import modules.review.Review;
import modules.user.User;
import constants.UserType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static util.MyUtil.*;
import static constants.UserType.*;

public class ApplicationService {
    private Map<String, Integer> userVsRatingCount;
    private Map<Platform, Platform> platforms;
    private Map<User, User> users;
    private Map<Review, Review> reviews;

    public void addPlatform(String pfName, String vertical, String platformStatus) {
        if (platforms == null) platforms = new HashMap<>();
        if (!isPlatformAlreadyAdded(platforms, pfName)) {
            Platform temp = new Platform(pfName, vertical, platformStatus);
            platforms.put(temp, temp);
        } else System.out.println("Platform already added!!");
    }

    public void addUser(String uName) {
        if (users == null) users = new HashMap<>();
        if (!isUserAlreadyAdded(users, uName)) {
            User temp = new User(uName);
            users.put(temp, temp);
        } else System.out.println("User already added!!");
    }

    public void addReview(String uName, String pfName, int rating) throws MultipleReviewsException, PlatformNotReadyException {
        if (reviews == null) reviews = new HashMap<>();
        User userObj = getUserObject(users, uName);
        Platform pfObj = getPlatformObject(platforms, pfName);
        if (userObj == null || pfObj == null) {
            System.out.println("User or platform not available!!");
        } else if (pfObj.getStatus().equals(PlatformStatus.NOT_RELEASED.toString())) {
            throw new PlatformNotReadyException("Platform yet to be released!");
        } else {
            if (Objects.equals(userObj.getType(), CRITIC)) rating *= CRITIC_VALUE;
            Review review = new Review(uName, pfName, rating);
            if (!isReviewAlreadyAdded(reviews, review)) {
                reviews.put(review, review);
                pfObj.addReviews(review);
                userObj.addReviewedPlatform(pfObj);
                promoteUser(userObj);
            } else {
                throw new MultipleReviewsException("Multiple reviews not allowed!");
            }
        }
    }

    private void promoteUser(User userObj) {
        if (userObj.getReviewedPlatforms().size() > UserType.CRITIC_THRESHOLD)
            userObj.setType(UserType.CRITIC);
    }

    public Map<Platform, Platform> getTopPlatformsRatedBy(String userType) {
        return platforms;//todo
    }

    public Map<Platform, Platform> getAllPlatforms() {
        return platforms;
    }

    public Map<User, User> getAllUsers() {
        return users;
    }

    public Map<Review, Review> getAllReviews() {
        return reviews;
    }

    public float getAvgRatingOf(String platform) {
        int temp = 0, count = 0;
        Platform pfObj = getPlatformObject(platforms, platform);
        assert pfObj != null;
        for (Review review : pfObj.getReviews()) {
            temp += review.getRating();
            count++;
        }
        count = (count == 0) ? 1 : count;
        return (float) temp / count;
    }
}
