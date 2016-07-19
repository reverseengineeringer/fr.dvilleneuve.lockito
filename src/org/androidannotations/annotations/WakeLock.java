package org.androidannotations.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface WakeLock
{
  public static final String DEFAULT_TAG = "NO_TAG";
  
  Flag[] flags() default {};
  
  Level level() default Level.PARTIAL_WAKE_LOCK;
  
  String tag() default "NO_TAG";
  
  public static enum Flag
  {
    ACQUIRE_CAUSES_WAKEUP,  ON_AFTER_RELEASE;
    
    private Flag() {}
  }
  
  public static enum Level
  {
    FULL_WAKE_LOCK,  PARTIAL_WAKE_LOCK,  SCREEN_BRIGHT_WAKE_LOCK,  SCREEN_DIM_WAKE_LOCK;
    
    private Level() {}
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.annotations.WakeLock
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */