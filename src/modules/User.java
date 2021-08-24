package modules;

import java.util.HashSet;

public class User implements UserType {
    private String name;
    private String type;
    private int noOfReviews;
    private HashSet<Platform> reviewedPlatforms;

    User() {
    }

    User(String name) {
        this.name = name;
        this.type = one;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", reviewedPlatforms=" + reviewedPlatforms +
                '}';
    }
}
