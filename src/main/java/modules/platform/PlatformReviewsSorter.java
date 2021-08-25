package modules.platform;

import util.MyUtil;

import java.util.Comparator;

public class PlatformReviewsSorter implements Comparator<Platform> {
    @Override
    public int compare(Platform o1, Platform o2) {
        int o1Sum = MyUtil.sumList(MyUtil.getOnlyRatings(o1.getReviews()));
        int o2Sum = MyUtil.sumList(MyUtil.getOnlyRatings(o2.getReviews()));
        return o1Sum-o2Sum;
    }
}
