package org.apache.commons.cli;

public class PatternOptionBuilder
{
  public static final Class CLASS_VALUE;
  public static final Class DATE_VALUE;
  public static final Class EXISTING_FILE_VALUE;
  public static final Class FILES_VALUE;
  public static final Class FILE_VALUE;
  public static final Class NUMBER_VALUE;
  public static final Class OBJECT_VALUE;
  public static final Class STRING_VALUE;
  public static final Class URL_VALUE;
  static Class array$Ljava$io$File;
  static Class class$java$io$File;
  static Class class$java$io$FileInputStream;
  static Class class$java$lang$Class;
  static Class class$java$lang$Number;
  static Class class$java$lang$Object;
  static Class class$java$lang$String;
  static Class class$java$net$URL;
  static Class class$java$util$Date;
  
  static
  {
    Class localClass;
    if (class$java$lang$String == null)
    {
      localClass = class$("java.lang.String");
      class$java$lang$String = localClass;
      STRING_VALUE = localClass;
      if (class$java$lang$Object != null) {
        break label188;
      }
      localClass = class$("java.lang.Object");
      class$java$lang$Object = localClass;
      label36:
      OBJECT_VALUE = localClass;
      if (class$java$lang$Number != null) {
        break label195;
      }
      localClass = class$("java.lang.Number");
      class$java$lang$Number = localClass;
      label56:
      NUMBER_VALUE = localClass;
      if (class$java$util$Date != null) {
        break label202;
      }
      localClass = class$("java.util.Date");
      class$java$util$Date = localClass;
      label76:
      DATE_VALUE = localClass;
      if (class$java$lang$Class != null) {
        break label209;
      }
      localClass = class$("java.lang.Class");
      class$java$lang$Class = localClass;
      label96:
      CLASS_VALUE = localClass;
      if (class$java$io$FileInputStream != null) {
        break label216;
      }
      localClass = class$("java.io.FileInputStream");
      class$java$io$FileInputStream = localClass;
      label116:
      EXISTING_FILE_VALUE = localClass;
      if (class$java$io$File != null) {
        break label223;
      }
      localClass = class$("java.io.File");
      class$java$io$File = localClass;
      label136:
      FILE_VALUE = localClass;
      if (array$Ljava$io$File != null) {
        break label230;
      }
      localClass = class$("[Ljava.io.File;");
      array$Ljava$io$File = localClass;
      label156:
      FILES_VALUE = localClass;
      if (class$java$net$URL != null) {
        break label237;
      }
      localClass = class$("java.net.URL");
      class$java$net$URL = localClass;
    }
    for (;;)
    {
      URL_VALUE = localClass;
      return;
      localClass = class$java$lang$String;
      break;
      label188:
      localClass = class$java$lang$Object;
      break label36;
      label195:
      localClass = class$java$lang$Number;
      break label56;
      label202:
      localClass = class$java$util$Date;
      break label76;
      label209:
      localClass = class$java$lang$Class;
      break label96;
      label216:
      localClass = class$java$io$FileInputStream;
      break label116;
      label223:
      localClass = class$java$io$File;
      break label136;
      label230:
      localClass = array$Ljava$io$File;
      break label156;
      label237:
      localClass = class$java$net$URL;
    }
  }
  
  static Class class$(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
      throw new NoClassDefFoundError().initCause(paramString);
    }
  }
  
  public static Object getValueClass(char paramChar)
  {
    switch (paramChar)
    {
    default: 
      return null;
    case '@': 
      return OBJECT_VALUE;
    case ':': 
      return STRING_VALUE;
    case '%': 
      return NUMBER_VALUE;
    case '+': 
      return CLASS_VALUE;
    case '#': 
      return DATE_VALUE;
    case '<': 
      return EXISTING_FILE_VALUE;
    case '>': 
      return FILE_VALUE;
    case '*': 
      return FILES_VALUE;
    }
    return URL_VALUE;
  }
  
  public static boolean isValueCode(char paramChar)
  {
    return (paramChar == '@') || (paramChar == ':') || (paramChar == '%') || (paramChar == '+') || (paramChar == '#') || (paramChar == '<') || (paramChar == '>') || (paramChar == '*') || (paramChar == '/') || (paramChar == '!');
  }
  
  public static Options parsePattern(String paramString)
  {
    boolean bool3 = true;
    char c1 = ' ';
    boolean bool1 = false;
    Object localObject1 = null;
    Options localOptions = new Options();
    int i = 0;
    if (i < paramString.length())
    {
      char c2 = paramString.charAt(i);
      if (!isValueCode(c2))
      {
        bool2 = bool1;
        Object localObject2 = localObject1;
        if (c1 != ' ')
        {
          if (localObject1 != null)
          {
            bool2 = true;
            label66:
            OptionBuilder.hasArg(bool2);
            OptionBuilder.isRequired(bool1);
            OptionBuilder.withType(localObject1);
            localOptions.addOption(OptionBuilder.create(c1));
            bool2 = false;
            localObject2 = null;
          }
        }
        else
        {
          c1 = c2;
          localObject1 = localObject2;
          bool1 = bool2;
        }
      }
      for (;;)
      {
        i += 1;
        break;
        bool2 = false;
        break label66;
        if (c2 == '!') {
          bool1 = true;
        } else {
          localObject1 = getValueClass(c2);
        }
      }
    }
    if (c1 != ' ') {
      if (localObject1 == null) {
        break label190;
      }
    }
    label190:
    for (boolean bool2 = bool3;; bool2 = false)
    {
      OptionBuilder.hasArg(bool2);
      OptionBuilder.isRequired(bool1);
      OptionBuilder.withType(localObject1);
      localOptions.addOption(OptionBuilder.create(c1));
      return localOptions;
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.PatternOptionBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */