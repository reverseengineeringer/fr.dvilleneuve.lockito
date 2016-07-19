package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.SpinnerAdapter;

class AppCompatSpinner$DropdownPopup
  extends ListPopupWindow
{
  private ListAdapter mAdapter;
  private CharSequence mHintText;
  private final Rect mVisibleRect = new Rect();
  
  public AppCompatSpinner$DropdownPopup(final AppCompatSpinner paramAppCompatSpinner, Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setAnchorView(paramAppCompatSpinner);
    setModal(true);
    setPromptPosition(0);
    setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        this$0.setSelection(paramAnonymousInt);
        if (this$0.getOnItemClickListener() != null) {
          this$0.performItemClick(paramAnonymousView, paramAnonymousInt, mAdapter.getItemId(paramAnonymousInt));
        }
        dismiss();
      }
    });
  }
  
  private boolean isVisibleToUser(View paramView)
  {
    return (ViewCompat.isAttachedToWindow(paramView)) && (paramView.getGlobalVisibleRect(mVisibleRect));
  }
  
  void computeContentWidth()
  {
    Object localObject = getBackground();
    int i = 0;
    int n;
    int i1;
    int i2;
    if (localObject != null)
    {
      ((Drawable)localObject).getPadding(AppCompatSpinner.access$300(this$0));
      if (ViewUtils.isLayoutRtl(this$0))
      {
        i = access$300this$0).right;
        n = this$0.getPaddingLeft();
        i1 = this$0.getPaddingRight();
        i2 = this$0.getWidth();
        if (AppCompatSpinner.access$400(this$0) != -2) {
          break label245;
        }
        int k = AppCompatSpinner.access$500(this$0, (SpinnerAdapter)mAdapter, getBackground());
        int m = this$0.getContext().getResources().getDisplayMetrics().widthPixels - access$300this$0).left - access$300this$0).right;
        int j = k;
        if (k > m) {
          j = m;
        }
        setContentWidth(Math.max(j, i2 - n - i1));
        label172:
        if (!ViewUtils.isLayoutRtl(this$0)) {
          break label285;
        }
        i += i2 - i1 - getWidth();
      }
    }
    for (;;)
    {
      setHorizontalOffset(i);
      return;
      i = -access$300this$0).left;
      break;
      localObject = AppCompatSpinner.access$300(this$0);
      access$300this$0).right = 0;
      left = 0;
      break;
      label245:
      if (AppCompatSpinner.access$400(this$0) == -1)
      {
        setContentWidth(i2 - n - i1);
        break label172;
      }
      setContentWidth(AppCompatSpinner.access$400(this$0));
      break label172;
      label285:
      i += n;
    }
  }
  
  public CharSequence getHintText()
  {
    return mHintText;
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    super.setAdapter(paramListAdapter);
    mAdapter = paramListAdapter;
  }
  
  public void setPromptText(CharSequence paramCharSequence)
  {
    mHintText = paramCharSequence;
  }
  
  public void show()
  {
    boolean bool = isShowing();
    computeContentWidth();
    setInputMethodMode(2);
    super.show();
    getListView().setChoiceMode(1);
    setSelection(this$0.getSelectedItemPosition());
    if (bool) {}
    ViewTreeObserver localViewTreeObserver;
    do
    {
      return;
      localViewTreeObserver = this$0.getViewTreeObserver();
    } while (localViewTreeObserver == null);
    final ViewTreeObserver.OnGlobalLayoutListener local2 = new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        if (!AppCompatSpinner.DropdownPopup.this.isVisibleToUser(this$0))
        {
          dismiss();
          return;
        }
        computeContentWidth();
        AppCompatSpinner.DropdownPopup.this.show();
      }
    };
    localViewTreeObserver.addOnGlobalLayoutListener(local2);
    setOnDismissListener(new PopupWindow.OnDismissListener()
    {
      public void onDismiss()
      {
        ViewTreeObserver localViewTreeObserver = this$0.getViewTreeObserver();
        if (localViewTreeObserver != null) {
          localViewTreeObserver.removeGlobalOnLayoutListener(local2);
        }
      }
    });
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.AppCompatSpinner.DropdownPopup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */