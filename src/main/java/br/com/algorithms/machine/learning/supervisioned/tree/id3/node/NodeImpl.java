package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;

import java.util.HashMap;
import java.util.Map;

public class NodeImpl implements Node {

  private NodeType nodeType;
  private String output;
  private Feature feature;
  private Map<String, Node> childNodes;

  public NodeImpl() {

    this.childNodes = new HashMap<String, Node>();
  }

  public Node setNodeType(NodeType nodeType) {

    this.nodeType = nodeType;

    return this;
  }

  public NodeType getNodeType() {

    return this.nodeType;
  }

  public Node setOutput(String output) {

    this.output = output;

    return this;
  }

  public String getOutput() {

    return this.output;
  }

  public Node setFeature(Feature feature) {

    this.feature = feature;

    return this;
  }

  public Feature getFeature() {

    return this.feature;
  }

  public Node setNewChildNode(String value, Node node) {

    this.childNodes.put(value, node);

    return this;
  }

  public Node getChildNode(String value) {

    return this.childNodes.get(value);
  }
}
