import constants.PlatformStatus;
import constants.Rating;
import constants.UserType;
import exceptions.*;
import lombok.Getter;
import lombok.NonNull;
import modules.platform.Platform;
import modules.review.Review;
import modules.user.*;
import org.javatuples.Pair;
import util.PlatformReviewUtility;
import util.UserObserver;

import java.util.*;
import java.util.stream.Collectors;

import static util.PlatformReviewUtility.*;

@Getter
public class ApplicationService implements UserObserver {
    private Set<Platform> platforms = new HashSet<>();
    private Set<User> users = new HashSet<>();
    private Set<Review> reviews = new HashSet<>();

    public Platform addPlatform(@NonNull String pfName, @NonNull String vertical, @NonNull PlatformStatus platformStatus) {
        Platform platform = null;
        if (!isPlatformAlreadyAdded(platforms, pfName, vertical)) {
            platform = Platform.builder()
                    .name(pfName)
                    .vertical(vertical)
                    .status(platformStatus).build();
            platforms.add(platform);
        }
        return platform;
    }

    public void addUser(@NonNull String userName, UserType userType) {
        if (!isUserAlreadyAdded(users, userName)) {

        }
    }

    public void addReview(@NonNull String userName, @NonNull String platformName, @NonNull Rating rating) {
        User user = getUser(userName);
        Platform platform = getPlatform(platformName);
        if (platform.isReleased()) {
            Pair<Integer, Integer> rating1 = rating.getRating(user.getType());
            Review review = new Review(user, platformName, rating1.getValue0());
            if (!isReviewAlreadyAdded(reviews, review)) {
                reviews.add(review);
                platform.setReview(review);
                user.addReviewedPlatform(platform);
                promoteUserIfNeeded(user); // todo add observer design pattern
            } else {
                throw new MultipleReviewsException("Multiple reviews not allowed!");
            }
        } else {
            throw new PlatformNotReadyException("Platform yet to be released!");
        }
    }


    //todo null pointer exception
    public List<Platform> sortPlatformsRatedBy(UserType userType) {
        List<Platform> platformsList = new ArrayList<>(platforms.stream().filter(platform -> platform.isReleased()).collect(Collectors.toList()));
        platformsList.sort((o1, o2) -> {
            int sum1 = getSum(userType, o1);
            int sum2 = getSum(userType, o2);
            return sum1 - sum2;
        });
        return platformsList;
    }

    private int getSum(UserType userType, Platform o1) {
        return o1.getReviews()
                .stream()
                .filter(platforms -> platforms.getUser().getType().equals(userType))
                .map(platformsList1 -> platformsList1.getRating())
                .mapToInt(i -> i)
                .sum();
    }


    public OptionalDouble getAvgRatingOf(String platform) {
        return getPlatformObject(platforms, platform).orElseThrow().getReviews()
                .stream()
                .mapToInt(review -> review.getRating())
                .average();
    }

    public List<Review> getAllReviewsOfPlatform(String platform) {
        return Objects.requireNonNull(getPlatformObject(platforms, platform)).orElseThrow().getReviews();
    }

    public Platform getPlatform(String platformName) {
        return PlatformReviewUtility.getPlatformObject(platforms, platformName).orElseThrow(() -> new PlatformNotReadyException("Platform not ready"));
    }

    private User getUser(String userName) {
        return getUserObject(users, userName).orElseThrow(() -> new PlatformNotReadyException("User not exist"));
    }

    @Override
    public void promoteUserIfNeeded(User user) {
        /*if (user.getReviewedPlatforms().size() >= new UserCritic().getThreshold()
                && user.getType().equals(UserType.VIEWER))
            user.setType(UserType.CRITIC);
        if (user.getReviewedPlatforms().size() >= new UserExpert().getThreshold()
                && user.getType().equals(UserType.CRITIC))
            user.setType(UserType.EXPERT);
        if (user.getReviewedPlatforms().size() >= new UserAdmin().getThreshold()
                && user.getType().equals(UserType.EXPERT))
            user.setType(UserType.ADMIN);*/
    }
}
