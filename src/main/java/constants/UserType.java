package constants;

public interface UserType {
    String VIEWER = "VIEWER";
    String CRITIC = "CRITIC";
    String EXPERT = "EXPERT";
    String ADMIN = "ADMIN";

    int CRITIC_THRESHOLD = 3;
    int EXPERT_THRESHOLD = 6;
    int ADMIN_THRESHOLD = 10;

    int CRITIC_VALUE = 2;
    int EXPERT_VALUE = 3;
    int ADMIN_VALUE = 3;
}
