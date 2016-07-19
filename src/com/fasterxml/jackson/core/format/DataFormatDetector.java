package com.fasterxml.jackson.core.format;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class DataFormatDetector
{
  public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
  protected final JsonFactory[] _detectors;
  protected final int _maxInputLookahead;
  protected final MatchStrength _minimalMatch;
  protected final MatchStrength _optimalMatch;
  
  public DataFormatDetector(Collection<JsonFactory> paramCollection)
  {
    this((JsonFactory[])paramCollection.toArray(new JsonFactory[paramCollection.size()]));
  }
  
  public DataFormatDetector(JsonFactory... paramVarArgs)
  {
    this(paramVarArgs, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
  }
  
  private DataFormatDetector(JsonFactory[] paramArrayOfJsonFactory, MatchStrength paramMatchStrength1, MatchStrength paramMatchStrength2, int paramInt)
  {
    _detectors = paramArrayOfJsonFactory;
    _optimalMatch = paramMatchStrength1;
    _minimalMatch = paramMatchStrength2;
    _maxInputLookahead = paramInt;
  }
  
  private DataFormatMatcher _findFormat(InputAccessor.Std paramStd)
    throws IOException
  {
    JsonFactory[] arrayOfJsonFactory = _detectors;
    int j = arrayOfJsonFactory.length;
    int i = 0;
    Object localObject1 = null;
    Object localObject2 = null;
    Object localObject3;
    Object localObject4;
    if (i < j)
    {
      localObject3 = arrayOfJsonFactory[i];
      paramStd.reset();
      localObject4 = ((JsonFactory)localObject3).hasFormat(paramStd);
      if (localObject4 != null) {
        if (((MatchStrength)localObject4).ordinal() >= _minimalMatch.ordinal()) {}
      }
    }
    for (;;)
    {
      i += 1;
      break;
      if ((localObject1 == null) || (((MatchStrength)localObject2).ordinal() < ((MatchStrength)localObject4).ordinal()))
      {
        if (((MatchStrength)localObject4).ordinal() >= _optimalMatch.ordinal()) {
          for (;;)
          {
            return paramStd.createMatcher((JsonFactory)localObject3, (MatchStrength)localObject4);
            break;
            localObject4 = localObject2;
            localObject3 = localObject1;
          }
        }
        localObject2 = localObject4;
        localObject1 = localObject3;
      }
    }
  }
  
  public DataFormatMatcher findFormat(InputStream paramInputStream)
    throws IOException
  {
    return _findFormat(new InputAccessor.Std(paramInputStream, new byte[_maxInputLookahead]));
  }
  
  public DataFormatMatcher findFormat(byte[] paramArrayOfByte)
    throws IOException
  {
    return _findFormat(new InputAccessor.Std(paramArrayOfByte));
  }
  
  public DataFormatMatcher findFormat(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    return _findFormat(new InputAccessor.Std(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    int j = _detectors.length;
    if (j > 0)
    {
      localStringBuilder.append(_detectors[0].getFormatName());
      int i = 1;
      while (i < j)
      {
        localStringBuilder.append(", ");
        localStringBuilder.append(_detectors[i].getFormatName());
        i += 1;
      }
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public DataFormatDetector withMaxInputLookahead(int paramInt)
  {
    if (paramInt == _maxInputLookahead) {
      return this;
    }
    return new DataFormatDetector(_detectors, _optimalMatch, _minimalMatch, paramInt);
  }
  
  public DataFormatDetector withMinimalMatch(MatchStrength paramMatchStrength)
  {
    if (paramMatchStrength == _minimalMatch) {
      return this;
    }
    return new DataFormatDetector(_detectors, _optimalMatch, paramMatchStrength, _maxInputLookahead);
  }
  
  public DataFormatDetector withOptimalMatch(MatchStrength paramMatchStrength)
  {
    if (paramMatchStrength == _optimalMatch) {
      return this;
    }
    return new DataFormatDetector(_detectors, paramMatchStrength, _minimalMatch, _maxInputLookahead);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.format.DataFormatDetector
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */