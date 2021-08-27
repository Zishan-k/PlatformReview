package util;

import exceptions.PlatformNotReadyException;
import modules.platform.Platform;
import modules.review.Review;
import modules.user.User;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class PlatformReviewUtility {

    public static boolean isPlatformAlreadyAdded(Set<Platform> platformSet, String name, String vertical) {
        return platformSet.stream()
                .anyMatch(platform -> platform.getName().equals(name) && platform.getVertical().equals(vertical));
    }

    public static boolean isUserAlreadyAdded(Set<User> userSet, String name) {
        return userSet.stream()
                .anyMatch(user -> user.getName().equalsIgnoreCase(name));
    }

    public static boolean isReviewAlreadyAdded(Platform platform, Review review) {
        if (platform.isReleased())
            return Optional.ofNullable(platform.getReviews())
                    .orElse(Collections.emptyList())
                    .stream()
                    .anyMatch(reviewInfo -> reviewInfo.equals(review));
        else
            throw new PlatformNotReadyException("Platform is not yet released");
    }

    public static Optional<User> getUserObject(Set<User> users, String name) {
        return users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public static Optional<Platform> getPlatformObject(Set<Platform> platforms, String name) {
        return platforms.stream()
                .filter(Platform::isReleased)
                .filter(platform -> platform.getName().equalsIgnoreCase(name))
                .findFirst();
    }


}
