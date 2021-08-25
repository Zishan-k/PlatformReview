import exceptions.*;
import modules.platform.Platform;
import constants.PlatformStatus;
import modules.review.Review;
import modules.user.User;
import constants.UserType;

import java.util.*;

import static util.MyUtil.*;
import static constants.UserType.*;

public class ApplicationService {
    private Map<String, Integer> userVsRatingCount;
    private Set<Platform> platforms;
    private Set<User> users;
    private Set<Review> reviews;

    public boolean addPlatform(String pfName, String vertical, String platformStatus) {
        if (platforms == null) platforms = new HashSet<>();
        if (!isPlatformAlreadyAdded(platforms, pfName) && (!pfName.equals("") || !vertical.equals("") || !platformStatus.equals(""))) {
            Platform temp = Platform.builder()
                    .name(pfName)
                    .vertical(vertical)
                    .status(platformStatus).build();
            platforms.add(temp);
            return true;
        }
        return false;
    }

    public void addUser(String uName) {
        if (users == null) users = new HashSet<>();
        if (!isUserAlreadyAdded(users, uName) && !uName.equals("")) {
            User temp = new User(uName);
            users.add(temp);
        }
    }

    public void addReview(String uName, String pfName, int rating)
            throws MultipleReviewsException, PlatformNotReadyException {
        if (!uName.equals("") || !pfName.equals("") || !isRatingValid(rating)) {
            if (reviews == null) reviews = new HashSet<>();
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
                    reviews.add(review);
                    pfObj.setReview(review);
                    userObj.addReviewedPlatform(pfObj);
                    promoteUser(userObj);
                } else {
                    throw new MultipleReviewsException("Multiple reviews not allowed!");
                }
            }
        }
    }

    private void promoteUser(User userObj) {
        if (userObj.getReviewedPlatforms().size() >= UserType.CRITIC_THRESHOLD)
            userObj.setType(UserType.CRITIC);
    }

    public Set<Platform> getTopPlatformsRatedBy(String userType) {
        return platforms;//todo
    }

    public Set<Platform> getAllPlatforms() {
        return platforms;
    }

    public Set<User> getAllUsers() {
        return users;
    }

    public Set<Review> getAllReviews() {
        return reviews;
    }

    public float getAvgRatingOf(String platform) {
        int temp = 0, count = 0;
        Platform pfObj = getPlatformObject(platforms, platform);
        for (Review review : Objects.requireNonNull(pfObj).getReviews()) {
            temp += review.getRating();
            count++;
        }
        count = (count == 0) ? 1 : count;
        return (float) temp / count;
    }

    public List<Review> getAllReviewsOfPlatform(String platform) {
        return Objects.requireNonNull(getPlatformObject(platforms, platform)).getReviews();
    }
}
