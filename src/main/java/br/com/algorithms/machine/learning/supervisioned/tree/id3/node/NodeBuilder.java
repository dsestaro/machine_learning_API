package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.exception.InvalidNodeInformationException;

public class NodeBuilder {

  private NodeImpl node;

  public NodeBuilder() {

    this.node = new NodeImpl();
  }

  public NodeBuilder setOutput(String output) {

    this.node.setOutput(output);

    return this;
  }

  public NodeBuilder setNodeType(NodeType nodeType) {

    this.node.setNodeType(nodeType);

    return this;
  }

  public NodeBuilder setNodeFeature(Feature feature) {

    this.node.setFeature(feature);

    return this;
  }

  public Node buildNode () {

    validateNode();

    return this.node;
  }

  private void validateNode() {

    validateNodeType();

    validateFeatureNode();

    validateLeafNode();
  }

  private void validateNodeType() {

    if(this.node.getNodeType() == null) {

      throwsNodeInformationException("The node type cannot be null.");
    }
  }

  private void validateFeatureNode() {

    if(this.node.getNodeType() == NodeType.FEATURE_NODE) {

      validateFeature();
    }
  }

  private void validateFeature() {

    if(this.node.getFeature() == null) {

      throwsNodeInformationException("The feature value cannot be null when the node type is equals " + NodeType.FEATURE_NODE + ".");
    }
  }

  private void validateLeafNode() {

    if(this.node.getNodeType() == NodeType.OUTPUT_LEAF_NODE) {

      validateOutput();
    }
  }

  private void validateOutput() {

    if(this.node.getOutput() == null || this.node.getOutput().isEmpty()) {

      throwsNodeInformationException("The output value cannot be null when the node type is equals " + NodeType.OUTPUT_LEAF_NODE + ".");
    }
  }

  private void throwsNodeInformationException(String message) {

    throw new InvalidNodeInformationException(message);
  }
}
