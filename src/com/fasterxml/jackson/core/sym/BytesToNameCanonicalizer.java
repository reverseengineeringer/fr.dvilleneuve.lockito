package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public final class BytesToNameCanonicalizer
{
  protected static final int DEFAULT_TABLE_SIZE = 64;
  static final int INITIAL_COLLISION_LEN = 32;
  static final int LAST_VALID_BUCKET = 254;
  static final int MAX_COLL_CHAIN_FOR_REUSE = 63;
  static final int MAX_COLL_CHAIN_LENGTH = 255;
  static final int MAX_ENTRIES_FOR_REUSE = 6000;
  protected static final int MAX_TABLE_SIZE = 65536;
  static final int MIN_HASH_SIZE = 16;
  private static final int MULT = 33;
  private static final int MULT2 = 65599;
  private static final int MULT3 = 31;
  protected int _collCount;
  protected int _collEnd;
  protected Bucket[] _collList;
  private boolean _collListShared;
  protected int _count;
  private final int _hashSeed;
  protected final boolean _intern;
  protected int _longestCollisionList;
  protected int[] _mainHash;
  protected int _mainHashMask;
  private boolean _mainHashShared;
  protected Name[] _mainNames;
  private boolean _mainNamesShared;
  private transient boolean _needRehash;
  protected final BytesToNameCanonicalizer _parent;
  protected final AtomicReference<TableInfo> _tableInfo;
  
  private BytesToNameCanonicalizer(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    _parent = null;
    _hashSeed = paramInt2;
    _intern = paramBoolean;
    if (paramInt1 < 16) {
      paramInt2 = 16;
    }
    for (;;)
    {
      _tableInfo = new AtomicReference(initTableInfo(paramInt2));
      return;
      paramInt2 = paramInt1;
      if ((paramInt1 - 1 & paramInt1) != 0)
      {
        paramInt2 = i;
        while (paramInt2 < paramInt1) {
          paramInt2 += paramInt2;
        }
      }
    }
  }
  
  private BytesToNameCanonicalizer(BytesToNameCanonicalizer paramBytesToNameCanonicalizer, boolean paramBoolean, int paramInt, TableInfo paramTableInfo)
  {
    _parent = paramBytesToNameCanonicalizer;
    _hashSeed = paramInt;
    _intern = paramBoolean;
    _tableInfo = null;
    _count = count;
    _mainHashMask = mainHashMask;
    _mainHash = mainHash;
    _mainNames = mainNames;
    _collList = collList;
    _collCount = collCount;
    _collEnd = collEnd;
    _longestCollisionList = longestCollisionList;
    _needRehash = false;
    _mainHashShared = true;
    _mainNamesShared = true;
    _collListShared = true;
  }
  
  private void _addSymbol(int paramInt, Name paramName)
  {
    if (_mainHashShared) {
      unshareMain();
    }
    if (_needRehash) {
      rehash();
    }
    _count += 1;
    int j = paramInt & _mainHashMask;
    int i;
    if (_mainNames[j] == null)
    {
      _mainHash[j] = (paramInt << 8);
      if (_mainNamesShared) {
        unshareNames();
      }
      _mainNames[j] = paramName;
      paramInt = _mainHash.length;
      if (_count > paramInt >> 1)
      {
        i = paramInt >> 2;
        if (_count <= paramInt - i) {
          break label288;
        }
        _needRehash = true;
      }
    }
    label199:
    label288:
    while (_collCount < i)
    {
      return;
      if (_collListShared) {
        unshareCollision();
      }
      _collCount += 1;
      int k = _mainHash[j];
      paramInt = k & 0xFF;
      if (paramInt == 0) {
        if (_collEnd <= 254)
        {
          i = _collEnd;
          _collEnd += 1;
          paramInt = i;
          if (i >= _collList.length)
          {
            expandCollision();
            paramInt = i;
          }
          _mainHash[j] = (k & 0xFF00 | paramInt + 1);
        }
      }
      for (;;)
      {
        paramName = new Bucket(paramName, _collList[paramInt]);
        _collList[paramInt] = paramName;
        _longestCollisionList = Math.max(paramName.length(), _longestCollisionList);
        if (_longestCollisionList <= 255) {
          break;
        }
        reportTooManyCollisions(255);
        break;
        paramInt = findBestBucket();
        break label199;
        paramInt -= 1;
      }
    }
    _needRehash = true;
  }
  
  protected static int[] calcQuads(byte[] paramArrayOfByte)
  {
    int n = paramArrayOfByte.length;
    int[] arrayOfInt = new int[(n + 3) / 4];
    int j;
    for (int i = 0; i < n; i = j + 1)
    {
      int k = paramArrayOfByte[i] & 0xFF;
      int m = i + 1;
      i = k;
      j = m;
      if (m < n)
      {
        k = k << 8 | paramArrayOfByte[m] & 0xFF;
        m += 1;
        i = k;
        j = m;
        if (m < n)
        {
          k = k << 8 | paramArrayOfByte[m] & 0xFF;
          m += 1;
          i = k;
          j = m;
          if (m < n)
          {
            i = k << 8 | paramArrayOfByte[m] & 0xFF;
            j = m;
          }
        }
      }
      arrayOfInt[(j >> 2)] = i;
    }
    return arrayOfInt;
  }
  
  private static Name constructName(int paramInt1, String paramString, int paramInt2, int paramInt3)
  {
    if (paramInt3 == 0) {
      return new Name1(paramString, paramInt1, paramInt2);
    }
    return new Name2(paramString, paramInt1, paramInt2, paramInt3);
  }
  
  private static Name constructName(int paramInt1, String paramString, int[] paramArrayOfInt, int paramInt2)
  {
    if (paramInt2 < 4) {}
    int[] arrayOfInt;
    int i;
    switch (paramInt2)
    {
    default: 
      arrayOfInt = new int[paramInt2];
      i = 0;
    }
    while (i < paramInt2)
    {
      arrayOfInt[i] = paramArrayOfInt[i];
      i += 1;
      continue;
      return new Name1(paramString, paramInt1, paramArrayOfInt[0]);
      return new Name2(paramString, paramInt1, paramArrayOfInt[0], paramArrayOfInt[1]);
      return new Name3(paramString, paramInt1, paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
    }
    return new NameN(paramString, paramInt1, arrayOfInt, paramInt2);
  }
  
  public static BytesToNameCanonicalizer createRoot()
  {
    long l = System.currentTimeMillis();
    int i = (int)l;
    return createRoot((int)(l >>> 32) + i | 0x1);
  }
  
  protected static BytesToNameCanonicalizer createRoot(int paramInt)
  {
    return new BytesToNameCanonicalizer(64, true, paramInt);
  }
  
  private void expandCollision()
  {
    Bucket[] arrayOfBucket = _collList;
    _collList = ((Bucket[])Arrays.copyOf(arrayOfBucket, arrayOfBucket.length * 2));
  }
  
  private int findBestBucket()
  {
    Bucket[] arrayOfBucket = _collList;
    int j = Integer.MAX_VALUE;
    int k = -1;
    int i = 0;
    int n = _collEnd;
    if (i < n)
    {
      int m = arrayOfBucket[i].length();
      if (m >= j) {
        break label62;
      }
      if (m == 1) {
        return i;
      }
      k = i;
      j = m;
    }
    label62:
    for (;;)
    {
      i += 1;
      break;
      return k;
    }
  }
  
  public static Name getEmptyName()
  {
    return Name1.getEmptyName();
  }
  
  private TableInfo initTableInfo(int paramInt)
  {
    return new TableInfo(0, paramInt - 1, new int[paramInt], new Name[paramInt], null, 0, 0, 0);
  }
  
  private void mergeChild(TableInfo paramTableInfo)
  {
    int i = count;
    TableInfo localTableInfo2 = (TableInfo)_tableInfo.get();
    if (i <= count) {
      return;
    }
    TableInfo localTableInfo1;
    if (i <= 6000)
    {
      localTableInfo1 = paramTableInfo;
      if (longestCollisionList <= 63) {}
    }
    else
    {
      localTableInfo1 = initTableInfo(64);
    }
    _tableInfo.compareAndSet(localTableInfo2, localTableInfo1);
  }
  
  private void nukeSymbols()
  {
    _count = 0;
    _longestCollisionList = 0;
    Arrays.fill(_mainHash, 0);
    Arrays.fill(_mainNames, null);
    Arrays.fill(_collList, null);
    _collCount = 0;
    _collEnd = 0;
  }
  
  private void rehash()
  {
    int n = 0;
    _needRehash = false;
    _mainNamesShared = false;
    int m = _mainHash.length;
    int i = m + m;
    if (i > 65536) {
      nukeSymbols();
    }
    label349:
    do
    {
      return;
      _mainHash = new int[i];
      _mainHashMask = (i - 1);
      Object localObject1 = _mainNames;
      _mainNames = new Name[i];
      int j = 0;
      int k;
      for (i = 0; j < m; i = k)
      {
        arrayOfBucket = localObject1[j];
        k = i;
        if (arrayOfBucket != null)
        {
          k = i + 1;
          i = arrayOfBucket.hashCode();
          i1 = _mainHashMask & i;
          _mainNames[i1] = arrayOfBucket;
          _mainHash[i1] = (i << 8);
        }
        j += 1;
      }
      int i1 = _collEnd;
      if (i1 == 0)
      {
        _longestCollisionList = 0;
        return;
      }
      _collCount = 0;
      _collEnd = 0;
      _collListShared = false;
      Bucket[] arrayOfBucket = _collList;
      _collList = new Bucket[arrayOfBucket.length];
      m = 0;
      j = n;
      while (m < i1)
      {
        localObject1 = arrayOfBucket[m];
        while (localObject1 != null)
        {
          Object localObject2 = _name;
          k = ((Name)localObject2).hashCode();
          int i2 = _mainHashMask & k;
          int i3 = _mainHash[i2];
          if (_mainNames[i2] == null)
          {
            _mainHash[i2] = (k << 8);
            _mainNames[i2] = localObject2;
            localObject1 = _next;
            i += 1;
          }
          else
          {
            _collCount += 1;
            k = i3 & 0xFF;
            if (k == 0) {
              if (_collEnd <= 254)
              {
                n = _collEnd;
                _collEnd += 1;
                k = n;
                if (n >= _collList.length)
                {
                  expandCollision();
                  k = n;
                }
                _mainHash[i2] = (i3 & 0xFF00 | k + 1);
              }
            }
            for (;;)
            {
              localObject2 = new Bucket((Name)localObject2, _collList[k]);
              _collList[k] = localObject2;
              j = Math.max(j, ((Bucket)localObject2).length());
              break;
              k = findBestBucket();
              break label349;
              k -= 1;
            }
          }
        }
        m += 1;
      }
      _longestCollisionList = j;
    } while (i == _count);
    throw new RuntimeException("Internal error: count after rehash " + i + "; should be " + _count);
  }
  
  private void unshareCollision()
  {
    Bucket[] arrayOfBucket = _collList;
    if (arrayOfBucket == null) {}
    for (_collList = new Bucket[32];; _collList = ((Bucket[])Arrays.copyOf(arrayOfBucket, arrayOfBucket.length)))
    {
      _collListShared = false;
      return;
    }
  }
  
  private void unshareMain()
  {
    int[] arrayOfInt = _mainHash;
    _mainHash = Arrays.copyOf(arrayOfInt, arrayOfInt.length);
    _mainHashShared = false;
  }
  
  private void unshareNames()
  {
    Name[] arrayOfName = _mainNames;
    _mainNames = ((Name[])Arrays.copyOf(arrayOfName, arrayOfName.length));
    _mainNamesShared = false;
  }
  
  public Name addName(String paramString, int paramInt1, int paramInt2)
  {
    String str = paramString;
    if (_intern) {
      str = InternCache.instance.intern(paramString);
    }
    if (paramInt2 == 0) {}
    for (int i = calcHash(paramInt1);; i = calcHash(paramInt1, paramInt2))
    {
      paramString = constructName(i, str, paramInt1, paramInt2);
      _addSymbol(i, paramString);
      return paramString;
    }
  }
  
  public Name addName(String paramString, int[] paramArrayOfInt, int paramInt)
  {
    String str = paramString;
    if (_intern) {
      str = InternCache.instance.intern(paramString);
    }
    int i;
    if (paramInt < 3) {
      if (paramInt == 1) {
        i = calcHash(paramArrayOfInt[0]);
      }
    }
    for (;;)
    {
      paramString = constructName(i, str, paramArrayOfInt, paramInt);
      _addSymbol(i, paramString);
      return paramString;
      i = calcHash(paramArrayOfInt[0], paramArrayOfInt[1]);
      continue;
      i = calcHash(paramArrayOfInt, paramInt);
    }
  }
  
  public int bucketCount()
  {
    return _mainHash.length;
  }
  
  public int calcHash(int paramInt)
  {
    paramInt = _hashSeed ^ paramInt;
    paramInt += (paramInt >>> 15);
    return paramInt ^ paramInt >>> 9;
  }
  
  public int calcHash(int paramInt1, int paramInt2)
  {
    paramInt1 = (paramInt1 >>> 15 ^ paramInt1) + paramInt2 * 33 ^ _hashSeed;
    return paramInt1 + (paramInt1 >>> 7);
  }
  
  public int calcHash(int[] paramArrayOfInt, int paramInt)
  {
    int i = 3;
    if (paramInt < 3) {
      throw new IllegalArgumentException();
    }
    int j = paramArrayOfInt[0] ^ _hashSeed;
    j = ((j + (j >>> 9)) * 33 + paramArrayOfInt[1]) * 65599;
    j = j + (j >>> 15) ^ paramArrayOfInt[2];
    j += (j >>> 17);
    while (i < paramInt)
    {
      j = j * 31 ^ paramArrayOfInt[i];
      j += (j >>> 3);
      j ^= j << 7;
      i += 1;
    }
    paramInt = (j >>> 15) + j;
    return paramInt ^ paramInt << 9;
  }
  
  public int collisionCount()
  {
    return _collCount;
  }
  
  public Name findName(int paramInt)
  {
    int i = calcHash(paramInt);
    int j = _mainHashMask & i;
    int k = _mainHash[j];
    Object localObject;
    if ((k >> 8 ^ i) << 8 == 0)
    {
      localObject = _mainNames[j];
      if (localObject != null) {}
    }
    do
    {
      do
      {
        do
        {
          return null;
          if (!((Name)localObject).equals(paramInt)) {
            break;
          }
          return (Name)localObject;
        } while (k == 0);
        j = k & 0xFF;
      } while (j <= 0);
      localObject = _collList[(j - 1)];
    } while (localObject == null);
    return ((Bucket)localObject).find(i, paramInt, 0);
  }
  
  public Name findName(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {}
    int k;
    Object localObject;
    for (int i = calcHash(paramInt1);; i = calcHash(paramInt1, paramInt2))
    {
      j = _mainHashMask & i;
      k = _mainHash[j];
      if ((k >> 8 ^ i) << 8 != 0) {
        break label79;
      }
      localObject = _mainNames[j];
      if (localObject != null) {
        break;
      }
      return null;
    }
    if (((Name)localObject).equals(paramInt1, paramInt2))
    {
      return (Name)localObject;
      label79:
      if (k == 0) {
        return null;
      }
    }
    int j = k & 0xFF;
    if (j > 0)
    {
      localObject = _collList[(j - 1)];
      if (localObject != null) {
        return ((Bucket)localObject).find(i, paramInt1, paramInt2);
      }
    }
    return null;
  }
  
  public Name findName(int[] paramArrayOfInt, int paramInt)
  {
    int i = 0;
    int j;
    Object localObject;
    if (paramInt < 3)
    {
      j = paramArrayOfInt[0];
      if (paramInt < 2)
      {
        paramInt = i;
        localObject = findName(j, paramInt);
      }
    }
    int k;
    do
    {
      do
      {
        return (Name)localObject;
        paramInt = paramArrayOfInt[1];
        break;
        i = calcHash(paramArrayOfInt, paramInt);
        j = _mainHashMask & i;
        k = _mainHash[j];
        if ((k >> 8 ^ i) << 8 != 0) {
          break label145;
        }
        localName = _mainNames[j];
        localObject = localName;
      } while (localName == null);
      localObject = localName;
    } while (localName.equals(paramArrayOfInt, paramInt));
    label145:
    while (k != 0)
    {
      Name localName;
      j = k & 0xFF;
      if (j <= 0) {
        break;
      }
      localObject = _collList[(j - 1)];
      if (localObject == null) {
        break;
      }
      return ((Bucket)localObject).find(i, paramArrayOfInt, paramInt);
    }
    return null;
    return null;
  }
  
  public int hashSeed()
  {
    return _hashSeed;
  }
  
  public BytesToNameCanonicalizer makeChild(boolean paramBoolean1, boolean paramBoolean2)
  {
    return new BytesToNameCanonicalizer(this, paramBoolean2, _hashSeed, (TableInfo)_tableInfo.get());
  }
  
  public int maxCollisionLength()
  {
    return _longestCollisionList;
  }
  
  public boolean maybeDirty()
  {
    return !_mainHashShared;
  }
  
  public void release()
  {
    if ((_parent != null) && (maybeDirty()))
    {
      _parent.mergeChild(new TableInfo(this));
      _mainHashShared = true;
      _mainNamesShared = true;
      _collListShared = true;
    }
  }
  
  protected void reportTooManyCollisions(int paramInt)
  {
    throw new IllegalStateException("Longest collision chain in symbol table (of size " + _count + ") now exceeds maximum, " + paramInt + " -- suspect a DoS attack based on hash collisions");
  }
  
  public int size()
  {
    if (_tableInfo != null) {
      return _tableInfo.get()).count;
    }
    return _count;
  }
  
  static final class Bucket
  {
    private final int _length;
    protected final Name _name;
    protected final Bucket _next;
    
    Bucket(Name paramName, Bucket paramBucket)
    {
      _name = paramName;
      _next = paramBucket;
      if (paramBucket == null) {}
      for (int i = 1;; i = _length + 1)
      {
        _length = i;
        return;
      }
    }
    
    public Name find(int paramInt1, int paramInt2, int paramInt3)
    {
      Object localObject;
      if ((_name.hashCode() == paramInt1) && (_name.equals(paramInt2, paramInt3)))
      {
        localObject = _name;
        return (Name)localObject;
      }
      for (Bucket localBucket = _next;; localBucket = _next)
      {
        if (localBucket == null) {
          break label83;
        }
        Name localName = _name;
        if (localName.hashCode() == paramInt1)
        {
          localObject = localName;
          if (localName.equals(paramInt2, paramInt3)) {
            break;
          }
        }
      }
      label83:
      return null;
    }
    
    public Name find(int paramInt1, int[] paramArrayOfInt, int paramInt2)
    {
      Object localObject;
      if ((_name.hashCode() == paramInt1) && (_name.equals(paramArrayOfInt, paramInt2)))
      {
        localObject = _name;
        return (Name)localObject;
      }
      for (Bucket localBucket = _next;; localBucket = _next)
      {
        if (localBucket == null) {
          break label83;
        }
        Name localName = _name;
        if (localName.hashCode() == paramInt1)
        {
          localObject = localName;
          if (localName.equals(paramArrayOfInt, paramInt2)) {
            break;
          }
        }
      }
      label83:
      return null;
    }
    
    public int length()
    {
      return _length;
    }
  }
  
  private static final class TableInfo
  {
    public final int collCount;
    public final int collEnd;
    public final BytesToNameCanonicalizer.Bucket[] collList;
    public final int count;
    public final int longestCollisionList;
    public final int[] mainHash;
    public final int mainHashMask;
    public final Name[] mainNames;
    
    public TableInfo(int paramInt1, int paramInt2, int[] paramArrayOfInt, Name[] paramArrayOfName, BytesToNameCanonicalizer.Bucket[] paramArrayOfBucket, int paramInt3, int paramInt4, int paramInt5)
    {
      count = paramInt1;
      mainHashMask = paramInt2;
      mainHash = paramArrayOfInt;
      mainNames = paramArrayOfName;
      collList = paramArrayOfBucket;
      collCount = paramInt3;
      collEnd = paramInt4;
      longestCollisionList = paramInt5;
    }
    
    public TableInfo(BytesToNameCanonicalizer paramBytesToNameCanonicalizer)
    {
      count = _count;
      mainHashMask = _mainHashMask;
      mainHash = _mainHash;
      mainNames = _mainNames;
      collList = _collList;
      collCount = _collCount;
      collEnd = _collEnd;
      longestCollisionList = _longestCollisionList;
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */