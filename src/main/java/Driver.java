import constants.PlatformStatus;
import exceptions.*;

public class Driver {
    public static void main(String[] args) {
        ApplicationService dh = new ApplicationService();
        dh.addPlatform("jjj","qq", PlatformStatus.RELEASED.toString());
        dh.addPlatform("kkk","qq", PlatformStatus.RELEASED.toString());
        dh.addPlatform("lll","qq", PlatformStatus.RELEASED.toString());

        dh.addUser("zishan");
        dh.addUser("zaky");
        dh.addUser("zishan");
        dh.addPlatform("mmm","jkd", PlatformStatus.NOT_RELEASED.toString());
        try {
            dh.addReview("zishan", "jjj", 5);
            dh.addReview("zishan", "kkk", 1);
            dh.addReview("zishan", "lll", 3);
            dh.addReview("zaky", "mmm", 3);
        } catch(PlatformNotReadyException e){
            e.printMessage();
        } catch (MultipleReviewsException e){
            e.printMessage();
        }
        System.out.println(dh.getAvgRatingOf("jjj"));
    }
}
