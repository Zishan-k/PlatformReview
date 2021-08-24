package util;

import modules.platform.Platform;
import modules.review.Review;
import modules.user.User;

import java.util.Map;

public class MyUtil {
    public static boolean isPlatformAlreadyAdded(Map<Platform, Platform> map, String name) {
        for (Platform temp : map.keySet()) {
            if (temp.getName().equals(name)) return true;
        }
        return false;
    }

    public static boolean isUserAlreadyAdded(Map<User, User> map, String name) {
        for (User temp : map.keySet()) {
            if (temp.getName().equals(name)) return true;
        }
        return false;
    }

    public static boolean isReviewAlreadyAdded(Map<Review, Review> map, Review review){
        for (Review temp :
                map.keySet()) {
            if(temp.equals(review)) return true;
        }
        return false;
    }

    public static User getUserObject(Map<User, User> map, String name){
        for (User temp :
                map.keySet()) {
            if (temp.getName().equals(name)) return temp;
        }
        return null;
    }

    public static Platform getPlatformObject(Map<Platform, Platform> map, String name){
        for (Platform temp :
                map.keySet()) {
            if (temp.getName().equals(name)) return temp;
        }
        return null;
    }

}
