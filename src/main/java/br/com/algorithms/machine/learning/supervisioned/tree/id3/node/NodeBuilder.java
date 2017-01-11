package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

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

  public Node buildNode () {

    validateNode();

    return this.node;
  }

  private void validateNode() {

    if(this.node.getNodeType() == null) {

      throw new InvalidNodeInformationException("The node type cannot be null.");
    }

    if(this.node.getNodeType() == NodeType.OUTPUT_LEAF_NODE) {

      if(this.node.getOutput() == null || this.node.getOutput().isEmpty()) {

        throw new InvalidNodeInformationException("The output value cannot be null em the node type is equals " + NodeType.OUTPUT_LEAF_NODE + ".");
      }
    }
  }
}
