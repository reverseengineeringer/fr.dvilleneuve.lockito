package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatApi24
{
  public static int getDrawingOrder(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).getDrawingOrder();
  }
  
  public static boolean isImportantForAccessibility(Object paramObject)
  {
    return ((AccessibilityNodeInfo)paramObject).isImportantForAccessibility();
  }
  
  public static void setDrawingOrder(Object paramObject, int paramInt)
  {
    ((AccessibilityNodeInfo)paramObject).setDrawingOrder(paramInt);
  }
  
  public static void setImportantForAccessibility(Object paramObject, boolean paramBoolean)
  {
    ((AccessibilityNodeInfo)paramObject).setImportantForAccessibility(paramBoolean);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityNodeInfoCompatApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */