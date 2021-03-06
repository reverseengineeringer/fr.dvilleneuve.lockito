package org.androidannotations.annotations.rest;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
public @interface RequiresCookieInUrl
{
  String[] value();
}

/* Location:
 * Qualified Name:     org.androidannotations.annotations.rest.RequiresCookieInUrl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */