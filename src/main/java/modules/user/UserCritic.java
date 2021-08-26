package modules.user;

import constants.UserType;
import lombok.Getter;

@Getter
public class UserCritic extends User{
    public final int rating = 2;
    public final int threshold = 3;

    public UserCritic(String name) {
        super(name, UserType.CRITIC);
    }

    public UserCritic() {
        super();
    }

}
