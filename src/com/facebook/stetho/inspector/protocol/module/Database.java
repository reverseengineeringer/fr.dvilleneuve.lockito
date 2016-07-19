package com.facebook.stetho.inspector.protocol.module;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.database.DatabaseFilesProvider;
import com.facebook.stetho.inspector.database.DatabasePeerManager;
import com.facebook.stetho.inspector.database.DatabasePeerManager.ExecuteResultHandler;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError.ErrorCode;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

@TargetApi(11)
public class Database
  implements ChromeDevtoolsDomain
{
  private static final int MAX_EXECUTE_RESULTS = 250;
  private final DatabasePeerManager mDatabasePeerManager;
  private final ObjectMapper mObjectMapper;
  
  @Deprecated
  public Database(Context paramContext)
  {
    mDatabasePeerManager = new DatabasePeerManager(paramContext);
    mObjectMapper = new ObjectMapper();
  }
  
  public Database(Context paramContext, DatabaseFilesProvider paramDatabaseFilesProvider)
  {
    mDatabasePeerManager = new DatabasePeerManager(paramContext, paramDatabaseFilesProvider);
    mObjectMapper = new ObjectMapper();
  }
  
  private List<Object> flattenRows(Cursor paramCursor, int paramInt)
  {
    boolean bool;
    ArrayList localArrayList;
    int k;
    int i;
    if (paramInt >= 0)
    {
      bool = true;
      Util.throwIfNot(bool);
      localArrayList = new ArrayList();
      k = paramCursor.getColumnCount();
      i = 0;
    }
    for (;;)
    {
      if ((i >= paramInt) || (!paramCursor.moveToNext())) {
        break label209;
      }
      int j = 0;
      label48:
      if (j < k)
      {
        switch (paramCursor.getType(j))
        {
        case 3: 
        default: 
          localArrayList.add(paramCursor.getString(j));
        }
        for (;;)
        {
          j += 1;
          break label48;
          bool = false;
          break;
          localArrayList.add(null);
          continue;
          localArrayList.add(Long.valueOf(paramCursor.getLong(j)));
          continue;
          localArrayList.add(Double.valueOf(paramCursor.getDouble(j)));
          continue;
          localArrayList.add(paramCursor.getBlob(j));
        }
      }
      i += 1;
    }
    label209:
    if (!paramCursor.isAfterLast())
    {
      paramInt = 0;
      while (paramInt < k)
      {
        localArrayList.add("{truncated}");
        paramInt += 1;
      }
    }
    return localArrayList;
  }
  
  @ChromeDevtoolsMethod
  public void disable(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    mDatabasePeerManager.removePeer(paramJsonRpcPeer);
  }
  
  @ChromeDevtoolsMethod
  public void enable(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    mDatabasePeerManager.addPeer(paramJsonRpcPeer);
  }
  
  @ChromeDevtoolsMethod
  public JsonRpcResult executeSQL(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    paramJsonRpcPeer = (ExecuteSQLRequest)mObjectMapper.convertValue(paramJSONObject, ExecuteSQLRequest.class);
    try
    {
      paramJsonRpcPeer = (JsonRpcResult)mDatabasePeerManager.executeSQL(databaseId, query, new DatabasePeerManager.ExecuteResultHandler()
      {
        public Database.ExecuteSQLResponse handleInsert(long paramAnonymousLong)
          throws SQLiteException
        {
          Database.ExecuteSQLResponse localExecuteSQLResponse = new Database.ExecuteSQLResponse(null);
          columnNames = Arrays.asList(new String[] { "ID of last inserted row" });
          values = Arrays.asList(new Object[] { Long.valueOf(paramAnonymousLong) });
          return localExecuteSQLResponse;
        }
        
        public Database.ExecuteSQLResponse handleRawQuery()
          throws SQLiteException
        {
          Database.ExecuteSQLResponse localExecuteSQLResponse = new Database.ExecuteSQLResponse(null);
          columnNames = Arrays.asList(new String[] { "success" });
          values = Arrays.asList(new Object[] { "true" });
          return localExecuteSQLResponse;
        }
        
        public Database.ExecuteSQLResponse handleSelect(Cursor paramAnonymousCursor)
          throws SQLiteException
        {
          Database.ExecuteSQLResponse localExecuteSQLResponse = new Database.ExecuteSQLResponse(null);
          columnNames = Arrays.asList(paramAnonymousCursor.getColumnNames());
          values = Database.this.flattenRows(paramAnonymousCursor, 250);
          return localExecuteSQLResponse;
        }
        
        public Database.ExecuteSQLResponse handleUpdateDelete(int paramAnonymousInt)
          throws SQLiteException
        {
          Database.ExecuteSQLResponse localExecuteSQLResponse = new Database.ExecuteSQLResponse(null);
          columnNames = Arrays.asList(new String[] { "Modified rows" });
          values = Arrays.asList(new Object[] { Integer.valueOf(paramAnonymousInt) });
          return localExecuteSQLResponse;
        }
      });
      return paramJsonRpcPeer;
    }
    catch (SQLiteException paramJSONObject)
    {
      paramJsonRpcPeer = new Error();
      code = 0;
      message = paramJSONObject.getMessage();
      paramJSONObject = new ExecuteSQLResponse(null);
      sqlError = paramJsonRpcPeer;
    }
    return paramJSONObject;
  }
  
  @ChromeDevtoolsMethod
  public JsonRpcResult getDatabaseTableNames(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
    throws JsonRpcException
  {
    paramJsonRpcPeer = (GetDatabaseTableNamesRequest)mObjectMapper.convertValue(paramJSONObject, GetDatabaseTableNamesRequest.class);
    try
    {
      paramJSONObject = new GetDatabaseTableNamesResponse(null);
      tableNames = mDatabasePeerManager.getDatabaseTableNames(databaseId);
      return paramJSONObject;
    }
    catch (SQLiteException paramJsonRpcPeer)
    {
      throw new JsonRpcException(new JsonRpcError(JsonRpcError.ErrorCode.INVALID_REQUEST, paramJsonRpcPeer.toString(), null));
    }
  }
  
  public static class AddDatabaseEvent
  {
    @JsonProperty(required=true)
    public Database.DatabaseObject database;
  }
  
  public static class DatabaseObject
  {
    @JsonProperty(required=true)
    public String domain;
    @JsonProperty(required=true)
    public String id;
    @JsonProperty(required=true)
    public String name;
    @JsonProperty(required=true)
    public String version;
  }
  
  public static class Error
  {
    @JsonProperty(required=true)
    public int code;
    @JsonProperty(required=true)
    public String message;
  }
  
  private static class ExecuteSQLRequest
  {
    @JsonProperty(required=true)
    public String databaseId;
    @JsonProperty(required=true)
    public String query;
  }
  
  private static class ExecuteSQLResponse
    implements JsonRpcResult
  {
    @JsonProperty
    public List<String> columnNames;
    @JsonProperty
    public Database.Error sqlError;
    @JsonProperty
    public List<Object> values;
  }
  
  private static class GetDatabaseTableNamesRequest
  {
    @JsonProperty(required=true)
    public String databaseId;
  }
  
  private static class GetDatabaseTableNamesResponse
    implements JsonRpcResult
  {
    @JsonProperty(required=true)
    public List<String> tableNames;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Database
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */