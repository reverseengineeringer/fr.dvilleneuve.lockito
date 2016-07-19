package com.crashlytics.android.answers;

import io.fabric.sdk.android.Fabric;

public class Answers
  extends AnswersKit
{
  public static Answers getInstance()
  {
    return (Answers)Fabric.getKit(Answers.class);
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.answers.Answers
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */