package br.com.algorithms.machine.learning.supervisioned.tree.utils.tree.node;

public interface Node {

  Node setNodeType(NodeType nodeType);

  NodeType getNodeType();

  Node setOutput(String output);

  String getOutput();
}
