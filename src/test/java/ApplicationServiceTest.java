import constants.PlatformStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ApplicationServiceTest {
    @Test
    public void addPlatformTest() {
        ApplicationService as = new ApplicationService();
        Assertions.assertTrue(as.addPlatform("aa","v1",PlatformStatus.RELEASED.toString()));
        Assertions.assertTrue(as.addPlatform("bb","v1",PlatformStatus.RELEASED.toString()));
        Assertions.assertTrue(as.addPlatform("cc","v1",PlatformStatus.RELEASED.toString()));
        Assertions.assertTrue(as.addPlatform("dd","v2",PlatformStatus.RELEASED.toString()));
        Assertions.assertTrue(as.addPlatform("ee","v2",PlatformStatus.NOT_RELEASED.toString()));
    }
}
