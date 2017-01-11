package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

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

    return this.node;
  }
}
