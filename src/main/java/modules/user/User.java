package modules.user;

import constants.UserType;
import lombok.Data;
import modules.platform.Platform;

import java.util.HashSet;

@Data
public class User {
    private String name;
    private String type;
    private HashSet<Platform> reviewedPlatforms;

    public User(String name) {
        this.name = name;
        this.type = UserType.VIEWER;
    }

    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashSet<Platform> getReviewedPlatforms() {
        return reviewedPlatforms;
    }*/

    public void addReviewedPlatform(Platform platform) {
        if (reviewedPlatforms == null) reviewedPlatforms = new HashSet<>();
        reviewedPlatforms.add(platform);
    }

}
