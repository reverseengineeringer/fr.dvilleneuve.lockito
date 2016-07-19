package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;

public final class CharsToNameCanonicalizer
{
  protected static final int DEFAULT_TABLE_SIZE = 64;
  public static final int HASH_MULT = 33;
  static final int MAX_COLL_CHAIN_FOR_REUSE = 63;
  static final int MAX_COLL_CHAIN_LENGTH = 255;
  static final int MAX_ENTRIES_FOR_REUSE = 12000;
  protected static final int MAX_TABLE_SIZE = 65536;
  static final CharsToNameCanonicalizer sBootstrapSymbolTable = new CharsToNameCanonicalizer();
  protected Bucket[] _buckets;
  protected final boolean _canonicalize;
  protected boolean _dirty;
  private final int _hashSeed;
  protected int _indexMask;
  protected final boolean _intern;
  protected int _longestCollisionList;
  protected CharsToNameCanonicalizer _parent;
  protected int _size;
  protected int _sizeThreshold;
  protected String[] _symbols;
  
  private CharsToNameCanonicalizer()
  {
    _canonicalize = true;
    _intern = true;
    _dirty = true;
    _hashSeed = 0;
    _longestCollisionList = 0;
    initTables(64);
  }
  
  private CharsToNameCanonicalizer(CharsToNameCanonicalizer paramCharsToNameCanonicalizer, boolean paramBoolean1, boolean paramBoolean2, String[] paramArrayOfString, Bucket[] paramArrayOfBucket, int paramInt1, int paramInt2, int paramInt3)
  {
    _parent = paramCharsToNameCanonicalizer;
    _canonicalize = paramBoolean1;
    _intern = paramBoolean2;
    _symbols = paramArrayOfString;
    _buckets = paramArrayOfBucket;
    _size = paramInt1;
    _hashSeed = paramInt2;
    paramInt1 = paramArrayOfString.length;
    _sizeThreshold = _thresholdSize(paramInt1);
    _indexMask = (paramInt1 - 1);
    _longestCollisionList = paramInt3;
    _dirty = false;
  }
  
  private static int _thresholdSize(int paramInt)
  {
    return paramInt - (paramInt >> 2);
  }
  
  private void copyArrays()
  {
    Object localObject = _symbols;
    _symbols = ((String[])Arrays.copyOf((Object[])localObject, localObject.length));
    localObject = _buckets;
    _buckets = ((Bucket[])Arrays.copyOf((Object[])localObject, localObject.length));
  }
  
  public static CharsToNameCanonicalizer createRoot()
  {
    long l = System.currentTimeMillis();
    int i = (int)l;
    return createRoot((int)(l >>> 32) + i | 0x1);
  }
  
  protected static CharsToNameCanonicalizer createRoot(int paramInt)
  {
    return sBootstrapSymbolTable.makeOrphan(paramInt);
  }
  
  private void initTables(int paramInt)
  {
    _symbols = new String[paramInt];
    _buckets = new Bucket[paramInt >> 1];
    _indexMask = (paramInt - 1);
    _size = 0;
    _longestCollisionList = 0;
    _sizeThreshold = _thresholdSize(paramInt);
  }
  
  private CharsToNameCanonicalizer makeOrphan(int paramInt)
  {
    return new CharsToNameCanonicalizer(null, true, true, _symbols, _buckets, _size, paramInt, _longestCollisionList);
  }
  
  private void mergeChild(CharsToNameCanonicalizer paramCharsToNameCanonicalizer)
  {
    if ((paramCharsToNameCanonicalizer.size() > 12000) || (_longestCollisionList > 63)) {
      try
      {
        initTables(64);
        _dirty = false;
        return;
      }
      finally {}
    }
    if (paramCharsToNameCanonicalizer.size() > size()) {
      try
      {
        _symbols = _symbols;
        _buckets = _buckets;
        _size = _size;
        _sizeThreshold = _sizeThreshold;
        _indexMask = _indexMask;
        _longestCollisionList = _longestCollisionList;
        _dirty = false;
        return;
      }
      finally {}
    }
  }
  
