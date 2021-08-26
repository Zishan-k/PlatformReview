package modules.user;

import constants.UserType;
import lombok.Getter;

@Getter
public class UserViewer extends User{
    private final int rating = 1;

    public UserViewer(String name){
        super(name, UserType.VIEWER);
    }
}
