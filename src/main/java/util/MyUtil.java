package util;

import constants.ReviewConstants;
import modules.platform.Platform;
import modules.review.Review;
import modules.user.User;

import java.util.Set;

public class MyUtil {
    //todo
/*    public <T> boolean isAlreadyAdded(Set<T> set, String name){
        for (T temp : set) {
            temp.
        }
    }*/
    public static boolean isPlatformAlreadyAdded(Set<Platform> platformSet, String name) {
        for (Platform temp : platformSet) {
            if (temp.getName().equals(name)) return true;
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

    public static boolean isRatingValid(int rating){
        return rating > ReviewConstants.MIN_RATING && rating < ReviewConstants.MAX_RATING;
    }

}
