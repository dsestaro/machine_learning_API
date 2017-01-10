package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

public interface Node {

  Node setNodeType(NodeType nodeType);

  NodeType getNodeType();

  Node setOutput(String output);

  String getOutput();
}