  private void rehash()
  {
    int i1 = _symbols.length;
    int i = i1 + i1;
    if (i > 65536)
    {
      _size = 0;
      Arrays.fill(_symbols, null);
      Arrays.fill(_buckets, null);
      _dirty = true;
    }
    int k;
    label169:
    do
    {
      return;
      Object localObject1 = _symbols;
      Bucket[] arrayOfBucket = _buckets;
      _symbols = new String[i];
      _buckets = new Bucket[i >> 1];
      _indexMask = (i - 1);
      _sizeThreshold = _thresholdSize(i);
      k = 0;
      i = 0;
      int j = 0;
      Object localObject2;
      if (k < i1)
      {
        localObject2 = localObject1[k];
        m = i;
        int n = j;
        if (localObject2 != null)
        {
          n = j + 1;
          j = _hashToIndex(calcHash((String)localObject2));
          if (_symbols[j] != null) {
            break label169;
          }
          _symbols[j] = localObject2;
        }
        for (m = i;; m = Math.max(i, ((Bucket)localObject2).length()))
        {
          k += 1;
          i = m;
          j = n;
          break;
          j >>= 1;
          localObject2 = new Bucket((String)localObject2, _buckets[j]);
          _buckets[j] = localObject2;
        }
      }
      int m = 0;
      k = j;
      j = m;
      while (j < i1 >> 1)
      {
        localObject1 = arrayOfBucket[j];
        if (localObject1 != null)
        {
          k += 1;
          localObject2 = ((Bucket)localObject1).getSymbol();
          m = _hashToIndex(calcHash((String)localObject2));
          if (_symbols[m] == null) {
            _symbols[m] = localObject2;
          }
          for (;;)
          {
            localObject1 = ((Bucket)localObject1).getNext();
            break;
            m >>= 1;
            localObject2 = new Bucket((String)localObject2, _buckets[m]);
            _buckets[m] = localObject2;
            i = Math.max(i, ((Bucket)localObject2).length());
          }
        }
        j += 1;
      }
      _longestCollisionList = i;
    } while (k == _size);
    throw new Error("Internal error on SymbolTable.rehash(): had " + _size + " entries; now have " + k + ".");
  }
  
  public int _hashToIndex(int paramInt)
  {
    return (paramInt >>> 15) + paramInt & _indexMask;
  }
  
  public int bucketCount()
  {
    return _symbols.length;
  }
  
  public int calcHash(String paramString)
  {
    int k = paramString.length();
    int i = _hashSeed;
    int j = 0;
    while (j < k)
    {
      int m = paramString.charAt(j);
      j += 1;
      i = m + i * 33;
    }
    j = i;
    if (i == 0) {
      j = 1;
    }
    return j;
  }
  
  public int calcHash(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    paramInt1 = _hashSeed;
    int i = 0;
    while (i < paramInt2)
    {
      int j = paramArrayOfChar[i];
      i += 1;
      paramInt1 = j + paramInt1 * 33;
    }
    paramInt2 = paramInt1;
    if (paramInt1 == 0) {
      paramInt2 = 1;
    }
    return paramInt2;
  }
  
