package modules.user;

import constants.UserType;
import lombok.Data;
import modules.platform.Platform;

import java.util.HashSet;
import java.util.Objects;

@Data
public class User {
    private String name;
    private String type;
    private HashSet<Platform> reviewedPlatforms;

    public User(String name) {
        this.name = name;
        this.type = UserType.VIEWER;
    }

    public void addReviewedPlatform(Platform platform) {
        if (reviewedPlatforms == null) reviewedPlatforms = new HashSet<>();
        reviewedPlatforms.add(platform);
    }

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
