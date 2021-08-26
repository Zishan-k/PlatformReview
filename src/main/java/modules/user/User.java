package modules.user;

import constants.UserType;
import lombok.Data;
import modules.platform.Platform;

import java.util.HashSet;
import java.util.Objects;

@Data
public abstract class User {
    private String name;
    private UserType type;
    private HashSet<Platform> reviewedPlatforms = new HashSet<>();

    public User(String name, UserType userType) {
        this.name = name;
        this.type = userType;
    }

    public User() {}

    public abstract int getRating();

    public void addReviewedPlatform(Platform platform) {
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
