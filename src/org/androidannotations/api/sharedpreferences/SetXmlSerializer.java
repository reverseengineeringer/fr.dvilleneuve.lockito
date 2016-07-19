package org.androidannotations.api.sharedpreferences;

import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.io.StringReader;
import java.util.Set;
import java.util.TreeSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class SetXmlSerializer
{
  private static final String NAMESPACE = "";
  private static final String SET_TAG = "AA_set";
  private static final String STRING_TAG = "AA_string";
  
  public static Set<String> deserialize(String paramString)
  {
    TreeSet localTreeSet = new TreeSet();
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    try
    {
      localXmlPullParser.setInput(new StringReader(paramString));
      localXmlPullParser.next();
      localXmlPullParser.require(2, "", "AA_set");
      for (;;)
      {
        paramString = localTreeSet;
        if (localXmlPullParser.next() == 3) {
          break;
        }
        localXmlPullParser.require(2, "", "AA_string");
        localXmlPullParser.next();
        localXmlPullParser.require(4, null, null);
        localTreeSet.add(localXmlPullParser.getText());
        localXmlPullParser.next();
        localXmlPullParser.require(3, null, "AA_string");
      }
      return null;
    }
    catch (XmlPullParserException paramString)
    {
      Log.w("getStringSet", paramString);
      paramString = null;
      return paramString;
    }
    catch (IOException paramString)
    {
      Log.w("getStringSet", paramString);
    }
  }
  
  /* Error */
  public static String serialize(Set<String> paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: astore_1
    //   2: aload_0
    //   3: ifnonnull +7 -> 10
    //   6: invokestatic 85	java/util/Collections:emptySet	()Ljava/util/Set;
    //   9: astore_1
    //   10: new 87	java/io/StringWriter
    //   13: dup
    //   14: invokespecial 88	java/io/StringWriter:<init>	()V
    //   17: astore_0
    //   18: invokestatic 92	android/util/Xml:newSerializer	()Lorg/xmlpull/v1/XmlSerializer;
    //   21: astore_2
    //   22: aload_2
    //   23: aload_0
    //   24: invokeinterface 98 2 0
    //   29: aload_2
    //   30: ldc 8
    //   32: ldc 11
    //   34: invokeinterface 102 3 0
    //   39: pop
    //   40: aload_1
    //   41: invokeinterface 106 1 0
    //   46: astore_1
    //   47: aload_1
    //   48: invokeinterface 112 1 0
    //   53: ifeq +48 -> 101
    //   56: aload_1
    //   57: invokeinterface 115 1 0
    //   62: checkcast 117	java/lang/String
    //   65: astore_3
    //   66: aload_2
    //   67: ldc 8
    //   69: ldc 14
    //   71: invokeinterface 102 3 0
    //   76: aload_3
    //   77: invokeinterface 121 2 0
    //   82: ldc 8
    //   84: ldc 14
    //   86: invokeinterface 124 3 0
    //   91: pop
    //   92: goto -45 -> 47
    //   95: astore_1
    //   96: aload_0
    //   97: invokevirtual 127	java/io/StringWriter:toString	()Ljava/lang/String;
    //   100: areturn
    //   101: aload_2
    //   102: ldc 8
    //   104: ldc 11
    //   106: invokeinterface 124 3 0
    //   111: invokeinterface 130 1 0
    //   116: goto -20 -> 96
    //   119: astore_1
    //   120: goto -24 -> 96
    //   123: astore_1
    //   124: goto -28 -> 96
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	127	0	paramSet	Set<String>
    //   1	56	1	localObject	Object
    //   95	1	1	localIllegalArgumentException	IllegalArgumentException
    //   119	1	1	localIllegalStateException	IllegalStateException
    //   123	1	1	localIOException	IOException
    //   21	81	2	localXmlSerializer	org.xmlpull.v1.XmlSerializer
    //   65	12	3	str	String
    // Exception table:
    //   from	to	target	type
    //   22	47	95	java/lang/IllegalArgumentException
    //   47	92	95	java/lang/IllegalArgumentException
    //   101	116	95	java/lang/IllegalArgumentException
    //   22	47	119	java/lang/IllegalStateException
    //   47	92	119	java/lang/IllegalStateException
    //   101	116	119	java/lang/IllegalStateException
    //   22	47	123	java/io/IOException
    //   47	92	123	java/io/IOException
    //   101	116	123	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.SetXmlSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */