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
        if (platform.isReleased()) {
            Pair<Integer, Integer> rating1 = rating.getRating(user.getType());
            Review review = new Review(user, platformName, rating1.getValue1());
            if (!isReviewAlreadyAdded(reviews, review)) {
                reviews.add(review);
                platform.setReview(review);
                promoteUserIfNeeded(user);
            } else {
                throw new MultipleReviewsException("Multiple reviews not allowed!");
            }
        } else {
            throw new PlatformNotReadyException("Platform yet to be released!");
        }
    }

    public List<Platform> sortPlatformsRatedBy(UserType userType, String vertical) {
        List<Platform> platformsList = new ArrayList<>(platforms.stream()
                .filter(platform -> platform.isReleased())
                .filter(platform -> platform.getVertical().equals(vertical))
                .collect(Collectors.toList()));
        platformsList.sort((o1, o2) -> {
            int sum1 = getSum(userType, o1);
            int sum2 = getSum(userType, o2);
            return sum2 - sum1;
        });
        return platformsList;
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
        return PlatformReviewUtility.getPlatformObject(platforms, platformName).orElseThrow(() -> new PlatformNotReadyException("Platform not ready"));
    }

    public User getUser(String userName) {
        return getUserObject(users, userName).orElseThrow(() -> new PlatformNotReadyException("User not exist"));
    }

    @Override
    public void promoteUserIfNeeded(User user) {
        int totalReviews = 0;
        /*for (Review review : reviews) {
            if(review.getUser().getName().equals(user.getName())) totalReviews++;
        }*/
        /*int totalPlatformReviewed = platforms.stream()
                .filter(platformInfo -> platformInfo.isReleased())
                .mapToInt(platform -> {
                    long count = platform.getReviews()
                            .stream()

                            .filter(review -> review.getUser().getName().equalsIgnoreCase(user.getName()))
                            .count();
                    return Integer.valueOf(String.valueOf(count))*/



        /*Rating rating = null;
        if(user.getType().equals(UserType.VIEWER)
                && totalReviews >= rating.getRating(UserType.CRITIC).getValue0())
        user.setType(UserType.CRITIC);
        if(user.getType().equals(UserType.CRITIC)
        && totalReviews >= rating.getRating(UserType.EXPERT).getValue0())
            user.setType(UserType.EXPERT);*/
    }
}
