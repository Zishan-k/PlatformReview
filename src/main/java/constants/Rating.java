package constants;

public enum Rating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int rating;

    Rating(int i) {
        rating = i;
    }
    public int getRating(){
        return this.rating;
    }
}
