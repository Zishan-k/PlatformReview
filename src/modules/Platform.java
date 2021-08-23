package modules;

public class Platform {
    String name;
    String vertical;
    String status;

    Platform() {
    }

    Platform(String name, String vertical, String status) {
        this.name = name;
        this.vertical = vertical;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
