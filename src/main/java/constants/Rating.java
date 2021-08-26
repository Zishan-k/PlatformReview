package constants;

import org.javatuples.Pair;

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

    public Pair<Integer, Integer> getRating(UserType userType){
        switch (userType) {
            case VIEWER:
                return Pair.with(0, rating);
            case CRITIC:
                return Pair.with(3, rating * 2);
            case EXPERT:
                return Pair.with(6, rating * 3);
            case ADMIN:
                return Pair.with(10, rating * 3);
            default:
                throw new RuntimeException("User type not supported");
        }
        return Pair.with(0,0);
    }
}
