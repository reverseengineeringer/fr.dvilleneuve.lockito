package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.elements.NodeType;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.List;

class DOM$Node
  implements JsonRpcResult
{
  @JsonProperty
  public List<String> attributes;
  @JsonProperty
  public Integer childNodeCount;
  @JsonProperty
  public List<Node> children;
  @JsonProperty(required=true)
  public String localName;
  @JsonProperty(required=true)
  public int nodeId;
  @JsonProperty(required=true)
  public String nodeName;
  @JsonProperty(required=true)
  public NodeType nodeType;
  @JsonProperty(required=true)
  public String nodeValue;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.Node
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */