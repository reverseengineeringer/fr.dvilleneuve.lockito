package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.File;

public class JdkDeserializers$FileDeserializer
  extends FromStringDeserializer<File>
{
  public static final FileDeserializer instance = new FileDeserializer();
  
  public JdkDeserializers$FileDeserializer()
  {
    super(File.class);
  }
  
  protected File _deserialize(String paramString, DeserializationContext paramDeserializationContext)
  {
    return new File(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.JdkDeserializers.FileDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */