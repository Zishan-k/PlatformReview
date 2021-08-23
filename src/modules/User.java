package modules;

import java.util.HashSet;

public class User implements UserType {
    String name;
    String type = viewer;
    Rating reviewRating;
    HashSet<Platform> reviewedPlatforms;

    User() {
    }

    User(String name) {
        this.name = name;
    }

    public String getName() {
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

    public void addReviewedPlatform(Platform platform) {
        if (reviewedPlatforms == null) reviewedPlatforms = new HashSet<>();
        reviewedPlatforms.add(platform);
    }
}
