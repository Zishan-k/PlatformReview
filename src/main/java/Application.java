import constants.PlatformStatus;
import constants.Rating;
import constants.UserType;

public class Application {
    public static void main(String[] args) {
        PlatformApplication as = new PlatformApplication();
        as.addPlatform("p1", "vv", PlatformStatus.RELEASED);
        as.addPlatform("p2", "vv", PlatformStatus.RELEASED);
        as.addPlatform("p3", "bb", PlatformStatus.RELEASED);
        as.addPlatform("p4", "bb", PlatformStatus.RELEASED);

        as.addUser("u1", UserType.VIEWER);
        as.addUser("u2", UserType.CRITIC);
        as.addUser("u3", UserType.CRITIC);
        as.addPlatform("mmm", "jkd", PlatformStatus.NOT_RELEASED);

        as.addReview("u2", "p1", Rating.FOUR);
        as.addReview("u3", "p1", Rating.FIVE);
        as.addReview("u2", "p2", Rating.FIVE);
        as.addReview("u3", "p2", Rating.THREE);

        as.addReview("u2", "p3", Rating.FOUR);
        as.addReview("u3", "p3", Rating.TWO);
        as.addReview("u2", "p4", Rating.FIVE);
        as.addReview("u3", "p4", Rating.FIVE);

        System.out.println(as.getUsers());
        //System.out.println(as.getReviews());

        System.out.println(as.sortPlatformsRatedBy(UserType.CRITIC, "vv"));
    }
}
