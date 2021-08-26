package modules.user;

import constants.UserType;
import lombok.Getter;

@Getter
public class UserAdmin extends User{
    public final int rating = 3;
    public final int threshold = 3;

    public UserAdmin(String name){
        super(name, UserType.ADMIN);
    }

    public UserAdmin() {

    }
}
