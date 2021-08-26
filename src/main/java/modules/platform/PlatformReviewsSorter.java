package modules.platform;

import util.PlatformReviewUtility;

import java.util.Comparator;

public class PlatformReviewsSorter implements Comparator<Platform> {
    @Override
    public int compare(Platform o1, Platform o2) {
        int o1Sum = PlatformReviewUtility.sumList(PlatformReviewUtility.getOnlyRatings(o1.getReviews()));
        int o2Sum = PlatformReviewUtility.sumList(PlatformReviewUtility.getOnlyRatings(o2.getReviews()));
        return o1Sum-o2Sum;
    }
}
