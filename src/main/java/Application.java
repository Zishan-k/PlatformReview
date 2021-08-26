import constants.PlatformStatus;
import constants.Rating;
import constants.UserType;
import exceptions.*;
import util.PlatformReviewUtility;

public class Application {
    public static void main(String[] args) {
        ApplicationService as = new ApplicationService();
        as.addPlatform("p1", "qq", PlatformStatus.RELEASED);
        as.addPlatform("p2", "qq", PlatformStatus.RELEASED);

        as.addUser("u1", UserType.DEFAULT);
        as.addUser("u2", UserType.CRITIC);
        as.addUser("u3", UserType.CRITIC);
        as.addPlatform("mmm", "jkd", PlatformStatus.NOT_RELEASED);

        as.addReview("u2", "p1", Rating.FOUR);
        as.addReview("u3", "p1", Rating.FIVE);
        as.addReview("u2", "p2", Rating.FIVE);
        as.addReview("u3", "p2", Rating.FIVE);

        System.out.println(as.getUsers());

        System.out.println(as.sortPlatformsRatedBy(UserType.CRITIC));
    }
}
