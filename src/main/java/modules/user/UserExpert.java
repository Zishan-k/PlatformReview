package modules.user;

import constants.UserType;
import lombok.Getter;

@Getter
public class UserExpert extends User {
    public final int rating = 2;
    public final int threshold = 6;

    public UserExpert(String name) {
        super(name, UserType.EXPERT);
    }

    public UserExpert() {
    }
}