  public int collisionCount()
  {
    int j = 0;
    Bucket[] arrayOfBucket = _buckets;
    int m = arrayOfBucket.length;
    int i = 0;
    while (i < m)
    {
      Bucket localBucket = arrayOfBucket[i];
      int k = j;
      if (localBucket != null) {
        k = j + localBucket.length();
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  public String findSymbol(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 < 1)
    {
      localObject = "";
      return (String)localObject;
    }
    if (!_canonicalize) {
      return new String(paramArrayOfChar, paramInt1, paramInt2);
    }
    int i = _hashToIndex(paramInt3);
    Object localObject = _symbols[i];
    if (localObject != null)
    {
      if (((String)localObject).length() == paramInt2)
      {
        paramInt3 = 0;
        if (((String)localObject).charAt(paramInt3) != paramArrayOfChar[(paramInt1 + paramInt3)]) {}
        for (;;)
        {
          if (paramInt3 != paramInt2) {
            break label112;
          }
          return (String)localObject;
          int j = paramInt3 + 1;
          paramInt3 = j;
          if (j < paramInt2) {
            break;
          }
          paramInt3 = j;
        }
      }
      label112:
      localObject = _buckets[(i >> 1)];
      if (localObject != null)
      {
        localObject = ((Bucket)localObject).find(paramArrayOfChar, paramInt1, paramInt2);
        if (localObject != null) {
          return (String)localObject;
        }
      }
    }
    if (!_dirty)
    {
      copyArrays();
      _dirty = true;
      paramInt3 = i;
    }
    for (;;)
    {
      localObject = new String(paramArrayOfChar, paramInt1, paramInt2);
      paramArrayOfChar = (char[])localObject;
      if (_intern) {
        paramArrayOfChar = InternCache.instance.intern((String)localObject);
      }
      _size += 1;
      if (_symbols[paramInt3] == null)
      {
        _symbols[paramInt3] = paramArrayOfChar;
        return paramArrayOfChar;
        if (_size >= _sizeThreshold)
        {
          rehash();
          paramInt3 = _hashToIndex(calcHash(paramArrayOfChar, paramInt1, paramInt2));
        }
      }
      else
      {
        paramInt1 = paramInt3 >> 1;
        localObject = new Bucket(paramArrayOfChar, _buckets[paramInt1]);
        _buckets[paramInt1] = localObject;
        _longestCollisionList = Math.max(((Bucket)localObject).length(), _longestCollisionList);
        localObject = paramArrayOfChar;
        if (_longestCollisionList <= 255) {
          break;
        }
        reportTooManyCollisions(255);
        return paramArrayOfChar;
      }
      paramInt3 = i;
    }
  }
  
  public int hashSeed()
  {
    return _hashSeed;
  }
  
  public CharsToNameCanonicalizer makeChild(boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      String[] arrayOfString = _symbols;
      Bucket[] arrayOfBucket = _buckets;
      int i = _size;
      int j = _hashSeed;
      int k = _longestCollisionList;
      return new CharsToNameCanonicalizer(this, paramBoolean1, paramBoolean2, arrayOfString, arrayOfBucket, i, j, k);
    }
    finally {}
  }
  
  public int maxCollisionLength()
  {
    return _longestCollisionList;
  }
  
  public boolean maybeDirty()
  {
    return _dirty;
  }
  
  public void release()
  {
    if (!maybeDirty()) {}
    while (_parent == null) {
      return;
    }
    _parent.mergeChild(this);
    _dirty = false;
  }
  
  protected void reportTooManyCollisions(int paramInt)
  {
    throw new IllegalStateException("Longest collision chain in symbol table (of size " + _size + ") now exceeds maximum, " + paramInt + " -- suspect a DoS attack based on hash collisions");
  }
  
  public int size()
  {
    return _size;
  }
  
  static final class Bucket
  {
    private final int _length;
    private final Bucket _next;
    private final String _symbol;
    
    public Bucket(String paramString, Bucket paramBucket)
    {
      _symbol = paramString;
      _next = paramBucket;
      if (paramBucket == null) {}
      for (int i = 1;; i = _length + 1)
      {
        _length = i;
        return;
      }
    }
    
    public String find(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      String str = _symbol;
      for (Bucket localBucket = _next;; localBucket = localBucket.getNext())
      {
        if (str.length() == paramInt2)
        {
          int i = 0;
          if (str.charAt(i) != paramArrayOfChar[(paramInt1 + i)]) {}
          for (;;)
          {
            if (i != paramInt2) {
              break label72;
            }
            return str;
            int j = i + 1;
            i = j;
            if (j < paramInt2) {
              break;
            }
            i = j;
          }
        }
        label72:
        if (localBucket == null) {
          return null;
        }
        str = localBucket.getSymbol();
      }
    }
    
    public Bucket getNext()
    {
      return _next;
    }
    
    public String getSymbol()
    {
      return _symbol;
    }
    
    public int length()
    {
      return _length;
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */