package util;

import constants.PlatformStatus;
import constants.ReviewConstants;
import constants.UserType;
import modules.platform.Platform;
import modules.review.Review;
import modules.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyUtil {
    public static boolean isPlatformAlreadyAdded(Set<Platform> platformSet, String name, String vertical) {
        for (Platform temp : platformSet) {
            if (temp.getName().equals(name) && temp.getVertical().equals(vertical)) return true;
        }
        return false;
    }

    public static boolean isUserAlreadyAdded(Set<User> userSet, String name) {
        for (User temp : userSet) {
            if (temp.getName().equals(name)) return true;
        }
        return false;
    }

    public static boolean isReviewAlreadyAdded(Set<Review> reviewSet, Review review) {
        for (Review temp : reviewSet) {
            if (temp.equals(review)) return true;
        }
        return false;
    }

    public static User getUserObject(Set<User> set, String name) {
        for (User temp : set) {
            if (temp.getName().equals(name)) return temp;
        }
        return null;
    }

    public static Platform getPlatformObject(Set<Platform> set, String name) {
        for (Platform temp : set) {
            if (temp.getName().equals(name)) return temp;
        }
        return null;
    }

    public static boolean isRatingValid(int rating) {
        return rating > ReviewConstants.MIN_RATING && rating < ReviewConstants.MAX_RATING;
    }

    public static boolean isPlatformReleased(Platform pfObj) {
        return pfObj.getStatus().equals(PlatformStatus.RELEASED);
    }

    public static int sumList(List<Integer> list) {
        int sum = 0;
        for (int i : list) sum += i;
        return sum;
    }

    public static List<Integer> getOnlyRatings(List<Review> reviews) {
        List<Integer> listOfRatings = new ArrayList<>();
        for (Review r : reviews) listOfRatings.add(r.getRating());
        return listOfRatings;
    }

}
