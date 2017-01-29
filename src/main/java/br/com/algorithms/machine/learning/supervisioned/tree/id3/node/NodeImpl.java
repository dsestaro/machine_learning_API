package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.node.InvalidNodeParameterException;
import br.com.algorithms.machine.learning.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class NodeImpl implements Node {

  public final static String INVALID_NODE_TYPE = "Node type cannot be null.";
  public final static String INVALID_OUTPUT = "Output cannot be empty or null.";

  private NodeType nodeType;
  private String output;
  private Feature feature;
  private Map<String, Node> childNodes;

  public NodeImpl() {

    this.childNodes = new HashMap<String, Node>();
  }

  public Node setNodeType(NodeType nodeType) {

    validateNodeType(nodeType);

    this.nodeType = nodeType;

    return this;
  }

  private void validateNodeType(NodeType nodeType) {

    if (nodeType == null) {

      throw new InvalidNodeParameterException(INVALID_NODE_TYPE);
    }
  }

  public NodeType getNodeType() {

    return this.nodeType;
  }

  public Node setOutput(String output) {

    validateOutput(output);

    this.output = output;

    return this;
  }

  private void validateOutput(String output) {

    if(StringUtils.isEmpty(output)) {

      throw new InvalidNodeParameterException(INVALID_OUTPUT);
    }
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
