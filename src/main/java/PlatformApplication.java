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

import java.util.*;
import java.util.stream.Collectors;

import static util.PlatformReviewUtility.*;

@Getter
public class PlatformApplication {
    private Set<Platform> platforms = new HashSet<>();
    private Set<User> users = new HashSet<>();


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

    public User addUser(@NonNull String userName, UserType userType) {
        User user = null;
        if (!isUserAlreadyAdded(users, userName)) {
            user = User.builder()
                    .name(userName)
                    .type(userType)
                    .build();
            users.add(user);
        }
        return user;
    }

    public void addReview(@NonNull String userName, @NonNull String platformName, @NonNull Rating rating) {
        User user = getUser(userName);
        Platform platform = getPlatform(platformName);
        Pair<Integer, Integer> thresholdVsRating = rating.getRating(user.getType());
        Review review = new Review(user, platformName, thresholdVsRating.getValue1());
        if (!isReviewAlreadyAdded(platform, review)) {
            platform.addReview(review);
            promoteUserIfNeeded(user, rating);
        } else {
            throw new MultipleReviewsException("Multiple reviews not allowed!");
        }
    }

    public List<Platform> sortPlatformsRatedBy(UserType userType, String vertical) {
        List<Platform> platformsList = new ArrayList<>(getAllReleasedPlatformForUserType(vertical, userType));
        platformsList.sort((o1, o2) -> {
            int sum1 = getSum(userType, o1);
            int sum2 = getSum(userType, o2);
            return sum2 - sum1;
        });
        return platformsList;
    }

    private List<Platform> getAllReleasedPlatformForUserType(String vertical, UserType userType) {
        return platforms.stream()
                .filter(Platform::isReleased)
                .filter(platform -> platform.getReviews()
                        .stream()
                        .anyMatch(review -> review.getUser().getType().equals(userType)))
                .filter(platform -> platform.getVertical().equals(vertical))
                .collect(Collectors.toList());
    }

    private int getSum(UserType userType, Platform o1) {
        return o1.getReviews()
                .stream()
                .filter(platforms -> platforms.getUser().getType().equals(userType))
                .map(Review::getRating)
                .mapToInt(i -> i)
                .sum();
    }


    public OptionalDouble getAvgRatingOf(String platform) {
        return getPlatformObject(platforms, platform).orElseThrow().getReviews()
                .stream()
                .mapToInt(Review::getRating)
                .average();
    }

    public List<Review> getAllReviewsOfPlatform(String platform) {
        return Objects.requireNonNull(getPlatformObject(platforms, platform)).orElseThrow().getReviews();
    }

    public Platform getPlatform(String platformName) {
        return PlatformReviewUtility
                .getPlatformObject(platforms, platformName)
                .orElseThrow(() -> new PlatformNotReadyException("Platform not ready"));
    }

    public User getUser(String userName) {
        return getUserObject(users, userName).orElseThrow(() -> new PlatformNotReadyException("User not exist"));
    }

    public void promoteUserIfNeeded(User user, @NonNull Rating rating) {
        int totalPlatformReviewed = getTotalPlatformReviewed(user);

        if (user.getType().equals(UserType.VIEWER)
                && totalPlatformReviewed >= rating.getRating(UserType.CRITIC).getValue0())
            user.setType(UserType.CRITIC);

        if (user.getType().equals(UserType.CRITIC)
                && totalPlatformReviewed >= rating.getRating(UserType.EXPERT).getValue0())
            user.setType(UserType.EXPERT);

    }

    private int getTotalPlatformReviewed(User user) {
        return platforms.stream()
                .filter(Platform::isReleased)
                .mapToInt(platform -> {
                    long count = platform.getReviews()
                            .stream()
                            .filter(review -> review.getUser().getName().equalsIgnoreCase(user.getName()))
                            .count();
                    return Integer.valueOf(String.valueOf(count));
                }).sum();
    }

    public boolean updatePlatformStatus(String platformName, PlatformStatus to){
        Optional<Platform> platform = platforms.stream()
                .filter(platform1 -> platform1.getName().equals(platformName))
                .findFirst();
        platform.get().setStatus(to);
        return platform.get().getStatus().equals(to);
    }


}
