package android.support.v4.app;

import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.util.ArrayList;

class BackStackRecord$2
  implements ViewTreeObserver.OnPreDrawListener
{
  BackStackRecord$2(BackStackRecord paramBackStackRecord, View paramView, Object paramObject1, ArrayList paramArrayList, BackStackRecord.TransitionState paramTransitionState, Object paramObject2, Object paramObject3, boolean paramBoolean, Fragment paramFragment1, Fragment paramFragment2) {}
  
  public boolean onPreDraw()
  {
    val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
    FragmentTransitionCompat21.removeTargets(val$sharedElementTransition, val$sharedElementTargets);
    val$sharedElementTargets.remove(val$state.nonExistentView);
    FragmentTransitionCompat21.excludeSharedElementViews(val$enterTransition, val$exitTransition, val$sharedElementTransition, val$sharedElementTargets, false);
    val$sharedElementTargets.clear();
    ArrayMap localArrayMap = BackStackRecord.access$000(this$0, val$state, val$isBack, val$inFragment);
    FragmentTransitionCompat21.setSharedElementTargets(val$sharedElementTransition, val$state.nonExistentView, localArrayMap, val$sharedElementTargets);
    BackStackRecord.access$100(this$0, localArrayMap, val$state);
    BackStackRecord.access$200(this$0, val$state, val$inFragment, val$outFragment, val$isBack, localArrayMap);
    FragmentTransitionCompat21.excludeSharedElementViews(val$enterTransition, val$exitTransition, val$sharedElementTransition, val$sharedElementTargets, true);
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.BackStackRecord.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */