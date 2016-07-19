package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.console.RuntimeRepl;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError.ErrorCode;
import com.facebook.stetho.json.ObjectMapper;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONObject;

class Runtime$Session
{
  private final ObjectMapper mObjectMapper = new ObjectMapper();
  private final ObjectIdMapper mObjects = new ObjectIdMapper();
  @Nullable
  private RuntimeRepl mRepl;
  
  private List<?> arrayToList(Object paramObject)
  {
    Object localObject = paramObject.getClass();
    if (!((Class)localObject).isArray()) {
      throw new IllegalArgumentException("Argument must be an array.  Was " + localObject);
    }
    if (!((Class)localObject).getComponentType().isPrimitive())
    {
      localObject = Arrays.asList((Object[])paramObject);
      return (List<?>)localObject;
    }
    int j = Array.getLength(paramObject);
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    for (;;)
    {
      localObject = localArrayList;
      if (i >= j) {
        break;
      }
      localArrayList.add(Array.get(paramObject, i));
      i += 1;
    }
  }
  
  private Runtime.EvaluateResponse buildExceptionResponse(Object paramObject)
  {
    Runtime.EvaluateResponse localEvaluateResponse = new Runtime.EvaluateResponse(null);
    wasThrown = true;
    result = objectForRemote(paramObject);
    exceptionDetails = new Runtime.ExceptionDetails(null);
    exceptionDetails.text = paramObject.toString();
    return localEvaluateResponse;
  }
  
  private Runtime.EvaluateResponse buildNormalResponse(Object paramObject)
  {
    Runtime.EvaluateResponse localEvaluateResponse = new Runtime.EvaluateResponse(null);
    wasThrown = false;
    result = objectForRemote(paramObject);
    return localEvaluateResponse;
  }
  
  private Runtime.GetPropertiesResponse getPropertiesForIterable(Iterable<?> paramIterable, boolean paramBoolean)
  {
    Runtime.GetPropertiesResponse localGetPropertiesResponse = new Runtime.GetPropertiesResponse(null);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    Iterator localIterator = paramIterable.iterator();
    if (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      Runtime.PropertyDescriptor localPropertyDescriptor = new Runtime.PropertyDescriptor(null);
      if (paramBoolean)
      {
        paramIterable = String.valueOf(i);
        i += 1;
      }
      for (;;)
      {
        name = paramIterable;
        value = objectForRemote(localObject);
        localArrayList.add(localPropertyDescriptor);
        break;
        paramIterable = null;
      }
    }
    result = localArrayList;
    return localGetPropertiesResponse;
  }
  
  private Runtime.GetPropertiesResponse getPropertiesForMap(Object paramObject)
  {
    Runtime.GetPropertiesResponse localGetPropertiesResponse = new Runtime.GetPropertiesResponse(null);
    ArrayList localArrayList = new ArrayList();
    paramObject = ((Map)paramObject).entrySet().iterator();
    while (((Iterator)paramObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)paramObject).next();
      Runtime.PropertyDescriptor localPropertyDescriptor = new Runtime.PropertyDescriptor(null);
      name = String.valueOf(localEntry.getKey());
      value = objectForRemote(localEntry.getValue());
      localArrayList.add(localPropertyDescriptor);
    }
    result = localArrayList;
    return localGetPropertiesResponse;
  }
  
  private Runtime.GetPropertiesResponse getPropertiesForObject(Object paramObject)
  {
    Runtime.GetPropertiesResponse localGetPropertiesResponse = new Runtime.GetPropertiesResponse(null);
    ArrayList localArrayList = new ArrayList();
    label213:
    for (Class localClass = paramObject.getClass(); localClass != null; localClass = localClass.getSuperclass())
    {
      Object localObject1 = new ArrayList(Arrays.asList(localClass.getDeclaredFields()));
      Collections.reverse((List)localObject1);
      if (localClass == paramObject.getClass()) {}
      for (String str = "";; str = localClass.getSimpleName() + ".")
      {
        localObject1 = ((List)localObject1).iterator();
        for (;;)
        {
          if (!((Iterator)localObject1).hasNext()) {
            break label213;
          }
          Field localField = (Field)((Iterator)localObject1).next();
          if (!Modifier.isStatic(localField.getModifiers()))
          {
            localField.setAccessible(true);
            try
            {
              Object localObject2 = localField.get(paramObject);
              Runtime.PropertyDescriptor localPropertyDescriptor = new Runtime.PropertyDescriptor(null);
              name = (str + localField.getName());
              value = objectForRemote(localObject2);
              localArrayList.add(localPropertyDescriptor);
            }
            catch (IllegalAccessException paramObject)
            {
              throw new RuntimeException((Throwable)paramObject);
            }
          }
        }
      }
    }
    Collections.reverse(localArrayList);
    result = localArrayList;
    return localGetPropertiesResponse;
  }
  
  private Runtime.GetPropertiesResponse getPropertiesForProtoContainer(Runtime.ObjectProtoContainer paramObjectProtoContainer)
  {
    Object localObject = object;
    paramObjectProtoContainer = new Runtime.RemoteObject();
    type = Runtime.ObjectType.OBJECT;
    subtype = Runtime.ObjectSubType.NODE;
    className = localObject.getClass().getName();
    description = Runtime.access$300(localObject);
    objectId = String.valueOf(mObjects.putObject(localObject));
    localObject = new Runtime.PropertyDescriptor(null);
    name = "1";
    value = paramObjectProtoContainer;
    paramObjectProtoContainer = new Runtime.GetPropertiesResponse(null);
    result = new ArrayList(1);
    result.add(localObject);
    return paramObjectProtoContainer;
  }
  
  @Nonnull
  private RuntimeRepl getRepl(RuntimeReplFactory paramRuntimeReplFactory)
  {
    try
    {
      if (mRepl == null) {
        mRepl = paramRuntimeReplFactory.newInstance();
      }
      paramRuntimeReplFactory = mRepl;
      return paramRuntimeReplFactory;
    }
    finally {}
  }
  
  public Runtime.EvaluateResponse evaluate(RuntimeReplFactory paramRuntimeReplFactory, JSONObject paramJSONObject)
  {
    paramJSONObject = (Runtime.EvaluateRequest)mObjectMapper.convertValue(paramJSONObject, Runtime.EvaluateRequest.class);
    try
    {
      if (!objectGroup.equals("console")) {
        return buildExceptionResponse("Not supported by FAB");
      }
      paramRuntimeReplFactory = buildNormalResponse(getRepl(paramRuntimeReplFactory).evaluate(expression));
      return paramRuntimeReplFactory;
    }
    catch (Throwable paramRuntimeReplFactory) {}
    return buildExceptionResponse(paramRuntimeReplFactory);
  }
  
  public Object getObjectOrThrow(String paramString)
    throws JsonRpcException
  {
    Object localObject = getObjects().getObjectForId(Integer.parseInt(paramString));
    if (localObject == null) {
      throw new JsonRpcException(new JsonRpcError(JsonRpcError.ErrorCode.INVALID_REQUEST, "No object found for " + paramString, null));
    }
    return localObject;
  }
  
  public ObjectIdMapper getObjects()
  {
    return mObjects;
  }
  
  public Runtime.GetPropertiesResponse getProperties(JSONObject paramJSONObject)
    throws JsonRpcException
  {
    paramJSONObject = (Runtime.GetPropertiesRequest)mObjectMapper.convertValue(paramJSONObject, Runtime.GetPropertiesRequest.class);
    if (!ownProperties)
    {
      paramJSONObject = new Runtime.GetPropertiesResponse(null);
      result = new ArrayList();
      return paramJSONObject;
    }
    Object localObject = getObjectOrThrow(objectId);
    paramJSONObject = (JSONObject)localObject;
    if (localObject.getClass().isArray()) {
      paramJSONObject = arrayToList(localObject);
    }
    if ((paramJSONObject instanceof Runtime.ObjectProtoContainer)) {
      return getPropertiesForProtoContainer((Runtime.ObjectProtoContainer)paramJSONObject);
    }
    if ((paramJSONObject instanceof List)) {
      return getPropertiesForIterable((List)paramJSONObject, true);
    }
    if ((paramJSONObject instanceof Set)) {
      return getPropertiesForIterable((Set)paramJSONObject, false);
    }
    if ((paramJSONObject instanceof Map)) {
      return getPropertiesForMap(paramJSONObject);
    }
    return getPropertiesForObject(paramJSONObject);
  }
  
  public Runtime.RemoteObject objectForRemote(Object paramObject)
  {
    Runtime.RemoteObject localRemoteObject = new Runtime.RemoteObject();
    if (paramObject == null)
    {
      type = Runtime.ObjectType.OBJECT;
      subtype = Runtime.ObjectSubType.NULL;
      value = JSONObject.NULL;
      return localRemoteObject;
    }
    if ((paramObject instanceof Boolean))
    {
      type = Runtime.ObjectType.BOOLEAN;
      value = paramObject;
      return localRemoteObject;
    }
    if ((paramObject instanceof Number))
    {
      type = Runtime.ObjectType.NUMBER;
      value = paramObject;
      return localRemoteObject;
    }
    if ((paramObject instanceof Character))
    {
      type = Runtime.ObjectType.NUMBER;
      value = Integer.valueOf(((Character)paramObject).charValue());
      return localRemoteObject;
    }
    if ((paramObject instanceof String))
    {
      type = Runtime.ObjectType.STRING;
      value = String.valueOf(paramObject);
      return localRemoteObject;
    }
    type = Runtime.ObjectType.OBJECT;
    className = "What??";
    objectId = String.valueOf(mObjects.putObject(paramObject));
    if (paramObject.getClass().isArray())
    {
      description = "array";
      return localRemoteObject;
    }
    if ((paramObject instanceof List))
    {
      description = "List";
      return localRemoteObject;
    }
    if ((paramObject instanceof Set))
    {
      description = "Set";
      return localRemoteObject;
    }
    if ((paramObject instanceof Map))
    {
      description = "Map";
      return localRemoteObject;
    }
    description = Runtime.access$300(paramObject);
    return localRemoteObject;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Runtime.Session
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */