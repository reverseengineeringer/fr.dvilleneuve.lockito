package com.facebook.stetho.json.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JsonProperty
{
  boolean required() default false;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.json.annotation.JsonProperty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */