package org.androidannotations.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface HttpsClient
{
  public static final String DEFAULT_PASSWD = "changeit";
  
  boolean allowAllHostnames() default true;
  
  int keyStore() default -1;
  
  String keyStorePwd() default "changeit";
  
  String keyStoreResName() default "";
  
  int trustStore() default -1;
  
  String trustStorePwd() default "changeit";
  
  String trustStoreResName() default "";
}

/* Location:
 * Qualified Name:     org.androidannotations.annotations.HttpsClient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */