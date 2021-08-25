import constants.PlatformStatus;
import exceptions.*;

public class Application {
    public static void main(String[] args) {
        ApplicationService as = new ApplicationService();
        as.addPlatform("jjj","qq", PlatformStatus.RELEASED.toString());
        as.addPlatform("kkk","qq", PlatformStatus.RELEASED.toString());
        as.addPlatform("lll","qq", PlatformStatus.RELEASED.toString());

        as.addUser("zishan");
        as.addUser("zaky");
        as.addUser("zishan");
        as.addPlatform("mmm","jkd", PlatformStatus.NOT_RELEASED.toString());
        try {
            as.addReview("zishan", "jjj", 5);
            as.addReview("zishan", "kkk", 1);
            as.addReview("zishan", "lll", 3);
            as.addReview("zaky", "jjj", 3);
        } catch(PlatformNotReadyException e){
            e.printMessage();
        } catch (MultipleReviewsException e){
            e.printMessage();
        }
        System.out.println(as.getAvgRatingOf("jjj"));
    }
}
