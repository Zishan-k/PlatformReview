package modules.user;

import constants.UserType;
import lombok.Builder;
import lombok.Data;
import java.util.Objects;

@Builder
@Data
public class User {
    private String name;
    private UserType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getName().equals(user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

}
