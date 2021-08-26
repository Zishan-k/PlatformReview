import exceptions.*;
import modules.platform.Platform;
import modules.platform.PlatformReviewsSorter;
import modules.review.Review;
import modules.user.User;

import java.util.*;

import static util.MyUtil.*;
import static constants.UserType.*;

public class ApplicationService {
    private Set<Platform> platforms;
    private Set<User> users;
    private Set<Review> reviews;

    public boolean addPlatform(String pfName, String vertical, String platformStatus) {
        if (platforms == null) platforms = new HashSet<>();
        if (!isPlatformAlreadyAdded(platforms, pfName, vertical) && (!pfName.equals("") && !vertical.equals("") && !platformStatus.equals(""))) {
            Platform temp = Platform.builder()
                    .name(pfName)
                    .vertical(vertical)
                    .status(platformStatus).build();
            platforms.add(temp);
            return true;
        }
        return false;
    }

    public void addUser(@NonNull String userName, UserType userType) {
        if (!isUserAlreadyAdded(users, userName)) {
            switch (userType) {
                case VIEWER:
                    users.add(new UserViewer(userName));
                    break;
                case CRITIC:
                    users.add(new UserCritic(userName));
                    break;
                case EXPERT:
                    break;
                case ADMIN:
                    break;
                default:
                    users.add(new UserViewer(userName));
                    break;
            }

        }
    }

    public boolean addReview(String uName, String pfName, int rating)
            throws MultipleReviewsException, PlatformNotReadyException {
        if (!uName.equals("") || !pfName.equals("") || isRatingValid(rating)) {
            if (reviews == null) reviews = new HashSet<>();
            User userObj = getUserObject(users, uName);
            Platform pfObj = getPlatformObject(platforms, pfName);
            if (userObj != null && pfObj != null && isPlatformReleased(pfObj)) {
                if (userObj.getType().equals(CRITIC)) rating *= CRITIC_VALUE;//todo
                Review review = new Review(uName, pfName, rating);
                if (!isReviewAlreadyAdded(reviews, review)) {
                    reviews.add(review);
                    pfObj.setReview(review);
                    userObj.addReviewedPlatform(pfObj);
                    promoteUserIfNeeded(userObj);
                    return true;
                } else {
                    throw new MultipleReviewsException("Multiple reviews not allowed!");
                }
            } else {
                throw new PlatformNotReadyException("Platform yet to be released!");
            }
        }
        return false;
    }

    //todo null pointer exception
    public List<Platform> sortPlatformsRatedBy(UserType userType) {
        List<Platform> platformsList = new ArrayList<>(platforms);
        platformsList.sort(new PlatformReviewsSorter());
        return platformsList;
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

    public Platform getPlatform(String platformName) {
        return PlatformReviewUtility.getPlatformObject(platforms, platformName).orElseThrow(() -> new PlatformNotReadyException("Platform not ready"));
    }

    private User getUser(String userName) {
        return getUserObject(users, userName).orElseThrow(() -> new PlatformNotReadyException("User not exist"));
    }

    @Override
    public void promoteUserIfNeeded(User user) {
        if (user.getReviewedPlatforms().size() >= new UserCritic().getThreshold()
                && user.getType().equals(UserType.VIEWER))
            user.setType(UserType.CRITIC);
        if (user.getReviewedPlatforms().size() >= new UserExpert().getThreshold()
                && user.getType().equals(UserType.CRITIC))
            user.setType(UserType.EXPERT);
        if (user.getReviewedPlatforms().size() >= new UserAdmin().getThreshold()
                && user.getType().equals(UserType.EXPERT))
            user.setType(UserType.ADMIN);
    }
}
