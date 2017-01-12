package br.com.algorithms.machine.learning.supervisioned.tree.id3.node;

import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.feature.Feature;
import br.com.algorithms.machine.learning.supervisioned.tree.id3.data.instance.Instances;

public interface Node {

  NodeType getNodeType();

  String getOutput();

  Feature getFeature();

  Node getChildNode(String value);
}
