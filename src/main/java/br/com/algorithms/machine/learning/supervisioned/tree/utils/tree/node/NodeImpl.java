package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.node;

public class NodeImpl implements Node {

  private NodeType nodeType;
  private String output;

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
}
