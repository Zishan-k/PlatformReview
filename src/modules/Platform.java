package modules;

public class Platform {
    private String name;
    private String vertical;
    private String status;
    private int totalReviews;
    private int avgReview;

    Platform() {
    }

    public Platform(String name, String vertical, String status, int totalReviews, int avgReview) {
        this.name = name;
        this.vertical = vertical;
        this.status = status;
        this.totalReviews = totalReviews;
        this.avgReview = avgReview;
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

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public int getAvgReview() {
        return avgReview;
    }

    public void setAvgReview(int avgReview) {
        this.avgReview = avgReview;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "name='" + name + '\'' +
                ", vertical='" + vertical + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
