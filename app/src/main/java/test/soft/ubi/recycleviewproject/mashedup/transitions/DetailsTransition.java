package test.soft.ubi.recycleviewproject.mashedup.transitions;

import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

/**
 * Created by David on 2018. 01. 30..
 */

public class DetailsTransition extends TransitionSet {
    public DetailsTransition() {
        setOrdering(ORDERING_SEQUENTIAL);
        addTransition(new ChangeBounds())
                .addTransition(new ChangeTransform())
                .addTransition(new ChangeImageTransform());
    }
}
