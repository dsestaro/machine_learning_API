package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;

public interface Node {

  NodeType getNodeType();

  String getOutput();

  Feature getFeature();

  Node setNewChildNode(String value, Node node);

  Node getChildNode(String value);
}
